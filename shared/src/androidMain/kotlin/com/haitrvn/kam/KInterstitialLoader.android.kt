package com.haitrvn.kam

import android.content.Context
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.haitrvn.kam.core.KAdRequest
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume


actual class KInterstitialLoader(
    private val context: Context
) {
    actual suspend fun load(
        adUnitId: String,
        kadsRequest: KAdRequest,
        kInterstitialListener: KInterstitialListener
    ): KInterstitial? = suspendCancellableCoroutine { continuation ->
        InterstitialAd.load(
            context,
            adUnitId,
            kadsRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    super.onAdFailedToLoad(loadAdError)
                    kInterstitialListener.onInterstitialFailedToLoad()
                    continuation.resume(null)
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    super.onAdLoaded(interstitialAd)
                    kInterstitialListener.onInterstitialLoaded(KInterstitial(interstitialAd))
                    continuation.resume(KInterstitial(interstitialAd))
                }
            }
        )
    }
}