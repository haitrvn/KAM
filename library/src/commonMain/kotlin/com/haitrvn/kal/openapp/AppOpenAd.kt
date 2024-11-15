package com.haitrvn.kal.openapp

import com.haitrvn.kal.initialization.AppLovinSdk

expect class AppOpenAd(
    adUnitId: String,
    appLovinSdk: AppLovinSdk? = null
) {
    val isReady: Boolean
    val unitId: String

    fun loadAd()
    fun setAdReviewListener(reviewListener: ReviewListener)
    fun setExpirationListener(expirationListener: ExpirationListener)
    fun setExtraParameter(key: String, value: String)
    fun setListener(viewAdListener: AdListener)
    internal fun setLocalExtraParameter(key: String, param: Any)
    fun setRequestListener(requestListener: RequestListener)
    fun setRevenueListener(revenueListener: RevenueListener)
    fun showAd()
    fun showAd(placement: String)
    fun showAd(placement: String, customData: String)
    fun destroy()
}