package com.haitrvn.kal.listener

import com.haitrvn.kal.core.Ad
import com.haitrvn.kal.core.Reward

expect interface ReviewListener {
    fun onUserRewarded(ad: Ad, reward: Reward)
}