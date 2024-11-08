package com.haitrvn.kal.listener

import com.haitrvn.kal.core.Ad
import com.haitrvn.kal.core.AdError

expect interface AdListener {
    fun onAdClicked(ad: Ad)
    fun onAdDisplayed(ad: Ad)
    fun onAdDisplayFailed(ad: Ad, adError: AdError)
    fun onAdHidden(ad: Ad)
    fun onAdLoaded(ad: Ad)
    fun onAdLoadFailed(value: String, adError: AdError)
}