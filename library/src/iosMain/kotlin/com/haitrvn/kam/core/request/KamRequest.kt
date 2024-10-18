package com.haitrvn.kam.core.request

import cocoapods.Google_Mobile_Ads_SDK.GADRequest
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
actual typealias KamRequest = GADRequest

@OptIn(ExperimentalForeignApi::class)
actual fun KamRequest(): KamRequest {
    TODO()
}