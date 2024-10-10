package com.haitrvn.kam.core.init

import cocoapods.Google_Mobile_Ads_SDK.GADMobileAds
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
actual class KamInitializer {
    actual fun initialize(onComplete: () -> Unit) {
        GADMobileAds.sharedInstance().startWithCompletionHandler {
        }
    }

    actual fun disableMediationAdapterInitialization() {
    }

    actual fun setRequestConfiguration(requestConfiguration: RequestConfiguration) {
    }

    internal actual fun startPreload() {
    }
}