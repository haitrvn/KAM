package com.haitrvn.kal.listener

import com.applovin.mediation.MaxAdExpirationListener
import com.haitrvn.kal.core.Ad

actual fun interface ExpirationListener : MaxAdExpirationListener {
    actual override fun onExpiredAdReloaded(
        ad1: Ad,
        ad2: Ad
    )
}