package com.haitrvn.kam

import com.haitrvn.kam.core.RootView


actual fun KInterstitial.showAd(rootView: RootView) {
    show(rootView)
}

actual typealias KInterstitial = InterstitialAdWrapper