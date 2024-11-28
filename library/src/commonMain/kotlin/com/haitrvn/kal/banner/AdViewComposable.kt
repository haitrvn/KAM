package com.haitrvn.kal.banner

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AdViewComposable(
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
    PlatformAdView(modifier = modifier, maxAdView = maxAdView)
    DisposableEffect(maxAdView) {
        onDispose {
            maxAdView.destroy()
        }
    }
}