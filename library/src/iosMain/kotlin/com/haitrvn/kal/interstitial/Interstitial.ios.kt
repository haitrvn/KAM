package com.haitrvn.kal.interstitial

import androidx.lifecycle.Lifecycle
import com.haitrvn.kal.core.RootView
import com.haitrvn.kal.initialization.AppLovinSdk
import com.haitrvn.kal.listener.ExpirationListener
import com.haitrvn.kal.listener.RequestListener
import com.haitrvn.kal.listener.RevenueListener
import com.haitrvn.kal.listener.ReviewListener
import com.haitrvn.kal.listener.ViewAdListener
import com.haitrvn.kal.rewarded.ViewGroup

actual class InterstitialAd actual constructor(
    private val adUnitId: String,
    private val appLovinSdk: AppLovinSdk?
) {
    actual val isReady: Boolean
        get() = TODO("Not yet implemented")
    actual val unitId: String
        get() = TODO("Not yet implemented")

    actual fun loadAd() {
    }

    actual fun setAdReviewListener(reviewListener: ReviewListener) {
    }

    actual fun setExpirationListener(expirationListener: ExpirationListener) {
    }

    actual fun setExtraParameter(key: String, value: String) {
    }

    actual fun setListener(viewAdListener: ViewAdListener) {
    }

    internal actual fun setLocalExtraParameter(key: String, param: Any) {
    }

    actual fun setRequestListener(requestListener: RequestListener) {
    }

    actual fun setRevenueListener(revenueListener: RevenueListener) {
    }

    actual fun showAd(rootView: RootView) {
    }

    actual fun showAd(placement: String, rootView: RootView) {
    }

    actual fun showAd(
        placement: String,
        customData: String,
        rootView: RootView
    ) {
    }

    internal actual fun showAd(
        viewGroup: ViewGroup,
        lifecycle: Lifecycle,
        rootView: RootView
    ) {
    }

    internal actual fun showAd(
        placement: String,
        viewGroup: ViewGroup,
        lifecycle: Lifecycle,
        rootView: RootView
    ) {
    }

    internal actual fun showAd(
        placement: String,
        customData: String,
        viewGroup: ViewGroup,
        lifecycle: Lifecycle,
        rootView: RootView
    ) {
    }

    actual fun destroy() {
    }
}