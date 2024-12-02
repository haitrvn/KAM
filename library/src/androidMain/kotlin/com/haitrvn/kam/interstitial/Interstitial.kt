package com.haitrvn.kam.interstitial

import androidx.compose.runtime.Composable
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.haitrvn.kam.AdError
import com.haitrvn.kam.AdRequest
import com.haitrvn.kam.AdValue
import com.haitrvn.kam.FullScreenContent
import com.haitrvn.kam.ResponseInfo
import com.haitrvn.kam.RootView
import com.haitrvn.kam.getRootView
import com.haitrvn.kam.toCommon
import com.haitrvn.kam.util.ContextProvider
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

actual class Interstitial(
    val android: InterstitialAd
) {
    actual companion object {
        actual suspend fun load(unitId: String, adRequest: AdRequest): Interstitial? {
            return suspendCancellableCoroutine { continuation ->
                InterstitialAd.load(
                    ContextProvider.context,
                    unitId,
                    adRequest.android,
                    object : InterstitialAdLoadCallback() {
                        override fun onAdFailedToLoad(p0: LoadAdError) {
                            super.onAdFailedToLoad(p0)
                            continuation.resume(null)
                        }

                        override fun onAdLoaded(p0: InterstitialAd) {
                            super.onAdLoaded(p0)
                            continuation.resume(Interstitial(p0))
                        }
                    })
            }
        }

        actual fun pollAd(unitId: String): Interstitial? =
            InterstitialAd.pollAd(ContextProvider.context, unitId)?.let { Interstitial(it) }

        actual fun isAvailable(unitId: String): Boolean =
            InterstitialAd.isAdAvailable(ContextProvider.context, unitId)
    }

    actual val unitId: String
        get() = android.adUnitId

    actual val responseInfo: ResponseInfo
        get() = android.responseInfo.toCommon()

    actual val fullScreenContentFlow: Flow<FullScreenContent>
        get() = callbackFlow {
            android.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdClicked() {
                    super.onAdClicked()
                    trySend(FullScreenContent.Clicked)
                    println("haitrvn onAdClicked")
                }

                override fun onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent()
                    trySend(FullScreenContent.Dismissed)
                    println("haitrvn onAdDismissedFullScreenContent")
                }

                override fun onAdFailedToShowFullScreenContent(p0: com.google.android.gms.ads.AdError) {
                    super.onAdFailedToShowFullScreenContent(p0)
                    trySend(FullScreenContent.ShowFailed(AdError(p0)))
                    println("haitrvn onAdFailedToShowFullScreenContent")
                }

                override fun onAdImpression() {
                    super.onAdImpression()
                    trySend(FullScreenContent.Impression)
                    println("haitrvn onAdImpression")
                }

                override fun onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent()
                    trySend(FullScreenContent.Showed)
                    println("haitrvn onAdShowedFullScreenContent")
                }
            }
            awaitClose {
                android.fullScreenContentCallback = null
                println("haitrvn close flow")
            }
        }

    actual val paidEventFlow: Flow<AdValue>
        get() = callbackFlow {
            android.setOnPaidEventListener {
                trySend(it.toCommon())
            }
            awaitClose { android.onPaidEventListener = null }
        }

    actual fun setImmersiveMode(immersive: Boolean) {
        android.setImmersiveMode(immersive)
    }

    @Composable
    actual fun show() {
        android.show(getRootView())
    }

    actual fun show(rootView: RootView) {
        android.show(rootView)
    }
}