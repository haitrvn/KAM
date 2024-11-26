package com.haitrvn.kal.model

import com.haitrvn.kal.core.Ad
import com.haitrvn.kal.core.AdError
import com.haitrvn.kal.core.Reward

sealed interface MaxRewarded {
    data class Loaded(val ad: Ad) : MaxRewarded
    data class Displayed(val ad: Ad) : MaxRewarded
    data class Hidden(val ad: Ad) : MaxRewarded
    data class Clicked(val ad: Ad) : MaxRewarded
    data class LoadFailed(val message: String, val error: AdError) : MaxRewarded
    data class DisplayFailed(val ad: Ad, val error: AdError) : MaxRewarded
    data class UserRewarded(val ad: Ad, val reward: Reward) : MaxRewarded
}

data class ReviewAd(
    val id: String,
    val ad: Ad,
)