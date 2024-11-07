package com.haitrvn.kal.listener

actual fun interface ReviewListener {
    actual fun onCreativeIdGenerated(value: String, maxAd: MaxAd)
}