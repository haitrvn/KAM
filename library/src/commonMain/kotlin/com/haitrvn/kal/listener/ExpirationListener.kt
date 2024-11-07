package com.haitrvn.kal.listener

expect fun interface ExpirationListener {
    fun onExpiredAdReloaded(maxAd1: MaxAd, maxAd2: MaxAd)
}