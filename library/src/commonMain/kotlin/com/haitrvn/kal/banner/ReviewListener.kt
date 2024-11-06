package com.haitrvn.kal.banner

expect interface ReviewListener {
    fun onCreativeIdGenerated(value: String, maxAd: MaxAd)
}

expect interface MaxAd

expect interface ViewAdListener {
    fun onAdExpanded(maxAd: MaxAd)
    fun onAdCollapsed(maxAd: MaxAd)
}

expect interface RequestListener {
    fun onAdRequestStarted(value: String)
}

expect interface RevenueListener {
    fun onAdRevenuePaid(value: String)
}