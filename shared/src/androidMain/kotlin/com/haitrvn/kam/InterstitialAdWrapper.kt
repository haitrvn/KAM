package com.haitrvn.kam

import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.OnPaidEventListener
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.haitrvn.kam.core.RootView

class InterstitialAdWrapper(
    private val interstitialAd: InterstitialAd
) : InterstitialAd() {
    override fun show(activity: RootView) {
        interstitialAd.show(activity)
    }

    override fun getFullScreenContentCallback() = interstitialAd.fullScreenContentCallback

    override fun getOnPaidEventListener() = interstitialAd.onPaidEventListener

    override fun getResponseInfo() = interstitialAd.responseInfo

    override fun getAdUnitId() = interstitialAd.adUnitId

    override fun setFullScreenContentCallback(fullScreenContentCallback: FullScreenContentCallback?) {
        interstitialAd.fullScreenContentCallback = fullScreenContentCallback
    }

    override fun setImmersiveMode(immersiveMode: Boolean) {
        interstitialAd.setImmersiveMode(immersiveMode)
    }

    override fun setOnPaidEventListener(paidEventListener: OnPaidEventListener?) {
        interstitialAd.onPaidEventListener = paidEventListener
    }
}