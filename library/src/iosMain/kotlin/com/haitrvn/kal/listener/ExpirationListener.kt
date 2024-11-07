package com.haitrvn.kal.listener

actual fun interface ExpirationListener {
    actual fun onExpiredAdReloaded(
        maxAd1: MaxAd,
        maxAd2: MaxAd
    )
}