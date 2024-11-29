package com.haitrvn.kal.rewarded

import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxError
import com.applovin.mediation.MaxReward
import com.applovin.mediation.MaxRewardedAdListener
import com.applovin.mediation.ads.MaxRewardedAd

class RewardAdListenerGroup(
    loader: MaxRewardedAd,
    val listenerList: MutableList<MaxRewardedAdListener>
) {
    init {
        loader.setListener(object : MaxRewardedAdListener {
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

            override fun onUserRewarded(p0: MaxAd, p1: MaxReward) {
                listenerList.forEach { it.onUserRewarded(p0, p1) }
            }
        })
    }
}