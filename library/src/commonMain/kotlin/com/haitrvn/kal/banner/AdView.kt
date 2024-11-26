package com.haitrvn.kal.banner

import com.haitrvn.kal.initialization.AppLovinSdk

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
    fun setAlpha(alpha: Float)
    internal fun setBackgroundColor()
    fun setCustomData(data: String)
    fun setExtraParameter(param: String, data: String)
    fun setLocalExtraParameter(param: String, data: Any)
    fun setPlacement(placement: String)
    fun startAutoRefresh()
    fun stopAutoRefresh()
}