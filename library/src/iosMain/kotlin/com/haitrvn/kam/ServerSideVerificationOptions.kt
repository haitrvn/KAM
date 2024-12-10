package com.haitrvn.kam

import cocoapods.Google_Mobile_Ads_SDK.GADServerSideVerificationOptions
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
actual data class ServerSideVerificationOptions actual constructor(
    actual val customData: String,
    actual val userId: String,
) {
    constructor(android: GADServerSideVerificationOptions) : this(
        customData = android.customData,
        userId = android.userId
    )

    val ios: GADServerSideVerificationOptions by lazy {
        GADServerSideVerificationOptions().apply {
            customRewardString = this@Builder.customData
            userIdentifier = this@Builder.userId
        }
    }

}