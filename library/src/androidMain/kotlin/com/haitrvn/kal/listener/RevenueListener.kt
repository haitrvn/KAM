package com.haitrvn.kal.listener

import com.applovin.mediation.MaxAdRevenueListener

actual fun interface RevenueListener : MaxAdRevenueListener {
    actual override fun onAdRevenuePaid(value: MaxAd)
}