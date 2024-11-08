package com.haitrvn.kal.listener

import com.applovin.mediation.MaxRewardedAdListener
import com.haitrvn.kal.core.Ad
import com.haitrvn.kal.core.Reward

actual interface RewardedAdListener : MaxRewardedAdListener {
    actual override fun onUserRewarded(ad: Ad, reward: Reward)
}