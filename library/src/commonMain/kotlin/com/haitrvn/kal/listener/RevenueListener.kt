package com.haitrvn.kal.listener

import com.haitrvn.kal.core.Ad

expect fun interface RevenueListener {
    fun onAdRevenuePaid(value: Ad)
}