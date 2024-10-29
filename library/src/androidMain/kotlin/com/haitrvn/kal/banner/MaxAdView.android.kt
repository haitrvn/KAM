package com.haitrvn.kal.banner

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxAdViewAdListener
import com.applovin.mediation.MaxError
import com.haitrvn.kal.initialization.AppLovinSdk
import com.haitrvn.kal.util.ContextProvider
import com.applovin.mediation.ads.MaxAdView as AndroiMaxAdView

actual class MaxAdView actual constructor(
    private val adUnitId: String,
    private val maxAdFormat: MaxAdFormat?,
    private val sdk: AppLovinSdk?,
) {
    val maxAdView: AndroiMaxAdView by lazy {
        when {
            maxAdFormat != null && sdk != null -> AndroiMaxAdView(
                adUnitId,
                maxAdFormat.adFormat,
                sdk.androidAppLovinSdk,
                ContextProvider.applicationContext
            )

            maxAdFormat != null && sdk == null -> AndroiMaxAdView(
                adUnitId,
                maxAdFormat.adFormat,
                ContextProvider.applicationContext
            )

            maxAdFormat == null && sdk != null -> AndroiMaxAdView(
                adUnitId,
                sdk.androidAppLovinSdk,
                ContextProvider.applicationContext
            )

            else -> AndroiMaxAdView(
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

    internal actual fun setAdReviewListener() {
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

    internal actual fun setListener() {
        maxAdView.setListener(object : MaxAdViewAdListener {
            override fun onAdLoaded(p0: MaxAd) {
                println("haitrvn onAdLoaded $p0")
            }

            override fun onAdDisplayed(p0: MaxAd) {
                println("haitrvn onAdDisplayed $p0")

            }

            override fun onAdHidden(p0: MaxAd) {
                println("haitrvn onAdHidden $p0")

            }

            override fun onAdClicked(p0: MaxAd) {
                println("haitrvn onAdClicked $p0")

            }

            override fun onAdLoadFailed(p0: String, p1: MaxError) {
                println("haitrvn onAdLoadFailed $p0 $p1")

            }

            override fun onAdDisplayFailed(p0: MaxAd, p1: MaxError) {
                println("haitrvn onAdDisplayFailed $p0 $p1")

            }

            override fun onAdExpanded(p0: MaxAd) {
                println("haitrvn onAdExpanded $p0")

            }

            override fun onAdCollapsed(p0: MaxAd) {
                println("haitrvn onAdCollapsed $p0")

            }
        })
    }

    actual fun setLocalExtraParameter(param: String, data: Any) {
    }

    actual fun setPlacement(placement: String) {
        maxAdView.placement = placement
    }

    internal actual fun setRequestListener() {
    }

    internal actual fun setRevenueListener() {
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