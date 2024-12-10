package com.haitrvn.kam

import com.google.android.gms.ads.rewarded.ServerSideVerificationOptions as AndroidServerSideVerificationOptions

actual data class ServerSideVerificationOptions actual constructor(
    actual val customData: String,
    actual val userId: String,
) {
    constructor(android: AndroidServerSideVerificationOptions) : this(
        customData = android.customData,
        userId = android.userId
    )

    val android: AndroidServerSideVerificationOptions by lazy {
        AndroidServerSideVerificationOptions.Builder().apply {
            setCustomData(customData)
            setUserId(userId)
        }.build()
    }
}