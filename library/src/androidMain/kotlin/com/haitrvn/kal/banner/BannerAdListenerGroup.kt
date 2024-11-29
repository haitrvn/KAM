package com.haitrvn.kal.banner

import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxAdViewAdListener
import com.applovin.mediation.MaxError
import com.applovin.mediation.ads.MaxAdView

class BannerAdListenerGroup(
    loader: MaxAdView,
    val listenerList: MutableList<MaxAdViewAdListener>
) {
    init {
        loader.setListener(object : MaxAdViewAdListener {
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

            override fun onAdExpanded(ad: MaxAd) {
                listenerList.forEach { it.onAdExpanded(ad) }
            }

            override fun onAdCollapsed(ad: MaxAd) {
                listenerList.forEach { it.onAdCollapsed(ad) }
            }
        })
    }
}