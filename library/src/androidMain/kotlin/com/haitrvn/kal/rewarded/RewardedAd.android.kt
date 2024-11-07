package com.haitrvn.kal.rewarded

import com.applovin.mediation.ads.MaxRewardedAd
import com.haitrvn.kal.initialization.AppLovinSdk
import com.haitrvn.kal.util.ContextProvider

actual class RewardedAd actual constructor(
    private val adUnitId: String,
    private val appLovinSdk: AppLovinSdk?
) {
    private val rewardedAd: MaxRewardedAd by lazy {
        if (appLovinSdk != null) {
            MaxRewardedAd.getInstance(
                adUnitId,
                appLovinSdk.androidAppLovinSdk,
                ContextProvider.applicationContext
            )
        } else {
            MaxRewardedAd.getInstance(adUnitId, ContextProvider.applicationContext)
        }
    }

    init {

    }
}