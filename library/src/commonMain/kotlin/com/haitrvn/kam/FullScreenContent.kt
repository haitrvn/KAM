package com.haitrvn.kam

sealed interface FullScreenContent {
    data object Clicked : FullScreenContent
    data object Dismissed : FullScreenContent
    data class ShowFailed(val adError: AdError) : FullScreenContent
    data object Impression : FullScreenContent
    data object Showed : FullScreenContent
}