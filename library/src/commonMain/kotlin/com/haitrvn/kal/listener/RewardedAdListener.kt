package com.haitrvn.kal.listener

expect interface RewardedAdListener {
    fun onUserRewarded(ad: Ad, reward: Reward)
}
