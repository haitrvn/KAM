package com.haitrvn.kam

expect class AdValue {
    val precisionType: PrecisionType
    val valueMicros: Long
    val currencyCode: String
}