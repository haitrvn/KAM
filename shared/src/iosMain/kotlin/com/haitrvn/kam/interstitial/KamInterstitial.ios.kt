package com.haitrvn.kam.interstitial

import com.haitrvn.kam.core.RootView
import cocoapods.Google_Mobile_Ads_SDK.GADInterstitialAd
import cocoapods.Google_Mobile_Ads_SDK.GADFullScreenContentDelegate
import cocoapods.Google_Mobile_Ads_SDK.GADPaidEventHandler
import cocoapods.Google_Mobile_Ads_SDK.GADAdValue
import com.haitrvn.kam.core.model.KamAdValue
import com.haitrvn.kam.core.callback.KamFullScreenContentCallBack
import com.haitrvn.kam.core.model.PrecisionType
import kotlinx.cinterop.ExperimentalForeignApi

@ExperimentalForeignApi
actual class KamInterstitial(
    private val interstitialAd: GADInterstitialAd
) {
    actual fun showAd(rootView: RootView) {
        interstitialAd.show(rootView)
    }

    actual fun setImmersiveMode(immersive: Boolean) {
        interstitialAd.setImmersiveMode(immersive)
    }

    actual fun setFullScreenContentCallback(callback: KamFullScreenContentCallBack) {
    }

    actual fun setOnPaidEventListener(callback: (KamAdValue) -> Unit) {
    }

    private fun GADAdValue.toKamAdValue(): KamAdValue {
//        return KamAdValue(
//            precisionType = com.haitrvn.kam.core.PrecisionType.entries.firstOrNull { it.type == precisionType }
//                ?: com.haitrvn.kam.core.PrecisionType.UNKNOWN,
//            valueMicros = valueMicros,
//            currencyCode = currencyCode
//        )
        TODO()
    }
}