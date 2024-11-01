package com.haitrvn.sample

import androidx.compose.ui.graphics.painter.BitmapPainter

sealed class Puzzle(
    open val rightPosition: Int
) {
    data class NumberPuzzle(
        val number: Int?,
        override val rightPosition: Int,
    ) : Puzzle(rightPosition)

    data class ImagePuzzle(
        val image: BitmapPainter?,
        override val rightPosition: Int,
    ) : Puzzle(rightPosition)
}

fun Puzzle.isEnabled() =
    (this is Puzzle.NumberPuzzle && this.number != null) || (this is Puzzle.ImagePuzzle && this.image != null)