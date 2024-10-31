package com.haitrvn.sample

import android.graphics.Bitmap
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asAndroidBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// GameState.kt
sealed interface PuzzleGameState {
    data object Initial : PuzzleGameState
    data class Playing(
        val tiles: List<ImageTile>,
        val size: Int,
        val moves: Int,
        val emptyPosition: Int,
        val originalImageBitmap: ImageBitmap
    ) : PuzzleGameState

    data class Victory(
        val moves: Int
    ) : PuzzleGameState
}


data class ImageTile(
    val bitmap: ImageBitmap?,
    val position: Int
)

class GameViewModel {
    private val _Puzzle_gameState = MutableStateFlow<PuzzleGameState>(PuzzleGameState.Initial)
    val puzzleGameState: StateFlow<PuzzleGameState> = _Puzzle_gameState.asStateFlow()

    fun startGame(imageBitmap: ImageBitmap, size: Int = 3) {
        val tiles = createImageTiles(imageBitmap, size)
        _Puzzle_gameState.value = PuzzleGameState.Playing(
            tiles = tiles,
            size = size,
            moves = 0,
            emptyPosition = tiles.first { it.bitmap == null }.position,
            originalImageBitmap = imageBitmap
        )
    }

    private fun createImageTiles(imageBitmap: ImageBitmap, size: Int): List<ImageTile> {
        val tileWidth = imageBitmap.width / size
        val tileHeight = imageBitmap.height / size
        val tiles = mutableListOf<ImageTile>()

        for (i in 0 until size * size - 1) {
            val row = i / size
            val col = i % size
            val tileBitmap = Bitmap.createBitmap(
                imageBitmap.asAndroidBitmap(),
                col * tileWidth,
                row * tileHeight,
                tileWidth,
                tileHeight
            ).asImageBitmap()

            tiles.add(ImageTile(bitmap = tileBitmap, position = i))
        }

        // Add empty tile
        tiles.add(ImageTile(bitmap = null, position = size * size - 1))
        tiles.shuffle()

        return tiles
    }


    fun onTileClicked(position: Int) {
        val currentState = _Puzzle_gameState.value as? PuzzleGameState.Playing ?: return

        if (isValidMove(position, currentState)) {
            val newBoard = currentState.tiles.toMutableList()
            newBoard[currentState.emptyPosition] = newBoard[position]
            newBoard[position] = newBoard[position].copy(bitmap = null)

            val newState = currentState.copy(
                tiles = newBoard,
                moves = currentState.moves + 1,
                emptyPosition = position
            )

            _Puzzle_gameState.value = if (isVictory(newBoard)) {
                PuzzleGameState.Victory(newState.moves)
            } else {
                newState
            }
        }
    }

    private fun isValidMove(position: Int, state: PuzzleGameState.Playing): Boolean {
        val size = state.size
        val emptyPos = state.emptyPosition

        // Check if the clicked position is adjacent to empty position
        return when {
            // Same row
            position / size == emptyPos / size ->
                kotlin.math.abs(position - emptyPos) == 1
            // Same column
            position % size == emptyPos % size ->
                kotlin.math.abs(position - emptyPos) == size

            else -> false
        }
    }

    private fun isVictory(board: List<ImageTile>): Boolean {
        return board.dropLast(1).mapIndexed { index, value ->
            value.position == index + 1
        }.all { it }
    }
}

// Common UI Components
@Composable
fun NumberPuzzleGame(
    viewModel: GameViewModel = remember { GameViewModel() },
    choseImage: () -> Unit
) {
    val gameState by viewModel.puzzleGameState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Number Puzzle",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        when (gameState) {
            is PuzzleGameState.Initial -> {}
            is PuzzleGameState.Playing -> {
                PlayingBoard(
                    state = gameState as PuzzleGameState.Playing,
                    onTileClick = viewModel::onTileClicked
                )
            }

            is PuzzleGameState.Victory -> {
                VictoryScreen(
                    moves = (gameState as PuzzleGameState.Victory).moves,
                    onNewGame = { }
                )
            }
        }

        Button(
            onClick = { choseImage() },
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Chose Image")
        }
    }
}

@Composable
private fun PlayingBoard(
    state: PuzzleGameState.Playing,
    onTileClick: (Int) -> Unit
) {
    Column(
        modifier = Modifier.padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Moves: ${state.moves}",
            fontSize = 18.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Create grid layout
        val size = state.size
        for (row in 0 until size) {
            Row(
                modifier = Modifier.padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                for (col in 0 until size) {
                    val position = row * size + col
                    GameTile(
                        bitmap = state.tiles[position].bitmap,
                        onClick = { onTileClick(position) }
                    )
                }
            }
        }
    }
}

@Composable
private fun GameTile(
    bitmap: ImageBitmap?,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .size(80.dp)
            .padding(4.dp)
            .clickable(enabled = bitmap != null, onClick = onClick),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.Gray),
        backgroundColor = Color.LightGray
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            if (bitmap != null) {
                Image(
                    bitmap = bitmap,
                    contentDescription = null
                )
            }
        }
    }
}

@Composable
private fun VictoryScreen(
    moves: Int,
    onNewGame: () -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Congratulations!",
            fontSize = 24.sp,
            color = Color.Green
        )
        Text(
            text = "You solved the puzzle in $moves moves",
            fontSize = 18.sp,
            modifier = Modifier.padding(vertical = 8.dp)
        )
        Button(onClick = onNewGame) {
            Text("Play Again")
        }
    }
}
