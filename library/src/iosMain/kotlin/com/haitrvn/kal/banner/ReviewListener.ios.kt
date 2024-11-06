package com.haitrvn.kal.banner

actual interface ReviewListener {
    actual fun onCreativeIdGenerated(value: String, maxAd: MaxAd)
}

actual interface MaxAd
actual interface ViewAdListener {
    actual fun onAdExpanded(maxAd: MaxAd)
    actual fun onAdCollapsed(maxAd: MaxAd)
}

actual interface RequestListener {
    actual fun onAdRequestStarted(value: String)
}

actual interface RevenueListener {
    actual fun onAdRevenuePaid(value: String)
}