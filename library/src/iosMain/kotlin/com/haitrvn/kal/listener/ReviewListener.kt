package com.haitrvn.kal.listener

actual interface ReviewListener {
    actual fun onUserRewarded(ad: Ad, reward: Reward)
}