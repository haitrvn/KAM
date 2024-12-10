package com.haitrvn.kam

import com.google.android.gms.ads.AdError as AndroidError

actual data class AdError(
    actual val code: Long,
    actual val cause: AdError?,
    actual val domain: String,
    actual val message: String,
) {
    constructor(android: AndroidError) : this(
        code = android.code.toLong(),
        cause = android.cause?.toCommon(),
        domain = android.domain,
        message = android.message,
    )
}