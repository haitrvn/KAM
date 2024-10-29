package com.haitrvn.kal.banner

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.haitrvn.kal.initialization.AppLovinSdk

internal actual fun getDefaultAdFormat(): MaxAdFormat {
    TODO("Not yet implemented")
}

actual class MaxAdView actual constructor(
    adUnitId: String,
    maxAdFormat: MaxAdFormat,
    sdk: AppLovinSdk
) {

    actual fun destroy() {
    }

    actual fun getAdFormat(): MaxAdFormat {
        TODO("Not yet implemented")
    }

    actual fun getAdUnitId(): String {
        TODO("Not yet implemented")
    }

    actual fun getPlacement(): String {
        TODO("Not yet implemented")
    }

    actual fun loadAd() {
    }

    internal actual fun setAdReviewListener() {
    }

    actual fun setAlpha(alpha: Float) {
    }

    actual fun setBackgroundColor() {
    }

    actual fun setCustomData(data: String) {
    }

    actual fun setExtraParameter(param: String, data: String) {
    }

    internal actual fun setListener() {
    }

    actual fun setLocalExtraParameter(param: String, data: Any) {
    }

    actual fun setPlacement(placement: String) {
    }

    internal actual fun setRequestListener() {
    }

    internal actual fun setRevenueListener() {
    }

    actual fun startAutoRefresh() {
    }

    actual fun stopAutoRefresh() {
    }
}

actual class MaxAdFormat {
    actual companion object {
        actual val BANNER: MaxAdFormat
            get() = TODO("Not yet implemented")
        actual val MREC: MaxAdFormat
            get() = TODO("Not yet implemented")
        actual val LEADER: MaxAdFormat
            get() = TODO("Not yet implemented")
        actual val INTERSTITIAL: MaxAdFormat
            get() = TODO("Not yet implemented")
        actual val APP_OPEN: MaxAdFormat
            get() = TODO("Not yet implemented")
        actual val REWARDED: MaxAdFormat
            get() = TODO("Not yet implemented")
        actual val REWARDED_INTERSTITIAL: MaxAdFormat
            get() = TODO("Not yet implemented")
        actual val NATIVE: MaxAdFormat
            get() = TODO("Not yet implemented")
    }
}

@Composable
actual fun PlatformMaxAdView(
    modifier: Modifier,
    maxAdView: MaxAdView
) {
}