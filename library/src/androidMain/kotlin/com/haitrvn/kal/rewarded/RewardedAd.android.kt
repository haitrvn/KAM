package com.haitrvn.kal.rewarded

import androidx.lifecycle.Lifecycle
import com.applovin.mediation.ads.MaxRewardedAd
import com.haitrvn.kal.RootView
import com.haitrvn.kal.initialization.AppLovinSdk
import com.haitrvn.kal.listener.ExpirationListener
import com.haitrvn.kal.listener.RequestListener
import com.haitrvn.kal.listener.RevenueListener
import com.haitrvn.kal.listener.ReviewListener
import com.haitrvn.kal.listener.RewardedAdListener
import com.haitrvn.kal.util.ContextProvider

actual class RewardedAd actual constructor(
    private val adUnitId: String,
    private val appLovinSdk: AppLovinSdk?
) {
    private val interstitial: MaxRewardedAd by lazy {
        if (appLovinSdk != null) {
            MaxRewardedAd.getInstance(
                adUnitId,
                appLovinSdk.androidAppLovinSdk,
                ContextProvider.applicationContext
            )
        } else {
            MaxRewardedAd.getInstance(adUnitId, ContextProvider.applicationContext)
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

    actual fun setListener(viewAdListener: RewardedAdListener) {
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

    actual fun showAd(placement: String, rootView: RootView) {
        interstitial.showAd(placement, rootView)
    }

    actual fun showAd(placement: String, customData: String, rootView: RootView) {
        interstitial.showAd(placement, customData, rootView)
    }

    actual fun showAd(viewGroup: ViewGroup, lifecycle: Lifecycle, rootView: RootView) {
        interstitial.showAd(viewGroup, lifecycle, rootView)
    }

    actual fun showAd(
        placement: String,
        viewGroup: ViewGroup,
        lifecycle: Lifecycle,
        rootView: RootView
    ) {
        interstitial.showAd(placement, viewGroup, lifecycle, rootView)
    }

    actual fun showAd(
        placement: String,
        customData: String,
        viewGroup: ViewGroup,
        lifecycle: Lifecycle,
        rootView: RootView
    ) {
        interstitial.showAd(placement, customData, viewGroup, lifecycle, rootView)
    }

    actual fun destroy() {
        interstitial.destroy()
    }
}

actual typealias ViewGroup = android.view.ViewGroup