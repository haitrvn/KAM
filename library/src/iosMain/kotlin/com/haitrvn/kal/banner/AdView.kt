package com.haitrvn.kal.banner

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.haitrvn.kal.initialization.AppLovinSdk
import com.haitrvn.kal.listener.RequestListener
import com.haitrvn.kal.listener.RevenueListener
import com.haitrvn.kal.listener.ReviewListener
import com.haitrvn.kal.listener.ViewAdListener

actual class MaxAdView actual constructor(
    adUnitId: String,
    adFormat: AdFormat?,
    sdk: AppLovinSdk?
) {

    actual fun destroy() {
    }

    actual fun getAdFormat(): AdFormat? {
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

    internal actual fun setAdReviewListener(reviewListener: ReviewListener) {
    }

    actual fun setAlpha(alpha: Float) {
    }

    actual fun setBackgroundColor() {
    }

    actual fun setCustomData(data: String) {
    }

    actual fun setExtraParameter(param: String, data: String) {
    }

    internal actual fun setListener(viewAdListener: ViewAdListener) {
    }

    actual fun setLocalExtraParameter(param: String, data: Any) {
    }

    actual fun setPlacement(placement: String) {
    }

    internal actual fun setRequestListener(requestListener: RequestListener) {
    }

    internal actual fun setRevenueListener(revenueListener: RevenueListener) {
    }

    actual fun startAutoRefresh() {
    }

    actual fun stopAutoRefresh() {
    }
}

@Composable
actual fun PlatformMaxAdView(
    modifier: Modifier,
    maxAdView: MaxAdView
) {
}