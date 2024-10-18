package com.haitrvn.kam

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import com.haitrvn.kam.core.request.KamRequest
import com.haitrvn.kam.interstitial.KamInterstitial

@Composable
expect fun rememberKamInterstitial(
    adUnitId: String,
    adRequest: KamRequest = KamRequest()
): State<KamInterstitial?>