package com.haitrvn.kal.listener

actual interface RewardedAdListener {
    actual fun onUserRewarded(
        ad: Ad,
        reward: Reward
    )
}