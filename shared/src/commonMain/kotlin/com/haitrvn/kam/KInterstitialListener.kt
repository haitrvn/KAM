package com.haitrvn.kam

interface KInterstitialListener {
    fun onInterstitialLoaded(interstitial: KInterstitial)
    fun onInterstitialFailedToLoad()
}