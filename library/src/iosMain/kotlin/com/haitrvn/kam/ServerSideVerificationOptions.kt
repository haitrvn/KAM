package com.haitrvn.kam

import cocoapods.Google_Mobile_Ads_SDK.GADServerSideVerificationOptions
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
actual class ServerSideVerificationOptions(
    val ios: GADServerSideVerificationOptions
) {
    actual companion object {
        actual fun createInstance(block: Builder.() -> Unit): ServerSideVerificationOptions {
            return Builder().apply(block).build()
        }
    }

    actual class Builder {
        private var customData: String = ""
        private var userId: String = ""

        actual fun setCustomData(customData: String): Builder {
            this.customData = customData
            return this
        }

        actual fun setUserId(userId: String): Builder {
            this.userId = userId
            return this
        }

        actual fun build(): ServerSideVerificationOptions {
            return ServerSideVerificationOptions(GADServerSideVerificationOptions().apply {
                customRewardString = this@Builder.customData
                userIdentifier = this@Builder.userId
            })
        }
    }
}