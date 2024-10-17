package com.haitrvn.kam.interstitial

import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.FullScreenContentCallback
import com.haitrvn.kam.core.callback.KamFullScreenContentCallBack
import com.haitrvn.kam.extension.toKamError

class FullScreenContentCallbackImpl(
    val callback: KamFullScreenContentCallBack
) : FullScreenContentCallback() {
    override fun onAdClicked() {
        super.onAdClicked()
        callback.onAdClicked()
    }

    override fun onAdDismissedFullScreenContent() {
        super.onAdDismissedFullScreenContent()
        callback.onAdDismissedFullScreenContent()
    }

    override fun onAdFailedToShowFullScreenContent(error: AdError) {
        super.onAdFailedToShowFullScreenContent(error)
        callback.onAdFailedToShowFullScreenContent(error.toKamError())
    }

    override fun onAdImpression() {
        super.onAdImpression()
        callback.onAdImpression()
    }

    override fun onAdShowedFullScreenContent() {
        super.onAdShowedFullScreenContent()
        callback.onAdShowedFullScreenContent()
    }
}