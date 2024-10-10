package com.haitrvn.kam.interstitial

import com.google.android.gms.ads.AdValue
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.haitrvn.kam.core.RootView
import com.haitrvn.kam.core.model.KamAdValue
import com.haitrvn.kam.core.callback.KamFullScreenContentCallBack
import com.haitrvn.kam.core.model.PrecisionType

actual class KamInterstitial(
    private val interstitialAd: InterstitialAd
) {
    actual fun showAd(rootView: RootView) {
        interstitialAd.show(rootView)
    }

    actual fun setImmersiveMode(immersive: Boolean) {
        interstitialAd.setImmersiveMode(immersive)
    }

    actual fun setFullScreenContentCallback(callback: KamFullScreenContentCallBack) {
        interstitialAd.fullScreenContentCallback = FullScreenContentCallbackImpl(callback)
    }

    actual fun setOnPaidEventListener(callback: (KamAdValue) -> Unit) {
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
}