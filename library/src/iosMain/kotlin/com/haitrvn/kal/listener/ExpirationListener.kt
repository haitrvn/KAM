package com.haitrvn.kal.listener

import com.haitrvn.kal.core.Ad

actual fun interface ExpirationListener {
    actual fun onExpiredAdReloaded(
        ad1: Ad,
        ad2: Ad
    )
}