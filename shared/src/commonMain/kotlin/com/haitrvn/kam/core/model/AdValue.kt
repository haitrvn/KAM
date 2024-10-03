package com.haitrvn.kam.core.model

data class KamAdValue(
    val precisionType: PrecisionType,
    val valueMicros: Long,
    val currencyCode: String,
)

enum class PrecisionType(val type: Int) {
    UNKNOWN(0),
    ESTIMATED(1),
    PUBLISHER_PROVIDED(2),
    PRECISE(3);
}