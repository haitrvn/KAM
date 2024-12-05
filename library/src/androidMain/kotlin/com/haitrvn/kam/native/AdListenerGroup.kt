package com.haitrvn.kam.native

import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.LoadAdError

class AdListenerGroup(
    private val listListener: MutableList<AdListener> = mutableListOf()
) : AdListener() {
    override fun onAdClicked() {
        super.onAdClicked()
        listListener.forEach { it.onAdClicked() }
    }

    override fun onAdClosed() {
        super.onAdClosed()
        listListener.forEach { it.onAdClosed() }
    }

    override fun onAdFailedToLoad(p0: LoadAdError) {
        super.onAdFailedToLoad(p0)
        listListener.forEach { it.onAdFailedToLoad(p0) }
    }

    override fun onAdImpression() {
        super.onAdImpression()
        listListener.forEach { it.onAdImpression() }
    }

    override fun onAdLoaded() {
        super.onAdLoaded()
        listListener.forEach { it.onAdLoaded() }
    }

    override fun onAdOpened() {
        super.onAdOpened()
        listListener.forEach { it.onAdOpened() }
    }

    override fun onAdSwipeGestureClicked() {
        super.onAdSwipeGestureClicked()
        listListener.forEach { it.onAdSwipeGestureClicked() }
    }
}