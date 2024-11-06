package com.haitrvn.kal.banner

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.haitrvn.kal.initialization.AppLovinSdk

expect class MaxAdView(
    adUnitId: String,
    maxAdFormat: MaxAdFormat? = null,
    sdk: AppLovinSdk? = null,
) {
    fun destroy()
    fun getAdFormat(): MaxAdFormat?
    fun getAdUnitId(): String
    fun getPlacement(): String
    fun loadAd()
    internal fun setAdReviewListener(listener: ReviewListener)
    fun setAlpha(alpha: Float)
    fun setBackgroundColor()
    fun setCustomData(data: String)
    fun setExtraParameter(param: String, data: String)
    internal fun setListener(listener: ViewAdListener)
    fun setLocalExtraParameter(param: String, data: Any)
    fun setPlacement(placement: String)
    internal fun setRequestListener(listener: RequestListener)
    internal fun setRevenueListener(listener: RevenueListener)
    fun startAutoRefresh()
    fun stopAutoRefresh()
}

@Composable
fun MaxAdViewComposable(
    adUnitId: String,
    maxAdFormat: MaxAdFormat,
) {
    val maxAdView = remember {
        MaxAdView(adUnitId = adUnitId).apply {
            loadAd()
        }
    }
    val modifier = Modifier.then(
        when (maxAdFormat) {
            MaxAdFormat.BANNER -> {
                Modifier.requiredSize(width = 320.dp, height = 50.dp)
            }

            MaxAdFormat.LEADER -> {
                Modifier.requiredSize(width = 728.dp, height = 90.dp)
            }

            MaxAdFormat.MREC -> {
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