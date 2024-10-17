package com.haitrvn.kam

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import com.haitrvn.kam.core.init.KamInitializer
import com.haitrvn.kam.core.request.KamRequest
import com.haitrvn.kam.interstitial.KamInterstitial
import com.haitrvn.kam.interstitial.KamInterstitialLoader

@Composable
actual fun ProvideKamInterstitialLoader(content: @Composable () -> Unit) {
    val context = LocalContext.current
    val kamInterstitialLoader = remember { KamInterstitialLoader(context) }
    CompositionLocalProvider(LocalKamInterstitialLoader provides kamInterstitialLoader) {
        content()
    }
}

@Composable
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

actual typealias AppContext = Context

actual fun kamInit(appContext: AppContext) {
    KamInitializer(appContext).initialize()
}