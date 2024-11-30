package com.haitrvn.kal.interstitial

import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxAdListener
import com.applovin.mediation.MaxError
import com.applovin.mediation.ads.MaxInterstitialAd

class NativeAdListenerGroup(
    loader: MaxInterstitialAd,
    val listenerList: MutableList<MaxAdListener>
) {
    init {
        loader.setListener(object : MaxAdListener {
            override fun onAdLoaded(p0: MaxAd) {
                listenerList.forEach { it.onAdLoaded(p0) }
            }

            override fun onAdDisplayed(p0: MaxAd) {
                listenerList.forEach { it.onAdDisplayed(p0) }
            }

            override fun onAdHidden(p0: MaxAd) {
                listenerList.forEach { it.onAdHidden(p0) }
            }

            override fun onAdClicked(p0: MaxAd) {
                listenerList.forEach { it.onAdClicked(p0) }
            }

            override fun onAdLoadFailed(p0: String, p1: MaxError) {
                listenerList.forEach { it.onAdLoadFailed(p0, p1) }
            }

            override fun onAdDisplayFailed(p0: MaxAd, p1: MaxError) {
                listenerList.forEach { it.onAdDisplayFailed(p0, p1) }
            }
        })
    }
}

