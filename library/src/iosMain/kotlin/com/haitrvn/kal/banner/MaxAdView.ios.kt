package com.haitrvn.kal.banner

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.haitrvn.kal.initialization.AppLovinSdk

actual class MaxAdView actual constructor(
    adUnitId: String,
    maxAdFormat: MaxAdFormat?,
    sdk: AppLovinSdk?
) {

    actual fun destroy() {
    }

    actual fun getAdFormat(): MaxAdFormat? {
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

actual sealed class MaxAdFormat(

) {
    actual data object BANNER : MaxAdFormat()
    actual data object MREC : MaxAdFormat()
    actual data object LEADER : MaxAdFormat()
    actual data object INTERSTITIAL : MaxAdFormat()
    actual data object APP_OPEN : MaxAdFormat()
    actual data object REWARDED : MaxAdFormat()
    actual data object REWARDED_INTERSTITIAL : MaxAdFormat()
    actual data object NATIVE : MaxAdFormat()
}

@Composable
actual fun PlatformMaxAdView(
    modifier: Modifier,
    maxAdView: MaxAdView
) {
}