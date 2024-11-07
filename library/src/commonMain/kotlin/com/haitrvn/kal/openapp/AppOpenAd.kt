package com.haitrvn.kal.openapp

import com.haitrvn.kal.initialization.AppLovinSdk

expect class AppOpenAd(
    adUnitId: String,
    appLovinSdk: AppLovinSdk? = null
) {
    fun destroy()
    fun getAdUnitId()
    fun isReady()
    fun loadAd()
    fun setAdReviewListener()
    fun setExpirationListener()
    fun setExtraParameter()
    fun setListener()
    fun setLocalExtraParameter()
    fun setRequestListener()
    fun setRevenueListener()
    fun showAd()
}