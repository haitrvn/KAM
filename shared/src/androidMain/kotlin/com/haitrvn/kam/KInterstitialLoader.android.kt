package com.haitrvn.kam

import android.content.Context
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.haitrvn.kam.core.KAMLoadAdError
import com.haitrvn.kam.core.KAdRequest
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume


actual class KInterstitialLoader(
    private val context: Context
) {
    actual suspend fun load(
        adUnitId: String,
        kadsRequest: KAdRequest,
    ): KInterstitial? = suspendCancellableCoroutine { continuation ->
        InterstitialAd.load(
            context,
            adUnitId,
            kadsRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    super.onAdFailedToLoad(loadAdError)
                    continuation.resume(null)
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    super.onAdLoaded(interstitialAd)
                    continuation.resume(KInterstitial(interstitialAd))
                }
            }
        )
    }

    actual suspend fun load(
        adUnitId: String,
        kadsRequest: KAdRequest,
        callback: (KInterstitial?, KAMLoadAdError?) -> Unit
    ) {
        InterstitialAd.load(
            context,
            adUnitId,
            kadsRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    super.onAdFailedToLoad(loadAdError)
                    callback(null, loadAdError.toKamError())
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    super.onAdLoaded(interstitialAd)
                    callback(KInterstitial(interstitialAd), null)
                }
            }
        )
    }

    private fun LoadAdError.toKamError(): KAMLoadAdError {
        TODO()
    }
}