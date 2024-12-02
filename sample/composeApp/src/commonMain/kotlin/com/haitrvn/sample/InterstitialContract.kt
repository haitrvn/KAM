package com.haitrvn.sample

import com.haitrvn.kam.interstitial.Interstitial

sealed interface InterstitialContract {
    data object Loading : InterstitialContract
    data object Unknown : InterstitialContract
    data class Loaded(val interstitial: Interstitial) : InterstitialContract
}