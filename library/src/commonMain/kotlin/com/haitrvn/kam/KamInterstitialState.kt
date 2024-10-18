package com.haitrvn.kam

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.haitrvn.kam.core.callback.DefaultKamFullScreenContentCallBack
import com.haitrvn.kam.core.model.KamAdError
import com.haitrvn.kam.core.request.KamRequest
import com.haitrvn.kam.interstitial.KamInterstitial

@Composable
fun rememberKamInterstitial(
    adUnitId: String,
    adRequest: KamRequest = KamRequest(),
    reloadEnabled: Boolean = false,
): State<KamInterstitial?> {
    val kamInterstitialState = remember { mutableStateOf<KamInterstitial?>(null) }
    var shouldReload by remember { mutableStateOf(true) }
    LaunchedEffect(shouldReload) {
        kamInterstitialState.value = KamInterstitial.load(adUnitId, adRequest)?.apply {
            setFullScreenContentCallback(object : DefaultKamFullScreenContentCallBack() {
                override fun onAdDismissedFullScreenContent() {
                    kamInterstitialState.value = null
                    if (reloadEnabled) {
                        shouldReload = !shouldReload
                    }
                }

                override fun onAdFailedToShowFullScreenContent(error: KamAdError) {
                    kamInterstitialState.value = null
                    if (reloadEnabled) {
                        shouldReload = !shouldReload
                    }
                }
            })
        }
    }
    return kamInterstitialState
}