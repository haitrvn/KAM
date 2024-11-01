package com.haitrvn.sample

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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NumberPuzzleGame(
    puzzleViewModel: PuzzleViewModel,
    choseImage: () -> Unit
) {
    val viewModel = remember { puzzleViewModel }
    val gameState by viewModel.uiState.collectAsState()
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
            is PuzzleState.Initial -> {}
            is PuzzleState.Playing -> {
                BoardGame(
                    state = gameState as PuzzleState.Playing,
                    onTileClick = viewModel::onPuzzleClicked
                )
            }

            is PuzzleState.Victory -> {
                VictoryScreen(
                    moves = (gameState as PuzzleState.Victory).moves,
                    onNewGame = { viewModel.startGame() }
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
fun BoardGame(
    state: PuzzleState.Playing,
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

        LazyVerticalGrid(
            columns = GridCells.Fixed(state.size.width),
            content = {
                items(state.puzzles.size) { index ->
                    GameTile(
                        puzzle = state.puzzles[index],
                        onClick = { onTileClick(index) }
                    )
                }
            }
        )
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

@Composable
private fun GameTile(
    puzzle: Puzzle,
    onClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .size(80.dp)
            .padding(4.dp)
            .clickable(
                enabled = puzzle.isEnabled(),
                onClick = onClick
            ),
        elevation = 4.dp,
        shape = RoundedCornerShape(8.dp),
        border = BorderStroke(1.dp, Color.Gray),
        backgroundColor = Color.LightGray
    ) {
        Box(
            contentAlignment = Alignment.Center
        ) {
            when (puzzle) {
                is Puzzle.NumberPuzzle -> {
                    Text(text = puzzle.number?.toString() ?: "")
                }

                is Puzzle.ImagePuzzle -> {
                    puzzle.image?.let { Image(painter = it, contentDescription = null) }
                }
            }
        }
    }
}