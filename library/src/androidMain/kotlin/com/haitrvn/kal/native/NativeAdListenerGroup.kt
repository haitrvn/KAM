package com.haitrvn.kal.native

import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxAdListener
import com.applovin.mediation.MaxError
import com.applovin.mediation.ads.MaxAppOpenAd
import com.applovin.mediation.nativeAds.MaxNativeAdListener
import com.applovin.mediation.nativeAds.MaxNativeAdLoader
import com.applovin.mediation.nativeAds.MaxNativeAdView

class NativeAdListenerGroup(
    loader: MaxNativeAdLoader,
    val listenerList: MutableList<MaxNativeAdListener>
) {
    init {
        loader.setNativeAdListener(object : MaxNativeAdListener() {
            override fun onNativeAdExpired(p0: MaxAd) {
                super.onNativeAdExpired(p0)
                listenerList.forEach { it.onNativeAdExpired(p0) }
            }

            override fun onNativeAdClicked(p0: MaxAd) {
                super.onNativeAdClicked(p0)
                listenerList.forEach { it.onNativeAdClicked(p0) }
            }

            override fun onNativeAdLoadFailed(p0: String, p1: MaxError) {
                super.onNativeAdLoadFailed(p0, p1)
                listenerList.forEach { it.onNativeAdLoadFailed(p0, p1) }
            }

            override fun onNativeAdLoaded(p0: MaxNativeAdView?, p1: MaxAd) {
                super.onNativeAdLoaded(p0, p1)
                listenerList.forEach { it.onNativeAdLoaded(p0, p1) }
            }
        })
    }
}

