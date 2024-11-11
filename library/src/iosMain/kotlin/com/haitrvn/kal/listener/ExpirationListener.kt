package com.haitrvn.kal.listener

import cocoapods.AppLovinSDK.MAAdExpirationDelegateProtocol
import com.haitrvn.kal.core.Ad
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
actual fun interface ExpirationListener : MAAdExpirationDelegateProtocol {
    actual fun onExpiredAdReloaded(
        ad1: Ad,
        ad2: Ad
    )
}