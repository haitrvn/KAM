package com.haitrvn.kam.interstitial

import com.haitrvn.kam.core.model.KamAdValue
import com.haitrvn.kam.core.callback.KamFullScreenContentCallBack
import com.haitrvn.kam.core.RootView

expect class KamInterstitial {
    fun showAd(rootView: RootView)
    fun setFullScreenContentCallback(callback: KamFullScreenContentCallBack)
    fun setOnPaidEventListener(callback: (KamAdValue?) -> Unit)
    internal fun setImmersiveMode(immersive: Boolean)
}