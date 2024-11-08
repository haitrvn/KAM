package com.haitrvn.kal.listener

import com.applovin.mediation.MaxAdReviewListener
import com.haitrvn.kal.core.Ad
import com.haitrvn.kal.core.Reward

actual interface ReviewListener : MaxAdReviewListener {
    actual fun onUserRewarded(ad: Ad, reward: Reward)
}