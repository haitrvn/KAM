package com.haitrvn.kam.interstitial

import android.content.Context
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.haitrvn.kam.core.model.KamAdError
import com.haitrvn.kam.core.request.KamRequest
import com.haitrvn.kam.extension.toKamError
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

actual class KamInterstitialLoader(
    private val context: Context
) {
    actual suspend fun load(
        adUnitId: String,
        request: KamRequest,
    ): KamInterstitial? = suspendCancellableCoroutine { continuation ->
        InterstitialAd.load(
            context,
            adUnitId,
            request,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    super.onAdFailedToLoad(loadAdError)
                    continuation.resume(null)
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    super.onAdLoaded(interstitialAd)
                    continuation.resume(KamInterstitial(interstitialAd))
                }
            }
        )
    }

    actual suspend fun load(
        adUnitId: String,
        request: KamRequest,
        callback: (KamInterstitial?, KamAdError?) -> Unit
    ) {
        InterstitialAd.load(
            context,
            adUnitId,
            request,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    super.onAdFailedToLoad(loadAdError)
                    callback(null, loadAdError.toKamError())
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    super.onAdLoaded(interstitialAd)
                    callback(KamInterstitial(interstitialAd), null)
                }
            }
        )
    }
}