package com.haitrvn.kam

import com.haitrvn.kam.KInterstitial
import com.haitrvn.kam.KInterstitialListener
import com.haitrvn.kam.core.KAdRequest

actual class KInterstitialLoader {
    actual suspend fun load(
        adUnitId: String,
        kadsRequest: KAdRequest,
        kInterstitialListener: KInterstitialListener
    ): KInterstitial? {
        TODO("Not yet implemented")
    }
}