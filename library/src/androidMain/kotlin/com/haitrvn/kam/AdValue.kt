package com.haitrvn.kam


actual class AdValue(
    private val android: com.google.android.gms.ads.AdValue
) {
    actual val precisionType: PrecisionType
        get() = PrecisionType.entries.find { it.value == android.precisionType }
            ?: PrecisionType.UNKNOWN
    actual val valueMicros: Long
        get() = android.valueMicros
    actual val currencyCode: String
        get() = android.currencyCode
}


