package com.haitrvn.kam.core.init

import cocoapods.Google_Mobile_Ads_SDK.GADMobileAds
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
actual class KamInitializer {
    actual fun initialize(onComplete: () -> Unit) {
        GADMobileAds.sharedInstance().startWithCompletionHandler {
        }
        GADMobileAds.sharedInstance().requestConfiguration
    }

    actual fun disableMediationAdapterInitialization() {
        GADMobileAds.sharedInstance().disableMediationInitialization()
    }

    actual fun setRequestConfiguration(requestConfiguration: RequestConfiguration) {
    }

    internal actual fun startPreload() {
    }

    actual fun setApplicationVolume(volume: Float) {
        GADMobileAds.sharedInstance().setApplicationVolume(volume)
    }

    actual fun getVersion(): String {
        return GADMobileAds.sharedInstance().versionNumber().toString()
    }

    actual fun setMuted(muted: Boolean) {
        GADMobileAds.sharedInstance().setApplicationMuted(muted)
    }
}