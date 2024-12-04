package com.haitrvn.kam.reward

import cocoapods.Google_Mobile_Ads_SDK.GADAdReward
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
actual class RewardItem(
    private val ios: GADAdReward
) {
    actual val amount: Int
        get() = ios.amount.intValue

    actual val type: String
        get() = ios.type
}