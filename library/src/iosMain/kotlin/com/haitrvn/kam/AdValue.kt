package com.haitrvn.kam

import cocoapods.Google_Mobile_Ads_SDK.GADAdValue
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
actual class AdValue(
    private val ios: GADAdValue
) {
    actual val precisionType: PrecisionType
        get() = PrecisionType.entries.find { it.value == ios.precision.toInt() }
            ?: PrecisionType.UNKNOWN
    actual val valueMicros: Long
        get() = ios.value.longValue
    actual val currencyCode: String
        get() = ios.currencyCode
}