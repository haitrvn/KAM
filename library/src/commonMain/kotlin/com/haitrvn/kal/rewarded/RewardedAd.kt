package com.haitrvn.kal.rewarded

import androidx.lifecycle.Lifecycle
import com.haitrvn.kal.core.Ad
import com.haitrvn.kal.core.RootView
import com.haitrvn.kal.initialization.AppLovinSdk
import com.haitrvn.kal.model.MaxRewarded
import kotlinx.coroutines.flow.Flow

expect class RewardedAd(
    adUnitId: String,
    appLovinSdk: AppLovinSdk? = null
) {
    val isReady: Boolean
    val unitId: String

    val reviewFlow: Flow<Ad>
    val expirationFlow: Flow<Pair<Ad, Ad>>
    val revenueFlow: Flow<Ad>
    val requestFlow: Flow<String>
    val rewardedAd: Flow<MaxRewarded>

    fun loadAd()
    fun setExtraParameter(key: String, value: String)
    internal fun setLocalExtraParameter(key: String, param: Any)
    fun showAd(rootView: RootView)
    fun showAd(placement: String, rootView: RootView)
    fun showAd(placement: String, customData: String, rootView: RootView)
    internal fun showAd(viewGroup: ViewGroup, lifecycle: Lifecycle, rootView: RootView)
    internal fun showAd(placement: String, viewGroup: ViewGroup, lifecycle: Lifecycle, rootView: RootView)
    internal fun showAd(placement: String, customData: String,viewGroup: ViewGroup, lifecycle: Lifecycle, rootView: RootView)
    fun destroy()
}

expect abstract class ViewGroup