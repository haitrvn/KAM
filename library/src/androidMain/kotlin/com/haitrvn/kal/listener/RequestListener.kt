package com.haitrvn.kal.listener

import com.applovin.mediation.MaxAdRequestListener

actual fun interface RequestListener : MaxAdRequestListener {
    actual override fun onAdRequestStarted(value: String)
}