package com.haitrvn.kal.rewarded

import com.haitrvn.kal.initialization.AppLovinSdk

expect class RewardedAd(
    adUnitId: String,
    appLovinSdk: AppLovinSdk? = null
)