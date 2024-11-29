package com.haitrvn.kal.rewarded

import androidx.lifecycle.Lifecycle
import com.haitrvn.kal.core.Ad
import com.haitrvn.kal.core.RootView
import com.haitrvn.kal.initialization.AppLovinSdk
import com.haitrvn.kal.model.AdEvent
import com.haitrvn.kal.model.ReviewAd
import kotlinx.coroutines.flow.Flow

expect class RewardedAd(
    adUnitId: String,
    appLovinSdk: AppLovinSdk? = null
) {
    val isReady: Boolean
    val unitId: String
    val reviewFlow: Flow<ReviewAd>
    val expirationFlow: Flow<Pair<Ad, Ad>>
    val revenueFlow: Flow<Ad>
    val requestFlow: Flow<String>
    val rewardedAdFlow: Flow<AdEvent>

    suspend fun loadAd(): RewardedAd?
    fun showAd(rootView: RootView)
    fun showAd(placement: String, rootView: RootView)
    fun showAd(placement: String, customData: String, rootView: RootView)
    fun destroy()
    fun setExtraParameter(key: String, value: String)

    internal fun showAd(viewGroup: ViewGroup, lifecycle: Lifecycle, rootView: RootView)
    internal fun showAd(placement: String, viewGroup: ViewGroup, lifecycle: Lifecycle, rootView: RootView)
    internal fun showAd(placement: String, customData: String,viewGroup: ViewGroup, lifecycle: Lifecycle, rootView: RootView)

    internal fun setLocalExtraParameter(key: String, param: Any)
}

expect class ViewGroup