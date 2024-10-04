package com.haitrvn.kam.core

import kotlinx.cinterop.ExperimentalForeignApi
import cocoapods.Google_Mobile_Ads_SDK.GADRequest

@ExperimentalForeignApi
actual object KadRequestBuilder {
    actual fun build(): KAdRequest {
         return GADRequest()
    }
}