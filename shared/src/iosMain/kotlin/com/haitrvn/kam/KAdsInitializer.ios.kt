package com.haitrvn.kam

import cocoapods.Google_Mobile_Ads_SDK.GADMobileAds
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
actual class KAdsInitializer {
    actual fun initialize(onComplete: () -> Unit) {
        GADMobileAds.sharedInstance().startWithCompletionHandler {
        }
    }
}
