package com.haitrvn.kam.interstitial

import com.google.android.gms.ads.AdValue
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.haitrvn.kam.core.RootView
import com.haitrvn.kam.core.callback.KamFullScreenContentCallBack
import com.haitrvn.kam.core.init.ContextProvider
import com.haitrvn.kam.core.model.KamAdError
import com.haitrvn.kam.core.model.KamAdValue
import com.haitrvn.kam.core.model.PrecisionType
import com.haitrvn.kam.core.model.ResponseInfo
import com.haitrvn.kam.core.request.KamRequest
import com.haitrvn.kam.extension.toKamError
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

actual class KamInterstitial(
    private val interstitialAd: InterstitialAd
) {
    actual fun show(rootView: RootView) {
        interstitialAd.show(rootView)
    }

    internal actual fun setImmersiveMode(immersive: Boolean) {
        interstitialAd.setImmersiveMode(immersive)
    }

    actual fun setFullScreenContentCallback(callback: KamFullScreenContentCallBack) {
        interstitialAd.fullScreenContentCallback = FullScreenContentCallbackImpl(callback)
    }

    actual fun setOnPaidEventListener(callback: (KamAdValue?) -> Unit) {
        interstitialAd.setOnPaidEventListener { adValue ->
            callback(adValue.toKamAdValue())
        }
    }

    private fun AdValue.toKamAdValue(): KamAdValue {
        return KamAdValue(
            precisionType = PrecisionType.entries.firstOrNull { it.type == precisionType }
                ?: PrecisionType.UNKNOWN,
            valueMicros = valueMicros,
            currencyCode = currencyCode
        )
    }

    internal actual fun getAdUnitId(): String {
        return interstitialAd.adUnitId
    }

    internal actual fun getFullScreenContentCallback(): KamFullScreenContentCallBack {
        return (interstitialAd.fullScreenContentCallback as FullScreenContentCallbackImpl).callback
    }

    internal actual fun getResponseInfo(): ResponseInfo {
        TODO()
    }

    actual companion object {
        actual suspend fun load(
            adUnitId: String,
            request: KamRequest,
        ): KamInterstitial? = suspendCancellableCoroutine { continuation ->
            InterstitialAd.load(
                ContextProvider.applicationContext,
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
                ContextProvider.applicationContext,
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

        internal actual fun isAdAvailable(adUnitId: String): Boolean {
            return InterstitialAd.isAdAvailable(ContextProvider.applicationContext, adUnitId)
        }

        internal actual fun pollAd(adUnitId: String): KamInterstitial? {
            return InterstitialAd.pollAd(ContextProvider.applicationContext, adUnitId)
                ?.let { KamInterstitial(it) }
        }
    }
}