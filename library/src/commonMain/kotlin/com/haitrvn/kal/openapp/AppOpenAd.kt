package com.haitrvn.kal.openapp

import com.haitrvn.kal.core.Ad
import com.haitrvn.kal.initialization.AppLovinSdk
import com.haitrvn.kal.model.ReviewAd
import kotlinx.coroutines.flow.Flow

expect class AppOpenAd(
    adUnitId: String,
    appLovinSdk: AppLovinSdk? = null
) {
    val isReady: Boolean
    val unitId: String

    val reviewFlow: Flow<ReviewAd>
    val expirationFlow: Flow<Pair<Ad, Ad>>
    val revenueFlow: Flow<Ad>
    val requestFlow: Flow<String>

    fun loadAd()
    fun setExtraParameter(key: String, value: String)
    internal fun setLocalExtraParameter(key: String, param: Any)
    fun showAd()
    fun showAd(placement: String)
    fun showAd(placement: String, customData: String)
    fun destroy()
}