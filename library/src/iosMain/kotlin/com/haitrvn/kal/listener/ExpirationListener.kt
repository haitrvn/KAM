package com.haitrvn.kal.listener

actual fun interface ExpirationListener {
    actual fun onExpiredAdReloaded(
        ad1: Ad,
        ad2: Ad
    )
}