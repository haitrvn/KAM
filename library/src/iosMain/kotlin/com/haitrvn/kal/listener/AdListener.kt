package com.haitrvn.kal.listener

import com.haitrvn.kal.core.AdError
import com.haitrvn.kal.core.Ad

actual interface AdListener {
    actual fun onAdClicked(ad: Ad)
    actual fun onAdDisplayed(ad: Ad)
    actual fun onAdDisplayFailed(
        ad: Ad,
        adError: AdError
    )

    actual fun onAdHidden(ad: Ad)
    actual fun onAdLoaded(ad: Ad)
    actual fun onAdLoadFailed(value: String, adError: AdError)
}