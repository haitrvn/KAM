package com.haitrvn.kam.interstitial

import cocoapods.Google_Mobile_Ads_SDK.GADInterstitialAd
import com.haitrvn.kam.core.model.KamAdError
import com.haitrvn.kam.core.request.KamRequest
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.suspendCancellableCoroutine
import platform.Foundation.NSError
import kotlin.coroutines.resume

@ExperimentalForeignApi
actual class KamInterstitialLoader {
    actual suspend fun load(
        adUnitId: String,
        request: KamRequest,
    ): KamInterstitial? = suspendCancellableCoroutine { continuation ->
        GADInterstitialAd.loadWithAdUnitID(adUnitId, request) { ad, error ->
            when {
                ad != null -> continuation.resume(KamInterstitial(ad))
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
            when {
                ad != null -> callback(KamInterstitial(ad), null)
                else -> callback(null, error?.toKamAdError())
            }
        }
    }

    private fun NSError.toKamAdError(): KamAdError {
        return KamAdError(
            code = code.toInt(),
            cause = localizedFailureReason().orEmpty(),
            domain = domain.toString(),
            message = localizedDescription
        )
    }
}