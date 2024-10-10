package com.haitrvn.kam

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.haitrvn.kam.core.request.KamRequest
import com.haitrvn.kam.interstitial.KamInterstitial
import com.haitrvn.kam.interstitial.KamInterstitialLoader
import kotlinx.cinterop.ExperimentalForeignApi

@Composable
@OptIn(ExperimentalForeignApi::class)
actual fun ProvideKamInterstitialLoader(content: @Composable () -> Unit) {
    val kamInterstitialLoader = remember { KamInterstitialLoader() }
    CompositionLocalProvider(LocalKamInterstitialLoader provides kamInterstitialLoader) {
        content()
    }
}

@Composable
@OptIn(ExperimentalForeignApi::class)
actual fun rememberKamInterstitial(
    adUnitId: String,
    request: KamRequest
): State<KamInterstitial?> {
    val kamInterstitialLoader = LocalKamInterstitialLoader.current
        ?: error("KamInterstitialLoader provided first")
    val kamInterstitialState = remember { mutableStateOf<KamInterstitial?>(null) }
    LaunchedEffect(adUnitId) {
        kamInterstitialState.value = kamInterstitialLoader.load(adUnitId, request)
    }
    return kamInterstitialState
}

actual abstract class AppContext

actual fun kamInit(appContext: AppContext) {
}