package com.haitrvn.kal.rewarded

import androidx.lifecycle.Lifecycle
import com.haitrvn.kal.core.RootView
import com.haitrvn.kal.initialization.AppLovinSdk
import com.haitrvn.kal.listener.ExpirationListener
import com.haitrvn.kal.listener.RequestListener
import com.haitrvn.kal.listener.RevenueListener
import com.haitrvn.kal.listener.ReviewListener
import com.haitrvn.kal.listener.RewardedAdListener

expect class RewardedAd(
    adUnitId: String,
    appLovinSdk: AppLovinSdk? = null
) {
    val isReady: Boolean
    val unitId: String

    fun loadAd()
    fun setAdReviewListener(reviewListener: ReviewListener)
    fun setExpirationListener(expirationListener: ExpirationListener)
    fun setExtraParameter(key: String, value: String)
    fun setListener(viewAdListener: RewardedAdListener)
    internal fun setLocalExtraParameter(key: String, param: Any)
    fun setRequestListener(requestListener: RequestListener)
    fun setRevenueListener(revenueListener: RevenueListener)
    fun showAd(rootView: RootView)
    fun showAd(placement: String, rootView: RootView)
    fun showAd(placement: String, customData: String, rootView: RootView)
    internal fun showAd(viewGroup: ViewGroup, lifecycle: Lifecycle, rootView: RootView)
    internal fun showAd(placement: String, viewGroup: ViewGroup, lifecycle: Lifecycle, rootView: RootView)
    internal fun showAd(placement: String, customData: String,viewGroup: ViewGroup, lifecycle: Lifecycle, rootView: RootView)
    fun destroy()
}

expect abstract class ViewGroup