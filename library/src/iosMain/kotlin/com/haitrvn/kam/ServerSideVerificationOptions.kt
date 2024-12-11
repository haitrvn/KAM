package com.haitrvn.kam

import cocoapods.Google_Mobile_Ads_SDK.GADServerSideVerificationOptions
import com.haitrvn.kam.until.EMPTY_STRING
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
actual data class ServerSideVerificationOptions actual constructor(
    actual val customData: String,
    actual val userId: String,
) {
    constructor(ios: GADServerSideVerificationOptions) : this(
        customData = ios.customRewardString ?: EMPTY_STRING,
        userId = ios.userIdentifier ?: EMPTY_STRING
    )

    val ios: GADServerSideVerificationOptions by lazy {
        GADServerSideVerificationOptions().apply {
            customRewardString = customData
            userIdentifier = userId
        }
    }

}