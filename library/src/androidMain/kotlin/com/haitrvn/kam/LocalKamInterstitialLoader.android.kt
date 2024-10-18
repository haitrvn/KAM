package com.haitrvn.kam

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.haitrvn.kam.core.request.KamRequest
import com.haitrvn.kam.interstitial.KamInterstitial

@Composable
actual fun rememberKamInterstitial(
    adUnitId: String,
    adRequest: KamRequest
): State<KamInterstitial?> {
    val kamInterstitialState = remember { mutableStateOf<KamInterstitial?>(null) }
    LaunchedEffect(adUnitId) {
        kamInterstitialState.value = KamInterstitial.load(adUnitId, adRequest)
    }
    return kamInterstitialState
}