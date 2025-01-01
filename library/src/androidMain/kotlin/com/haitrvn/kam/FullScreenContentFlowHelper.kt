package com.haitrvn.kam

import com.google.android.gms.ads.FullScreenContentCallback
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import com.google.android.gms.ads.AdError as AndroidAdError

fun fullScreenContentFlow(callback: (FullScreenContentCallback) -> Unit, onClose: () -> Unit) =
    callbackFlow {
        callback(object : FullScreenContentCallback() {
            override fun onAdClicked() {
                super.onAdClicked()
                trySend(FullScreenContent.Clicked)
            }

            override fun onAdDismissedFullScreenContent() {
                super.onAdDismissedFullScreenContent()
                trySend(FullScreenContent.Dismissed)
            }

            override fun onAdFailedToShowFullScreenContent(p0: AndroidAdError) {
                super.onAdFailedToShowFullScreenContent(p0)
                trySend(FullScreenContent.ShowFailed(AdError(p0)))
            }

            override fun onAdImpression() {
                super.onAdImpression()
                trySend(FullScreenContent.Impression)
            }

            override fun onAdShowedFullScreenContent() {
                super.onAdShowedFullScreenContent()
                trySend(FullScreenContent.Showed)
            }
        })
        awaitClose {
            onClose()
        }
    }