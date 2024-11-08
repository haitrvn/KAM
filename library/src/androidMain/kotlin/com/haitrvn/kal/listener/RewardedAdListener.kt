package com.haitrvn.kal.listener

import com.applovin.mediation.MaxRewardedAdListener

actual interface RewardedAdListener : MaxRewardedAdListener {
    actual override fun onUserRewarded(ad: Ad, reward: Reward)
}