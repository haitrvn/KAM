package com.haitrvn.sample
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.IntSize
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class PuzzleViewModel {
    private val _uiState = MutableStateFlow<PuzzleState>(PuzzleState.Initial)
    val uiState: StateFlow<PuzzleState> = _uiState.asStateFlow()

    fun startGame(imageBitmap: ImageBitmap, size: Int = 3) {
        val tiles = createImagePuzzle(imageBitmap, size)
        _uiState.value = PuzzleState.Playing(
            puzzles = tiles,
            moves = 0,
            emptyPosition = tiles.lastIndex,
            size = 4 to 4
        )
    }

    private fun createImagePuzzle(imageBitmap: ImageBitmap, size: Int): List<Puzzle.ImagePuzzle> {
        val puzzleWidth = imageBitmap.width / size
        val puzzleHeight = imageBitmap.height / size
        val puzzles = mutableListOf<Puzzle.ImagePuzzle>()

        for (i in 0 until size * size) {
            val row = i / size
            val col = i % size
            val tileBitmap = BitmapPainter(
                imageBitmap, IntOffset(col * puzzleWidth, col * puzzleHeight),
                srcSize = IntSize(puzzleWidth, puzzleHeight)
            )
            puzzles.add(Puzzle.ImagePuzzle(image = TODO(), rightPosition = i))
        }
        puzzles.shuffle()
        return puzzles
    }

    private fun createNumberPuzzle(imageBitmap: ImageBitmap, size: Int): List<Puzzle.NumberPuzzle> {
        val puzzles = mutableListOf<Puzzle.NumberPuzzle>()
        for (i in 0 until size * size) {
            puzzles.add(Puzzle.NumberPuzzle(number = i, rightPosition = i))
        }
        puzzles.shuffle()
        return puzzles
    }

    fun onPuzzleClicked(position: Int) {
        val currentState = _uiState.value as? PuzzleState.Playing ?: return
        if (isValidMove(position, currentState)) {
            val newBoard = currentState.puzzles.toMutableList()
            newBoard[currentState.emptyPosition] = newBoard[position]
            newBoard[position] = when (val puzzle = newBoard[position]) {
                is Puzzle.NumberPuzzle -> puzzle.copy(number = null)
                is Puzzle.ImagePuzzle -> puzzle.copy(image = null)
            }

            val newState = currentState.copy(
                puzzles = newBoard,
                moves = currentState.moves + 1,
                emptyPosition = position
            )

            _uiState.value = if (isVictory(newBoard)) {
                PuzzleState.Victory(newState.moves)
            } else {
                newState
            }
        }
    }

    private fun isValidMove(position: Int, state: PuzzleState.Playing): Boolean {
        val size = state.size
        val emptyPos = state.emptyPosition
        return when {
            position / size.first == emptyPos / size.first ->
                kotlin.math.abs(position - emptyPos) == 1

            position % size.second == emptyPos % size.second ->
                kotlin.math.abs(position - emptyPos) == size.second

            else -> false
        }
    }

    private fun isVictory(board: List<Puzzle>): Boolean {
        return board.mapIndexed { index, value ->
            value.rightPosition == index
        }.all { it }
    }
}