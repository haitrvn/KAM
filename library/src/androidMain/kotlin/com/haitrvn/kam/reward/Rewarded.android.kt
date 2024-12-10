package com.haitrvn.kam.reward

import com.google.android.gms.ads.rewarded.RewardItem as AndroidRewardItem

actual data class RewardItem(
    actual val amount: Int,
    actual val type: String,
) {
    constructor(android: AndroidRewardItem) : this(
        amount = android.amount,
        type = android.type,
    )
}