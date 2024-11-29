package com.haitrvn.kal.openapp

import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxAdListener
import com.applovin.mediation.MaxError
import com.applovin.mediation.ads.MaxAppOpenAd

class OpenAdListenerGroup(
    loader: MaxAppOpenAd,
    val listenerList: MutableList<MaxAdListener>
) {
    init {
        loader.setListener(object : MaxAdListener {
            override fun onAdLoaded(ad: MaxAd) {
                listenerList.forEach { it.onAdLoaded(ad) }
            }

            override fun onAdDisplayed(ad: MaxAd) {
                listenerList.forEach { it.onAdDisplayed(ad) }

            }

            override fun onAdHidden(ad: MaxAd) {
                listenerList.forEach { it.onAdHidden(ad) }

            }

            override fun onAdClicked(ad: MaxAd) {
                listenerList.forEach { it.onAdClicked(ad) }
            }

            override fun onAdLoadFailed(message: String, error: MaxError) {
                listenerList.forEach { it.onAdLoadFailed(message, error) }
            }

            override fun onAdDisplayFailed(ad: MaxAd, error: MaxError) {
                listenerList.forEach { it.onAdDisplayFailed(ad, error) }
            }
        })
    }
}