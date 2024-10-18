package com.haitrvn.kam.core.callback

import com.haitrvn.kam.core.model.KamAdError

interface KamFullScreenContentCallBack {
    fun onAdClicked()
    fun onAdDismissedFullScreenContent()
    fun onAdFailedToShowFullScreenContent(error: KamAdError)
    fun onAdImpression()
    fun onAdShowedFullScreenContent()
}

open class DefaultKamFullScreenContentCallBack : KamFullScreenContentCallBack {
    override fun onAdClicked() {
    }

    override fun onAdDismissedFullScreenContent() {
    }

    override fun onAdFailedToShowFullScreenContent(error: KamAdError) {
    }

    override fun onAdImpression() {
    }

    override fun onAdShowedFullScreenContent() {
    }
}