package com.haitrvn.kal.banner

import com.haitrvn.kal.initialization.AppLovinSdk
import com.haitrvn.kal.listener.RequestListener
import com.haitrvn.kal.listener.RevenueListener
import com.haitrvn.kal.listener.ReviewListener
import com.haitrvn.kal.listener.ViewAdListener

expect class MaxAdView(
    adUnitId: String,
    adFormat: AdFormat? = null,
    sdk: AppLovinSdk? = null,
) {
    fun destroy()
    fun getAdFormat(): AdFormat?
    fun getAdUnitId(): String
    fun getPlacement(): String
    fun loadAd()
    internal fun setAdReviewListener(reviewListener: ReviewListener)
    fun setAlpha(alpha: Float)
    fun setBackgroundColor()
    fun setCustomData(data: String)
    fun setExtraParameter(param: String, data: String)
    internal fun setListener(viewAdListener: ViewAdListener)
    fun setLocalExtraParameter(param: String, data: Any)
    fun setPlacement(placement: String)
    internal fun setRequestListener(requestListener: RequestListener)
    internal fun setRevenueListener(revenueListener: RevenueListener)
    fun startAutoRefresh()
    fun stopAutoRefresh()
}