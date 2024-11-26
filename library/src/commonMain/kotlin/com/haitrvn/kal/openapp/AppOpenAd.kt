package com.haitrvn.kal.openapp

import com.haitrvn.kal.initialization.AppLovinSdk

expect class AppOpenAd(
    adUnitId: String,
    appLovinSdk: AppLovinSdk? = null
) {
    val isReady: Boolean
    val unitId: String

    fun loadAd()
    fun setExtraParameter(key: String, value: String)
    internal fun setLocalExtraParameter(key: String, param: Any)
    fun showAd()
    fun showAd(placement: String)
    fun showAd(placement: String, customData: String)
    fun destroy()
}