package com.haitrvn.kal.rewarded

import androidx.lifecycle.Lifecycle
import com.haitrvn.kal.core.Ad
import com.haitrvn.kal.core.RootView
import com.haitrvn.kal.initialization.AppLovinSdk
import com.haitrvn.kal.model.MaxRewarded
import kotlinx.coroutines.flow.Flow

actual class RewardedAd actual constructor(
    adUnitId: String,
    appLovinSdk: AppLovinSdk?
) {
    actual val isReady: Boolean
        get() = TODO("Not yet implemented")
    actual val unitId: String
        get() = TODO("Not yet implemented")
    actual val reviewFlow: Flow<Ad>
        get() = TODO("Not yet implemented")
    actual val expirationFlow: Flow<Pair<Ad, Ad>>
        get() = TODO("Not yet implemented")
    actual val revenueFlow: Flow<Ad>
        get() = TODO("Not yet implemented")
    actual val requestFlow: Flow<String>
        get() = TODO("Not yet implemented")
    actual val rewardedAd: Flow<MaxRewarded>
        get() = TODO("Not yet implemented")

    actual fun loadAd() {
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

actual abstract class ViewGroup
