package com.haitrvn.kam.core.model

data class KamAdError(
    val code: Int,
    val cause: String,
    val domain: String,
    val message: String,
)