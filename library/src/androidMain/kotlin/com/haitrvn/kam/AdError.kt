package com.haitrvn.kam

actual class AdError(
    private val android: com.google.android.gms.ads.AdError
) {
    actual val domain: String
        get() = android.domain

    actual val code: Long
        get() = android.code.toLong()

    actual val message: String
        get() = android.message

    actual val cause: AdError?
        get() = android.cause?.toCommon()
}