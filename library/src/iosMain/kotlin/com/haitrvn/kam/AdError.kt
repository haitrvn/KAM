package com.haitrvn.kam

import platform.Foundation.NSError

actual class AdError(
    private val ios: NSError
) {
    actual val code: Int
        get() = ios.code.toInt()
    actual val cause: AdError?
        get() = null
    actual val domain: String
        get() = ios.domain.toString()
    actual val message: String
        get() = ios.description ?: ""
}