package com.haitrvn.kal.listener

import com.applovin.mediation.MaxAdReviewListener

actual fun interface ReviewListener : MaxAdReviewListener {
    actual override fun onCreativeIdGenerated(value: String, maxAd: MaxAd)
}