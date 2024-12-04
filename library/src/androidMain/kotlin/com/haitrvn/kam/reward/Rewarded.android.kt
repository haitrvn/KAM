package com.haitrvn.kam.reward

import com.google.android.gms.ads.rewarded.RewardItem

actual class RewardItem(
    private val rewardItem: RewardItem
) {
    actual val amount: Int
        get() = rewardItem.amount

    actual val type: String
        get() = rewardItem.type
}