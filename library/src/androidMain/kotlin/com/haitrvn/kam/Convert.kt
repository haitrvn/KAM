package com.haitrvn.kam

import com.haitrvn.kam.native.VideoOptions
import com.haitrvn.kam.reward.RewardItem
import com.google.android.gms.ads.AdValue as AndroidAdValue
import com.google.android.gms.ads.ResponseInfo as AndroidResponseInfo
import com.google.android.gms.ads.AdapterResponseInfo as AndroidAdapterResponseInfo
import com.google.android.gms.ads.AdError as AndroidError
import com.google.android.gms.ads.rewarded.RewardItem as AndroidRewardItem
import com.google.android.gms.ads.VideoOptions as AndroidVideoOptions

fun AndroidAdValue.toCommon() = AdValue(this)

fun AndroidResponseInfo.toCommon() = ResponseInfo(this)

fun AndroidAdapterResponseInfo.toCommon() = AdapterResponseInfo(this)

fun List<AndroidAdapterResponseInfo>.toCommon(): List<AdapterResponseInfo> {
    return this.map { AdapterResponseInfo(it) }
}

fun AndroidError.toCommon() = AdError(this)

fun AndroidRewardItem.toCommon() = RewardItem(this)


fun AndroidVideoOptions.toCommon() = VideoOptions(this)