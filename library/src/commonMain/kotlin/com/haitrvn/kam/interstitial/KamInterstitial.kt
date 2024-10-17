package com.haitrvn.kam.interstitial

import com.haitrvn.kam.core.RootView
import com.haitrvn.kam.core.callback.KamFullScreenContentCallBack
import com.haitrvn.kam.core.model.KamAdError
import com.haitrvn.kam.core.model.KamAdValue
import com.haitrvn.kam.core.model.ResponseInfo
import com.haitrvn.kam.core.request.KamRequest

expect class KamInterstitial {
    fun show(rootView: RootView)
    fun setFullScreenContentCallback(callback: KamFullScreenContentCallBack)
    fun setOnPaidEventListener(callback: (KamAdValue?) -> Unit)
    internal fun setImmersiveMode(immersive: Boolean)
    internal fun getAdUnitId(): String
    internal fun getFullScreenContentCallback(): KamFullScreenContentCallBack
    internal fun getResponseInfo(): ResponseInfo

    companion object {
        suspend fun load(
            adUnitId: String,
            request: KamRequest,
        ): KamInterstitial?

        suspend fun load(
            adUnitId: String,
            request: KamRequest,
            callback: (KamInterstitial?, KamAdError?) -> Unit,
        )
        internal fun isAdAvailable(adUnitId: String): Boolean
        internal fun pollAd(adUnitId: String): KamInterstitial?
    }
}

