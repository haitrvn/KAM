package com.haitrvn.sample

import androidx.compose.ui.graphics.ImageBitmap

sealed class Puzzle(
    open val rightPosition: Int
) {
    data class NumberPuzzle(
        val number: Int?,
        override val rightPosition: Int,
    ) : Puzzle(rightPosition)

    data class ImagePuzzle(
        val image: ImageBitmap?,
        override val rightPosition: Int,
    ) : Puzzle(rightPosition)
}

fun Puzzle.isEnabled() =
    (this is Puzzle.NumberPuzzle && this.number != null) || (this is Puzzle.ImagePuzzle && this.image != null)