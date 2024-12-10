package com.haitrvn.kam.reward

import cocoapods.Google_Mobile_Ads_SDK.GADAdReward
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
actual data class RewardItem(
    actual val amount: Int,
    actual val type: String,
) {
    constructor(ios: GADAdReward): this (
        amount = ios.amount.intValue,
        type = ios.type
    )
}