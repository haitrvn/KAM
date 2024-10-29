package com.haitrvn.kal.banner

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.text.BasicText
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    internal fun setAdReviewListener()
    fun setAlpha(alpha: Float)
    fun setBackgroundColor()
    fun setCustomData(data: String)
    fun setExtraParameter(param: String, data: String)
    internal fun setListener()
    fun setLocalExtraParameter(param: String, data: Any)
    fun setPlacement(placement: String)
    internal fun setRequestListener()
    internal fun setRevenueListener()
    fun startAutoRefresh()
    fun stopAutoRefresh()
}

@Composable
fun MaxAdViewComposable(
    adUnitId: String,
    maxAdFormat: MaxAdFormat,
) {
//    val maxAdView = remember {
//        MaxAdView(adUnitId = adUnitId).apply {
//            loadAd()
//        }
//    }
    val modifier = Modifier.then(
        when (maxAdFormat) {
            MaxAdFormat.BANNER -> {
                println("haitv BANNER")
                Modifier.requiredSize(width = 320.dp, height = 50.dp)
            }

            MaxAdFormat.LEADER -> {
                println("haitv LEADER")
                Modifier.requiredSize(width = 728.dp, height = 90.dp)
            }

            MaxAdFormat.MREC -> {
                println("haitv MREC")
                Modifier.requiredSize(width = 300.dp, height = 250.dp)
            }

            else -> {
                println("haitv else")
                Modifier.fillMaxSize()
            }
        }
    )

    BasicText("ABC", modifier = modifier.background(Color.Red))

//    DisposableEffect(maxAdView) {
//        onDispose {
//            maxAdView.destroy()
//        }
//    }
}

@Composable
expect fun PlatformMaxAdView(
    modifier: Modifier,
    maxAdView: MaxAdView
)