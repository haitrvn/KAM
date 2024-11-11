package com.haitrvn.kal.listener

import cocoapods.AppLovinSDK.MAAdReviewDelegateProtocol
import com.haitrvn.kal.core.Ad
import com.haitrvn.kal.core.Reward
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
actual interface ReviewListener: MAAdReviewDelegateProtocol {
    actual fun onUserRewarded(ad: Ad, reward: Reward)
}