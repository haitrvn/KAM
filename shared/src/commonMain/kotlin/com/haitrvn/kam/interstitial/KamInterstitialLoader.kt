package com.haitrvn.kam.interstitial

import com.haitrvn.kam.core.model.KamAdError
import com.haitrvn.kam.core.request.KamRequest

expect class KamInterstitialLoader {
    suspend fun load(
        adUnitId: String,
        request: KamRequest,
    ): KamInterstitial?

    suspend fun load(
        adUnitId: String,
        request: KamRequest,
        callback: (KamInterstitial?, KamAdError?) -> Unit,
    )
}