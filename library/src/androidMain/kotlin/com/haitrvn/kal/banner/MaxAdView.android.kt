package com.haitrvn.kal.banner

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.haitrvn.kal.initialization.AppLovinSdk
import com.haitrvn.kal.util.ContextProvider
import com.applovin.mediation.ads.MaxAdView as AndroidMaxAdView

actual class MaxAdView actual constructor(
    private val adUnitId: String,
    private val maxAdFormat: MaxAdFormat?,
    private val sdk: AppLovinSdk?,
) {
    val maxAdView: AndroidMaxAdView by lazy {
        when {
            maxAdFormat != null && sdk != null -> AndroidMaxAdView(
                adUnitId,
                maxAdFormat.adFormat,
                sdk.androidAppLovinSdk,
                ContextProvider.applicationContext
            )

            maxAdFormat != null && sdk == null -> AndroidMaxAdView(
                adUnitId,
                maxAdFormat.adFormat,
                ContextProvider.applicationContext
            )

            maxAdFormat == null && sdk != null -> AndroidMaxAdView(
                adUnitId,
                sdk.androidAppLovinSdk,
                ContextProvider.applicationContext
            )

            else -> AndroidMaxAdView(
                adUnitId,
                ContextProvider.applicationContext
            )
        }
    }

    actual fun destroy() {
        maxAdView.destroy()
    }

    actual fun getAdFormat(): MaxAdFormat? {
        return maxAdFormat
    }

    actual fun getAdUnitId(): String {
        return adUnitId
    }

    actual fun getPlacement(): String {
        return maxAdView.placement
    }

    actual fun loadAd() {
        maxAdView.loadAd()
    }

    internal actual fun setAdReviewListener(listener: ReviewListener) {
        maxAdView.setAdReviewListener(listener)
    }

    actual fun setAlpha(alpha: Float) {
        maxAdView.alpha = alpha
    }

    actual fun setBackgroundColor() {
    }

    actual fun setCustomData(data: String) {
        maxAdView.setCustomData(data)
    }

    actual fun setExtraParameter(param: String, data: String) {
        maxAdView.setExtraParameter(param, data)
    }

    internal actual fun setListener(listener: ViewAdListener) {
        maxAdView.setListener(listener)
    }

    actual fun setLocalExtraParameter(param: String, data: Any) {
        maxAdView.setLocalExtraParameter(param, data)
    }

    actual fun setPlacement(placement: String) {
        maxAdView.placement = placement
    }

    internal actual fun setRequestListener(listener: RequestListener) {
        maxAdView.setRequestListener(listener)
    }

    internal actual fun setRevenueListener(listener: RevenueListener) {
        maxAdView.setRevenueListener(listener)
    }

    actual fun startAutoRefresh() {
        maxAdView.startAutoRefresh()
    }

    actual fun stopAutoRefresh() {
        maxAdView.stopAutoRefresh()
    }
}

@Composable
actual fun PlatformMaxAdView(
    modifier: Modifier,
    maxAdView: MaxAdView
) {
    AndroidView(
        factory = { maxAdView.maxAdView },
        modifier = modifier
    )
}