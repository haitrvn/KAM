package com.haitrvn.kal.listener

import com.applovin.mediation.MaxAdExpirationListener

actual fun interface ExpirationListener : MaxAdExpirationListener {
    actual override fun onExpiredAdReloaded(
        maxAd1: MaxAd,
        maxAd2: MaxAd
    )
}