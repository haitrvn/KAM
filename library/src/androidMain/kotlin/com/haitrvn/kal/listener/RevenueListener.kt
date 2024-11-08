package com.haitrvn.kal.listener

import com.applovin.mediation.MaxAdRevenueListener
import com.haitrvn.kal.core.Ad

actual fun interface RevenueListener : MaxAdRevenueListener {
    actual override fun onAdRevenuePaid(value: Ad)
}