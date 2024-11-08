package com.haitrvn.kal.openapp

import com.applovin.mediation.ads.MaxAppOpenAd
import com.haitrvn.kal.initialization.AppLovinSdk
import com.haitrvn.kal.listener.ExpirationListener
import com.haitrvn.kal.listener.RequestListener
import com.haitrvn.kal.listener.RevenueListener
import com.haitrvn.kal.listener.ReviewListener
import com.haitrvn.kal.listener.RewardedAdListener
import com.haitrvn.kal.util.ContextProvider

actual class AppOpenAd actual constructor(
    private val adUnitId: String,
    private val appLovinSdk: AppLovinSdk?
) {
    private val appOpenAd: MaxAppOpenAd by lazy {
        if (appLovinSdk != null) {
            MaxAppOpenAd(adUnitId, appLovinSdk.androidAppLovinSdk)
        } else {
            MaxAppOpenAd(adUnitId, ContextProvider.applicationContext)
        }
    }

    actual val isReady: Boolean
        get() = appOpenAd.isReady
    actual val unitId: String
        get() = appOpenAd.adUnitId

    actual fun loadAd() {
        appOpenAd.loadAd()
    }

    actual fun setAdReviewListener(reviewListener: ReviewListener) {
        appOpenAd.setAdReviewListener(reviewListener)
    }

    actual fun setExpirationListener(expirationListener: ExpirationListener) {
        appOpenAd.setExpirationListener(expirationListener)
    }

    actual fun setExtraParameter(key: String, value: String) {
        appOpenAd.setExtraParameter(key, value)
    }

    actual fun setListener(viewAdListener: RewardedAdListener) {
        appOpenAd.setListener(viewAdListener)
    }

    internal actual fun setLocalExtraParameter(key: String, param: Any) {
        appOpenAd.setLocalExtraParameter(key, param)
    }

    actual fun setRequestListener(requestListener: RequestListener) {
        appOpenAd.setRequestListener(requestListener)
    }

    actual fun setRevenueListener(revenueListener: RevenueListener) {
        appOpenAd.setRevenueListener(revenueListener)
    }

    actual fun showAd() {
        appOpenAd.showAd()
    }

    actual fun showAd(placement: String) {
        appOpenAd.showAd(placement)
    }

    actual fun showAd(placement: String, customData: String) {
        appOpenAd.showAd(placement, customData)
    }

    actual fun destroy() {
        appOpenAd.destroy()
    }

}