package com.haitrvn.kam.core.request

import cocoapods.Google_Mobile_Ads_SDK.GADRequest
import kotlinx.cinterop.ExperimentalForeignApi

@ExperimentalForeignApi
actual object KamRequestBuilder {
    actual fun build(): KamRequest {
         return GADRequest()
    }
}