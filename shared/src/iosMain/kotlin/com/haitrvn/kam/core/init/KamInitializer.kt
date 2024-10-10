package com.haitrvn.kam.core.init

import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
actual class KamInitializer {
    actual fun initialize(onComplete: () -> Unit) {
        GADMobileAds.sharedInstance().startWithCompletionHandler {
        }
    }
}