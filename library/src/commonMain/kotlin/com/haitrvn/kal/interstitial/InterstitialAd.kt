package com.haitrvn.kal.interstitial

import com.haitrvn.kal.core.RootView
import com.haitrvn.kal.listener.ExpirationListener
import com.haitrvn.kal.listener.RequestListener
import com.haitrvn.kal.listener.RevenueListener
import com.haitrvn.kal.listener.ReviewListener
import com.haitrvn.kal.listener.ViewAdListener
import com.haitrvn.kal.initialization.AppLovinSdk

expect class InterstitialAd(
    adUnitId: String,
    appLovinSdk: AppLovinSdk? = null
) {
    val isReady: Boolean
    val unitId: String

    fun loadAd()
    fun setAdReviewListener(reviewListener: ReviewListener)
    fun setExpirationListener(expirationListener: ExpirationListener)
    fun setExtraParameter(key: String, value: String)
    fun setListener(viewAdListener: ViewAdListener)
    internal fun setLocalExtraParameter(key: String, param: Any)
    fun setRequestListener(requestListener: RequestListener)
    fun setRevenueListener(revenueListener: RevenueListener)
    fun showAd(rootView: RootView)
    fun destroy()
}