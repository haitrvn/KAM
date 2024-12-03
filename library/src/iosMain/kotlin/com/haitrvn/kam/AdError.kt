package com.haitrvn.kam

import platform.Foundation.NSError
import platform.Foundation.NSUnderlyingErrorKey

actual class AdError(
    private val ios: NSError
) {
    actual val domain: String
        get() = ios.domain ?: EMPTY_STRING

    actual val code: Long
        get() = ios.code

    actual val message: String
        get() = ios.description ?: EMPTY_STRING

    actual val cause: AdError?
        get() = (ios.userInfo[NSUnderlyingErrorKey] as? NSError)?.let { AdError(it) }
}