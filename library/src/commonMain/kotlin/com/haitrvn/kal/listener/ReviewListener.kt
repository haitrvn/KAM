package com.haitrvn.kal.listener

expect interface ReviewListener {
    fun onUserRewarded(ad: Ad, reward: Reward)
}