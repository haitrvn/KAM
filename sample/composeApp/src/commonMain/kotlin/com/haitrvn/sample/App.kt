package com.haitrvn.sample

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haitrvn.kal.banner.MaxAdFormat
import com.haitrvn.kal.banner.MaxAdViewComposable
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        Column(Modifier.fillMaxWidth().wrapContentHeight(), horizontalAlignment = Alignment.CenterHorizontally) {
            MaxAdViewComposable("123123123", MaxAdFormat.BANNER)
        }
    }
}

data class PikachuTile(
    val id: Int,
    val value: Int,
    val position: Pair<Int, Int>,
    var isVisible: Boolean = true,
    var isSelected: Boolean = false
)

data class Path(
    val points: List<Pair<Int, Int>>,
    val isValid: Boolean
)

data class GameState(
    val board: List<PikachuTile>,
    val score: Int,
    val selectedTile: PikachuTile?,
    val currentPath: Path?,
    val rows: Int,
    val cols: Int
)

// GameEngine - Business Logic
class PikachuGameEngine {
    fun canConnectDirectly(
        board: List<PikachuTile>,
        start: Pair<Int, Int>,
        end: Pair<Int, Int>,
        rows: Int,
        cols: Int
    ): Boolean {
        if (start.first == end.first) { // Same row
            val minCol = minOf(start.second, end.second)
            val maxCol = maxOf(start.second, end.second)
            for (col in minCol + 1 until maxCol) {
                if (board[start.first * cols + col].isVisible) return false
            }
            return true
        }

        if (start.second == end.second) { // Same column
            val minRow = minOf(start.first, end.first)
            val maxRow = maxOf(start.first, end.first)
            for (row in minRow + 1 until maxRow) {
                if (board[row * cols + start.second].isVisible) return false
            }
            return true
        }

        return false
    }

    fun findLPath(
        board: List<PikachuTile>,
        start: Pair<Int, Int>,
        end: Pair<Int, Int>,
        rows: Int,
        cols: Int
    ): Path {
        // Try L-path through vertical then horizontal
        val corner1 = Pair(end.first, start.second)
        if (!board[corner1.first * cols + corner1.second].isVisible ||
            (corner1 == start || corner1 == end)) {
            if (canConnectDirectly(board, start, corner1, rows, cols) &&
                canConnectDirectly(board, corner1, end, rows, cols)) {
                return Path(listOf(start, corner1, end), true)
            }
        }

        // Try L-path through horizontal then vertical
        val corner2 = Pair(start.first, end.second)
        if (!board[corner2.first * cols + corner2.second].isVisible ||
            (corner2 == start || corner2 == end)) {
            if (canConnectDirectly(board, start, corner2, rows, cols) &&
                canConnectDirectly(board, corner2, end, rows, cols)) {
                return Path(listOf(start, corner2, end), true)
            }
        }

        return Path(emptyList(), false)
    }

    fun findZPath(
        board: List<PikachuTile>,
        start: Pair<Int, Int>,
        end: Pair<Int, Int>,
        rows: Int,
        cols: Int
    ): Path {
        // Check all possible middle points
        for (row in -1..rows) {
            val corner1 = Pair(row, start.second)
            val corner2 = Pair(row, end.second)

            if ((row in 0 until rows) &&
                (board[corner1.first * cols + corner1.second].isVisible && corner1 != start && corner1 != end ||
                        board[corner2.first * cols + corner2.second].isVisible && corner2 != start && corner2 != end)) {
                continue
            }

            if (canConnectDirectly(board, start, corner1, rows, cols) &&
                canConnectDirectly(board, corner1, corner2, rows, cols) &&
                canConnectDirectly(board, corner2, end, rows, cols)) {
                return Path(listOf(start, corner1, corner2, end), true)
            }
        }

        // Try horizontal Z-path
        for (col in -1..cols) {
            val corner1 = Pair(start.first, col)
            val corner2 = Pair(end.first, col)

            if ((col in 0 until cols) &&
                (board[corner1.first * cols + corner1.second].isVisible && corner1 != start && corner1 != end ||
                        board[corner2.first * cols + corner2.second].isVisible && corner2 != start && corner2 != end)) {
                continue
            }

            if (canConnectDirectly(board, start, corner1, rows, cols) &&
                canConnectDirectly(board, corner1, corner2, rows, cols) &&
                canConnectDirectly(board, corner2, end, rows, cols)) {
                return Path(listOf(start, corner1, corner2, end), true)
            }
        }

        return Path(emptyList(), false)
    }

    fun findPath(
        board: List<PikachuTile>,
        start: Pair<Int, Int>,
        end: Pair<Int, Int>,
        rows: Int,
        cols: Int
    ): Path {
        if (canConnectDirectly(board, start, end, rows, cols)) {
            return Path(listOf(start, end), true)
        }

        val lPath = findLPath(board, start, end, rows, cols)
        if (lPath.isValid) return lPath

        val zPath = findZPath(board, start, end, rows, cols)
        if (zPath.isValid) return zPath

        return Path(emptyList(), false)
    }

