package com.haitrvn.kam

import cocoapods.Google_Mobile_Ads_SDK.GADAdValue
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
actual data class AdValue(
    actual val precisionType: PrecisionType,
    actual val valueMicros: Long,
    actual val currencyCode: String,
) {
    constructor(ios: GADAdValue): this(
        precisionType = PrecisionType.entries.find { it.value == ios.precision.toInt() }
            ?: PrecisionType.UNKNOWN,
        valueMicros = ios.value.longValue,
        currencyCode = ios.currencyCode,
    )
}