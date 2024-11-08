package com.haitrvn.kal.listener

import com.haitrvn.kal.core.Ad

expect fun interface ExpirationListener {
    fun onExpiredAdReloaded(ad1: Ad, ad2: Ad)
}