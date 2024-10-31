package com.haitrvn.sample

typealias BoardSize = Pair<Int, Int>

fun BoardSize.get1DSize() = first * second

val BoardSize.width get() = first
val BoardSize.height get() = second

sealed interface PuzzleState {
    data object Initial : PuzzleState
    data class Playing(
        val puzzles: List<Puzzle>,
        val size: BoardSize,
        val moves: Int,
        val emptyPosition: Int = size.second * size.first - 1
    ) : PuzzleState

    data class Victory(
        val moves: Int
    ) : PuzzleState
}