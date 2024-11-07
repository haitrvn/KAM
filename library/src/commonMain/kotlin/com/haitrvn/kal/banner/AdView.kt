package com.haitrvn.kal.banner

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.haitrvn.kal.initialization.AppLovinSdk
import com.haitrvn.kal.listener.RequestListener
import com.haitrvn.kal.listener.RevenueListener
import com.haitrvn.kal.listener.ReviewListener
import com.haitrvn.kal.listener.ViewAdListener

expect class MaxAdView(
    adUnitId: String,
    adFormat: AdFormat? = null,
    sdk: AppLovinSdk? = null,
) {
    fun destroy()
    fun getAdFormat(): AdFormat?
    fun getAdUnitId(): String
    fun getPlacement(): String
    fun loadAd()
    internal fun setAdReviewListener(reviewListener: ReviewListener)
    fun setAlpha(alpha: Float)
    fun setBackgroundColor()
    fun setCustomData(data: String)
    fun setExtraParameter(param: String, data: String)
    internal fun setListener(viewAdListener: ViewAdListener)
    fun setLocalExtraParameter(param: String, data: Any)
    fun setPlacement(placement: String)
    internal fun setRequestListener(requestListener: RequestListener)
    internal fun setRevenueListener(revenueListener: RevenueListener)
    fun startAutoRefresh()
    fun stopAutoRefresh()
}

@Composable
fun MaxAdViewComposable(
    adUnitId: String,
    adFormat: AdFormat,
    maxAdViewConfig: (MaxAdView) -> Unit
) {
    val maxAdView = remember {
        MaxAdView(adUnitId = adUnitId, adFormat = adFormat)
            .apply(maxAdViewConfig)
            .also { it.loadAd() }
    }
    val modifier = Modifier.then(
        when (adFormat) {
            AdFormat.BANNER -> {
                Modifier.requiredSize(width = 320.dp, height = 50.dp)
            }

            AdFormat.LEADER -> {
                Modifier.requiredSize(width = 728.dp, height = 90.dp)
            }

            AdFormat.MREC -> {
                Modifier.requiredSize(width = 300.dp, height = 250.dp)
            }

            else -> {
                Modifier.fillMaxSize()
            }
        }
    )
    PlatformMaxAdView(modifier = modifier, maxAdView = maxAdView)
    DisposableEffect(maxAdView) {
        onDispose {
            maxAdView.destroy()
        }
    }
}

@Composable
expect fun PlatformMaxAdView(
    modifier: Modifier,
    maxAdView: MaxAdView
)