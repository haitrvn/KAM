package com.haitrvn.kal.interstitial

import androidx.lifecycle.Lifecycle
import cocoapods.AppLovinSDK.MAInterstitialAd
import com.haitrvn.kal.core.Ad
import com.haitrvn.kal.core.RootView
import com.haitrvn.kal.initialization.AppLovinSdk
import com.haitrvn.kal.model.AdEvent
import com.haitrvn.kal.rewarded.ViewGroup
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.flow.Flow

@OptIn(ExperimentalForeignApi::class)
actual class InterstitialAd actual constructor(
    private val adUnitId: String,
    private val appLovinSdk: AppLovinSdk?
) {
    private val interstitial by lazy {
        if (appLovinSdk != null) {
            MAInterstitialAd(adUnitId, appLovinSdk.iosApplovinSdk)
        } else {
            MAInterstitialAd(adUnitId)
        }
    }

    actual val reviewFlow: Flow<Ad>
        get() = TODO("Not yet implemented")
    actual val expirationFlow: Flow<Pair<Ad, Ad>>
        get() = TODO("Not yet implemented")
    actual val revenueFlow: Flow<Ad>
        get() = TODO("Not yet implemented")
    actual val requestFlow: Flow<String>
        get() = TODO("Not yet implemented")
    actual val adEventFlow: Flow<AdEvent>
        get() = TODO("Not yet implemented")

    actual fun loadAd() {
        interstitial.loadAd()
    }

    actual fun setExtraParameter(key: String, value: String) {
    }

    internal actual fun setLocalExtraParameter(key: String, param: Any) {
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