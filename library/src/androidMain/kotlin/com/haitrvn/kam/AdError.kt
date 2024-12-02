package com.haitrvn.kam

actual class AdError(
    private val android: com.google.android.gms.ads.AdError
) {
    actual val code: Int
        get() = android.code

    actual val cause: AdError?
        get() = android.cause?.toCommon()

    actual val domain: String
        get() = android.domain

    actual val message: String
        get() = android.message
}