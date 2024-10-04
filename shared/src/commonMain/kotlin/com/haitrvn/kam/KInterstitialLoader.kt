package com.haitrvn.kam

import com.haitrvn.kam.core.KAMLoadAdError
import com.haitrvn.kam.core.KAdRequest

expect class KInterstitialLoader {
    suspend fun load(
        adUnitId: String,
        kadsRequest: KAdRequest,
    ): KInterstitial?

    suspend fun load(
        adUnitId: String,
        kadsRequest: KAdRequest,
        callback: (KInterstitial?, KAMLoadAdError?) -> Unit,
    )
}