package com.haitrvn.kam

import com.haitrvn.kam.KInterstitial
import cocoapods.Google_Mobile_Ads_SDK.GADMobileAds
import cocoapods.Google_Mobile_Ads_SDK.GADInterstitial
import com.haitrvn.kam.core.KAMLoadAdError
import com.haitrvn.kam.core.KAdRequest
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlinx.cinterop.ExperimentalForeignApi

@ExperimentalForeignApi
actual class KInterstitialLoader {
    actual suspend fun load(
        adUnitId: String,
        kadsRequest: KAdRequest,
    ): KInterstitial? = suspendCancellableCoroutine { continuation ->
        GADInterstitial.loadWithAdUnitID(adUnitId, kadsRequest) { ad, error ->
            when {
                ad != null -> continuation.resume(ad)
                else -> continuation.resume(null)
            }
        }
    }

    actual suspend fun load(
        adUnitId: String,
        kadsRequest: KAdRequest,
        callback: (KInterstitial?, KAMLoadAdError?) -> Unit
    ) {
        GADInterstitial.loadWithAdUnitID(adUnitId, kadsRequest) { ad, error ->
            //TODO convert error to kmm error
            callback(ad, null)
        }
    }
}