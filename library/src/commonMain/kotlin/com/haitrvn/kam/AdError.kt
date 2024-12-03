package com.haitrvn.kam

expect class AdError {
    val code: Long
    val cause: AdError?
    val domain: String
    val message: String
}