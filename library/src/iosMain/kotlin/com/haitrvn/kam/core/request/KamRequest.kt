package com.haitrvn.kam.core.request

import cocoapods.Google_Mobile_Ads_SDK.GADRequest
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
actual typealias KamRequest = GADRequest

actual fun KamRequest(): KamRequest {
    TODO()
}