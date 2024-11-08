package com.haitrvn.kal.listener

expect fun interface ExpirationListener {
    fun onExpiredAdReloaded(ad1: Ad, ad2: Ad)
}