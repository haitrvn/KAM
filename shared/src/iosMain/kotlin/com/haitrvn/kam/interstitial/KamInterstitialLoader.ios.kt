package com.haitrvn.kam.interstitial

import cocoapods.Google_Mobile_Ads_SDK.GADInterstitialAd
import com.haitrvn.kam.core.model.KamAdError
import com.haitrvn.kam.core.request.KamRequest
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlinx.cinterop.ExperimentalForeignApi

@ExperimentalForeignApi
actual class KamInterstitialLoader {
    actual suspend fun load(
        adUnitId: String,
        request: KamRequest,
    ): KamInterstitial? = suspendCancellableCoroutine { continuation ->
        GADInterstitialAd.loadWithAdUnitID(adUnitId, request) { ad, error ->
            when {
                ad != null -> continuation.resume(ad)
                else -> continuation.resume(null)
            }
        }
    }

    actual suspend fun load(
        adUnitId: String,
        request: KamRequest,
        callback: (KamInterstitial?, KamAdError?) -> Unit
    ) {
        GADInterstitialAd.loadWithAdUnitID(adUnitId, request) { ad, error ->
            //TODO convert error to kmm error
            callback(ad, null)
        }
    }
}