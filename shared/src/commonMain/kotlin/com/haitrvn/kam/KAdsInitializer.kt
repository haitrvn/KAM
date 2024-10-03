package com.haitrvn.kam

expect class KAdsInitializer {
    fun initialize(onComplete: () -> Unit = {})
}