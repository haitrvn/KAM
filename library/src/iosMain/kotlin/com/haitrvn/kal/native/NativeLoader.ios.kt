package com.haitrvn.kal.native

import com.haitrvn.kal.core.Ad
import com.haitrvn.kal.initialization.AppLovinSdk
import com.haitrvn.kal.model.ReviewAd
import kotlinx.coroutines.flow.Flow

actual class NativeAdView

actual class MAd

actual class NativeLoader actual constructor(
    adUnitId: String,
    appLovinSdk: AppLovinSdk?
) {
    actual val nativeAdEventFlow: Flow<NativeAdEvent>
        get() = TODO("Not yet implemented")

    actual val revenueFlow: Flow<Ad>
        get() = TODO("Not yet implemented")

    actual val reviewFlow: Flow<ReviewAd>
        get() = TODO("Not yet implemented")

    actual suspend fun loadAd(): NativeAdView? {
        TODO("Not yet implemented")
    }

    actual fun destroy(mAd: MAd) {
    }

    actual fun destroy() {
    }

    actual fun setLocalExtraParameter() {
    }

    actual fun setExtraParameter() {
    }

    actual fun setPlacement() {
    }

    actual fun render() {
    }
}