    fun createInitialBoard(): List<PikachuTile> {
        return listOf(
            1, 2, 2, 1,
            2, 2, 2, 2,
            2, 2, 2, 2,
            2, 2, 2, 2
        ).mapIndexed { index, value ->
            PikachuTile(
                id = index,
                value = value,
                position = Pair(index / 4, index % 4)
            )
        }
    }
}

// ViewModel
class PikachuGameViewModel(private val gameEngine: PikachuGameEngine) : ViewModel() {
    private val _gameState = MutableStateFlow(
        GameState(
            board = gameEngine.createInitialBoard(),
            score = 0,
            selectedTile = null,
            currentPath = null,
            rows = 4,
            cols = 4
        )
    )
    val gameState: StateFlow<GameState> = _gameState.asStateFlow()

    fun onTileClick(clickedTile: PikachuTile) = viewModelScope.launch {
        val currentState = _gameState.value

        if (!clickedTile.isVisible) return@launch

        when {
            currentState.selectedTile == null -> {
                // First tile selection
                val newBoard = currentState.board.map {
                    if (it.id == clickedTile.id) it.copy(isSelected = true)
                    else it
                }
                _gameState.value = currentState.copy(
                    board = newBoard,
                    selectedTile = clickedTile,
                    currentPath = null
                )
            }
            currentState.selectedTile.id == clickedTile.id -> {
                // Deselect current tile
                val newBoard = currentState.board.map {
                    if (it.id == clickedTile.id) it.copy(isSelected = false)
                    else it
                }
                _gameState.value = currentState.copy(
                    board = newBoard,
                    selectedTile = null,
                    currentPath = null
                )
            }
            currentState.selectedTile.value == clickedTile.value -> {
                // Try to match tiles
                val path = gameEngine.findPath(
                    currentState.board,
                    currentState.selectedTile.position,
                    clickedTile.position,
                    currentState.rows,
                    currentState.cols
                )

                if (path.isValid) {
                    _gameState.value = currentState.copy(currentPath = path)
                    delay(300)
                    val newBoard = currentState.board.map {
                        when (it.id) {
                            currentState.selectedTile.id, clickedTile.id ->
                                it.copy(isVisible = false, isSelected = false)
                            else -> it
                        }
                    }
                    _gameState.value = currentState.copy(
                        board = newBoard,
                        score = currentState.score + 10,
                        selectedTile = null,
                        currentPath = null
                    )
                } else {
                    val newBoard = currentState.board.map {
                        it.copy(isSelected = false)
                    }
                    _gameState.value = currentState.copy(
                        board = newBoard,
                        selectedTile = null,
                        currentPath = null
                    )
                }
            }
            else -> {
                // No match
                val newBoard = currentState.board.map {
                    it.copy(isSelected = false)
                }
                _gameState.value = currentState.copy(
                    board = newBoard,
                    selectedTile = null,
                    currentPath = null
                )
            }
        }
    }

    fun startNewGame() {
        _gameState.value = GameState(
            board = gameEngine.createInitialBoard(),
            score = 0,
            selectedTile = null,
            currentPath = null,
            rows = 4,
            cols = 4
        )
    }
}

// UI Components
@Composable
fun PikachuPuzzleGame(
    viewModel: PikachuGameViewModel
) {
    val gameState by viewModel.gameState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GameHeader(gameState.score)
        GameBoard(gameState, viewModel::onTileClick)
        GameControls(viewModel::startNewGame)
    }
}

@Composable
private fun GameHeader(score: Int) {
    Text(text = "Score: $score")
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
private fun GameBoard(
    gameState: GameState,
    onTileClick: (PikachuTile) -> Unit
) {
    Box {
        Column {
            gameState.board.chunked(4).forEach { row ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    row.forEach { tile ->
                        PikachuTile(
                            tile = tile,
                            onTileClick = onTileClick
                        )
                    }
                }
            }
        }

        gameState.currentPath?.let { path ->
            PathLine(path = path, rows = gameState.rows, cols = gameState.cols)
        }
    }
}

@Composable
private fun PikachuTile(
    tile: PikachuTile,
    onTileClick: (PikachuTile) -> Unit
) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .background(
                when {
                    !tile.isVisible -> Color.Transparent
                    tile.isSelected -> Color.Yellow
                    else -> Color.LightGray
                }
            )
            .clickable { onTileClick(tile) },
        contentAlignment = Alignment.Center
    ) {
        if (tile.isVisible) {
            Text(text = "${tile.value}")
        }
    }
}

@Composable
private fun PathLine(path: Path, rows: Int, cols: Int) {
    Canvas(modifier = Modifier.fillMaxSize()) {
        val points = path.points.map { (row, col) ->
            Offset(
                (col + 0.5f) * (size.width / cols),
                (row + 0.5f) * (size.height / rows)
            )
        }
        for (i in 0 until points.size - 1) {
            drawLine(
                color = Color.Red,
                start = points[i],
                end = points[i + 1],
                strokeWidth = 4f
            )
        }
    }
}

@Composable
private fun GameControls(onNewGame: () -> Unit) {
    Spacer(modifier = Modifier.height(16.dp))
    Button(onClick = onNewGame) {
        Text("New Game")
    }
}