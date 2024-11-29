package com.haitrvn.kal.native

import com.haitrvn.kal.core.Ad
import com.haitrvn.kal.core.AdError
import com.haitrvn.kal.initialization.AppLovinSdk
import com.haitrvn.kal.model.ReviewAd
import kotlinx.coroutines.flow.Flow

expect class NativeLoader(
    adUnitId: String,
    appLovinSdk: AppLovinSdk? = null
) {
    val unitId: String
    val nativeAdEventFlow: Flow<NativeAdEvent>
    val revenueFlow: Flow<Ad>
    val reviewFlow: Flow<ReviewAd>

    fun setLocalExtraParameter(key: String, value: String)
    fun setExtraParameter(key: String, value: String)
    fun setPlacement(placement: String)
    fun render(adView: NativeAdView, ad: MAd): Boolean
    suspend fun loadAd(): NativeAdView?
    suspend fun loadAd(ad: NativeAdView): NativeAdView?
    fun destroy(mAd: MAd)
    fun destroy()
}

expect class NativeAdView
expect class MAd

sealed interface NativeAdEvent {
    data class Loaded(val nativeAdView: NativeAdView?, val ad: MAd): NativeAdEvent
    data class LoadFailed(val adUnitId: String, val error: AdError): NativeAdEvent
    data class Clicked(val ad: MAd): NativeAdEvent
    data class Expired(val ad: MAd): NativeAdEvent
}