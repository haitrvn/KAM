package com.haitrvn.kam.reward

import com.google.android.gms.ads.rewarded.RewardItem

actual class RewardItem(
    private val rewardItem: RewardItem
) {
    companion object {
        val DEFAULT_REWARD = RewardItem(RewardItem.DEFAULT_REWARD)
    }

    val amount: Int
        get() = rewardItem.amount

    val type: String
        get() = rewardItem.type
}