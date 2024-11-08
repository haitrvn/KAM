package com.haitrvn.kal.listener

import com.haitrvn.kal.core.Ad

actual fun interface RevenueListener {
    actual fun onAdRevenuePaid(value: Ad)
}
