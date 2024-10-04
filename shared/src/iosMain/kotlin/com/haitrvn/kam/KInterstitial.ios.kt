package com.haitrvn.kam

import com.haitrvn.kam.core.RootView
import cocoapods.Google_Mobile_Ads_SDK.GADInterstitialAd
import kotlinx.cinterop.ExperimentalForeignApi

@ExperimentalForeignApi
actual typealias KInterstitial = GADInterstitialAd

@ExperimentalForeignApi
actual fun KInterstitial.showAd(rootView: RootView) {
    presentFromRootViewController(rootView)
}