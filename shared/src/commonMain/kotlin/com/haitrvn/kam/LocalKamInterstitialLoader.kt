package com.haitrvn.kam

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.staticCompositionLocalOf
import com.haitrvn.kam.core.request.KamRequest
import com.haitrvn.kam.core.request.KamRequestBuilder
import com.haitrvn.kam.interstitial.KamInterstitial
import com.haitrvn.kam.interstitial.KamInterstitialLoader

val LocalKamInterstitialLoader = staticCompositionLocalOf<KamInterstitialLoader?> { null }

@Composable
expect fun ProvideKamInterstitialLoader(content: @Composable () -> Unit)

expect abstract class AppContext

expect fun kamInit(appContext: AppContext)

@Composable
expect fun rememberKamInterstitial(
    adUnitId: String,
    request: KamRequest = KamRequestBuilder.build()
): State<KamInterstitial?>