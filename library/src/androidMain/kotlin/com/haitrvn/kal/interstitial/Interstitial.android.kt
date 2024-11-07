package com.haitrvn.kal.interstitial

import com.applovin.mediation.ads.MaxInterstitialAd
import com.haitrvn.kal.RootView
import com.haitrvn.kal.listener.ExpirationListener
import com.haitrvn.kal.listener.RequestListener
import com.haitrvn.kal.listener.RevenueListener
import com.haitrvn.kal.listener.ReviewListener
import com.haitrvn.kal.listener.ViewAdListener
import com.haitrvn.kal.initialization.AppLovinSdk
import com.haitrvn.kal.util.ContextProvider

actual class InterstitialAd actual constructor(
    private val adUnitId: String,
    private val appLovinSdk: AppLovinSdk?
) {
    private val interstitial by lazy {
        if (appLovinSdk != null) {
            MaxInterstitialAd(
                adUnitId,
                appLovinSdk.androidAppLovinSdk,
                ContextProvider.applicationContext
            )
        } else {
            MaxInterstitialAd(adUnitId, ContextProvider.applicationContext)
        }
    }

    actual val isReady: Boolean
        get() = interstitial.isReady
    actual val unitId: String
        get() = interstitial.adUnitId

    actual fun loadAd() {
        interstitial.loadAd()
    }

    actual fun setAdReviewListener(reviewListener: ReviewListener) {
        interstitial.setAdReviewListener(reviewListener)
    }

    actual fun setExpirationListener(expirationListener: ExpirationListener) {
        interstitial.setExpirationListener(expirationListener)
    }

    actual fun setExtraParameter(key: String, value: String) {
        interstitial.setExtraParameter(key, value)
    }

    actual fun setListener(viewAdListener: ViewAdListener) {
        interstitial.setListener(viewAdListener)
    }

    internal actual fun setLocalExtraParameter(key: String, param: Any) {
        interstitial.setLocalExtraParameter(key, param)
    }

    actual fun setRequestListener(requestListener: RequestListener) {
        interstitial.setRequestListener(requestListener)
    }

    actual fun setRevenueListener(revenueListener: RevenueListener) {
        interstitial.setRevenueListener(revenueListener)
    }

    actual fun showAd(rootView: RootView) {
        interstitial.showAd(rootView)
    }

    actual fun destroy() {
        interstitial.destroy()
    }

}