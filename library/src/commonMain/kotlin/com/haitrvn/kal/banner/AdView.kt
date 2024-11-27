package com.haitrvn.kal.banner

import com.haitrvn.kal.core.Ad
import com.haitrvn.kal.initialization.AppLovinSdk
import com.haitrvn.kal.model.AdEvent
import com.haitrvn.kal.model.ReviewAd
import kotlinx.coroutines.flow.Flow

expect class MaxAdView(
    adUnitId: String,
    adFormat: AdFormat? = null,
    sdk: AppLovinSdk? = null,
) {
    val reviewFlow: Flow<ReviewAd>
    val revenueFlow: Flow<Ad>
    val requestFlow: Flow<String>
    val adEventFlow: Flow<AdEvent>

    fun destroy()
    fun getAdFormat(): AdFormat?
    fun getAdUnitId(): String
    fun getPlacement(): String?
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