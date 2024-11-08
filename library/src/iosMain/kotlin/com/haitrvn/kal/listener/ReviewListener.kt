package com.haitrvn.kal.listener

import com.haitrvn.kal.core.Ad
import com.haitrvn.kal.core.Reward

actual interface ReviewListener {
    actual fun onUserRewarded(ad: Ad, reward: Reward)
}