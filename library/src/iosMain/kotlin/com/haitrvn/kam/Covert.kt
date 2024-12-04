package com.haitrvn.kam

import cocoapods.Google_Mobile_Ads_SDK.GADAdNetworkResponseInfo
import cocoapods.Google_Mobile_Ads_SDK.GADAdReward
import cocoapods.Google_Mobile_Ads_SDK.GADResponseInfo
import com.haitrvn.kam.reward.RewardItem
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSError

@OptIn(ExperimentalForeignApi::class)
fun GADResponseInfo.toCommon() = ResponseInfo(this)

@OptIn(ExperimentalForeignApi::class)
fun GADAdReward.toCommon() = RewardItem(this)

@OptIn(ExperimentalForeignApi::class)
fun NSError?.toCommon(): AdError? {
    TODO()
}

private const val NOT_READY = 0
private const val READY = 1

fun Number.toCommon(): AdapterStatus.State {
    return when (this) {
        READY -> AdapterStatus.State.READY
        NOT_READY -> AdapterStatus.State.NOT_READY
        else -> error("Unknown state: $this")
    }
}

@OptIn(ExperimentalForeignApi::class)
fun GADAdNetworkResponseInfo.toCommon() = AdapterResponseInfo(this)
