package com.haitrvn.kal.listener

expect fun interface ReviewListener {
    fun onCreativeIdGenerated(value: String, maxAd: MaxAd)
}