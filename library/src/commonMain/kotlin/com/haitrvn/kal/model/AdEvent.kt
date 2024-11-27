package com.haitrvn.kal.model

import com.haitrvn.kal.core.Ad
import com.haitrvn.kal.core.AdError
import com.haitrvn.kal.core.Reward

sealed interface AdEvent {
    data class Loaded(val ad: Ad) : AdEvent
    data class Displayed(val ad: Ad) : AdEvent
    data class Hidden(val ad: Ad) : AdEvent
    data class Clicked(val ad: Ad) : AdEvent
    data class LoadFailed(val message: String, val error: AdError) : AdEvent
    data class DisplayFailed(val ad: Ad, val error: AdError) : AdEvent
    data class Expanded(val ad: Ad) : AdEvent
    data class Collapsed(val ad: Ad) : AdEvent
    data class UserRewarded(val ad: Ad, val reward: Reward) : AdEvent
}