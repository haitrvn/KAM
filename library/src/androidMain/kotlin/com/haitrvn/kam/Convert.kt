package com.haitrvn.kam

import com.haitrvn.kam.reward.RewardItem

fun com.google.android.gms.ads.AdValue.toCommon() = AdValue(this)

fun com.google.android.gms.ads.ResponseInfo.toCommon() = ResponseInfo(this)

fun com.google.android.gms.ads.AdapterResponseInfo.toCommon() = AdapterResponseInfo(this)

fun List<com.google.android.gms.ads.AdapterResponseInfo>.toCommon(): List<AdapterResponseInfo> {
    return this.map { AdapterResponseInfo(it) }
}

fun com.google.android.gms.ads.AdError.toCommon() = AdError(this)

fun com.google.android.gms.ads.rewarded.RewardItem.toCommon() = RewardItem(this)
