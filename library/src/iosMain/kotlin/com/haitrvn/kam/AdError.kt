package com.haitrvn.kam

import com.haitrvn.kam.until.EMPTY_STRING
import platform.Foundation.NSError
import platform.Foundation.NSUnderlyingErrorKey

actual data class AdError (
    actual val code: Long,
    actual val cause: AdError?,
    actual val domain: String,
    actual val message: String,
) {
    constructor(ios: NSError) : this(
        code = ios.code.toLong(),
        cause = (ios.userInfo[NSUnderlyingErrorKey] as? NSError)?.let { AdError(it) },
        domain = ios.domain ?: EMPTY_STRING,
        message = ios.description ?: EMPTY_STRING,
    )
}