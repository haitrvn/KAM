package com.haitrvn.kam

expect class AdError {
    val code: Int
    val cause: AdError?
    val domain: String
    val message: String
}