package com.haitrvn.kam

import com.google.android.gms.ads.interstitial.InterstitialAd
import com.haitrvn.kam.core.RootView

actual class KInterstitial(
    interstitialAd: InterstitialAd
) : InterstitialAdWrapper(interstitialAd) {
    actual fun showAd(activity: RootView) {
        showAd(activity)
    }
}