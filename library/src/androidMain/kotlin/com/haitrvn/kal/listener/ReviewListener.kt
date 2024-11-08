package com.haitrvn.kal.listener

import com.applovin.mediation.MaxAdReviewListener
import com.applovin.mediation.MaxRewardedAdListener

actual interface ReviewListener : MaxAdReviewListener {
    actual fun onUserRewarded(ad: Ad, reward: Reward)
}