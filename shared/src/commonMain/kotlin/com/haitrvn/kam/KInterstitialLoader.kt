package com.haitrvn.kam

import com.haitrvn.kam.core.KAdRequest

expect class KInterstitialLoader {
    suspend fun load(
        adUnitId: String,
        kadsRequest: KAdRequest,
        kInterstitialListener: KInterstitialListener
    ): KInterstitial?
}