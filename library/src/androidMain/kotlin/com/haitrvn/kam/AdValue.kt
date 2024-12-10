package com.haitrvn.kam

import com.google.android.gms.ads.AdValue as AndroidAdValue

actual data class AdValue(
    actual val precisionType: PrecisionType,
    actual val valueMicros: Long,
    actual val currencyCode: String,
) {
    constructor(android: AndroidAdValue) : this(
        precisionType = PrecisionType.entries.find { it.value == android.precisionType }
            ?: PrecisionType.UNKNOWN,
        valueMicros = android.valueMicros,
        currencyCode = android.currencyCode,
    )
}


