package com.haitrvn.kal.interstitial

import androidx.lifecycle.Lifecycle
import com.haitrvn.kal.core.Ad
import com.haitrvn.kal.core.RootView
import com.haitrvn.kal.initialization.AppLovinSdk
import com.haitrvn.kal.model.AdEvent
import com.haitrvn.kal.model.ReviewAd
import com.haitrvn.kal.rewarded.ViewGroup
import kotlinx.coroutines.flow.Flow

expect class InterstitialAd(
    adUnitId: String,
    appLovinSdk: AppLovinSdk? = null
) {
    val unitId: String
    val isReady: Boolean
    val reviewFlow: Flow<ReviewAd>
    val expirationFlow: Flow<Pair<Ad, Ad>>
    val revenueFlow: Flow<Ad>
    val requestFlow: Flow<String>
    val adEventFlow: Flow<AdEvent>

    fun loadAd()
    fun setExtraParameter(key: String, value: String)
    internal fun setLocalExtraParameter(key: String, param: Any)
    fun showAd(rootView: RootView)
    fun showAd(placement: String, rootView: RootView)
    fun showAd(placement: String, customData: String, rootView: RootView)
    internal fun showAd(viewGroup: ViewGroup, lifecycle: Lifecycle, rootView: RootView)
    internal fun showAd(
        placement: String, viewGroup: ViewGroup, lifecycle: Lifecycle, rootView: RootView
    )

    internal fun showAd(
        placement: String,
        customData: String,
        viewGroup: ViewGroup,
        lifecycle: Lifecycle,
        rootView: RootView
    )

    fun destroy()
}