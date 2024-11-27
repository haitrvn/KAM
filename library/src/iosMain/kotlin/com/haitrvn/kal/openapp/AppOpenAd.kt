package com.haitrvn.kal.openapp

import com.haitrvn.kal.core.Ad
import com.haitrvn.kal.initialization.AppLovinSdk
import com.haitrvn.kal.model.ReviewAd
import kotlinx.coroutines.flow.Flow

actual class AppOpenAd actual constructor(
    private val adUnitId: String,
    private val appLovinSdk: AppLovinSdk?
) {
    actual val isReady: Boolean
        get() = TODO("Not yet implemented")
    actual val unitId: String
        get() = TODO("Not yet implemented")

    actual val reviewFlow: Flow<ReviewAd>
        get() = TODO("Not yet implemented")
    actual val expirationFlow: Flow<Pair<Ad, Ad>>
        get() = TODO("Not yet implemented")
    actual val revenueFlow: Flow<Ad>
        get() = TODO("Not yet implemented")
    actual val requestFlow: Flow<String>
        get() = TODO("Not yet implemented")

    actual fun loadAd() {
    }

    actual fun setExtraParameter(key: String, value: String) {
    }


    internal actual fun setLocalExtraParameter(key: String, param: Any) {
    }

    actual fun showAd() {
    }

    actual fun showAd(placement: String) {
    }

    actual fun showAd(placement: String, customData: String) {
    }

    actual fun destroy() {
    }
}