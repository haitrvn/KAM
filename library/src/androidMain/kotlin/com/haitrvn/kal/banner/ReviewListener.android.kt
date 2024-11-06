package com.haitrvn.kal.banner

import com.applovin.mediation.MaxAdRequestListener
import com.applovin.mediation.MaxAdRevenueListener
import com.applovin.mediation.MaxAdReviewListener
import com.applovin.mediation.MaxAdViewAdListener

actual interface ReviewListener : MaxAdReviewListener {
    actual override fun onCreativeIdGenerated(value: String, maxAd: MaxAd)
}

actual typealias MaxAd = com.applovin.mediation.MaxAd

actual interface ViewAdListener : MaxAdViewAdListener {
    actual override fun onAdExpanded(maxAd: MaxAd)
    actual override fun onAdCollapsed(maxAd: MaxAd)
}

actual interface RequestListener : MaxAdRequestListener {
    actual override fun onAdRequestStarted(value: String)
}

actual interface RevenueListener : MaxAdRevenueListener {
    actual fun onAdRevenuePaid(value: String)
}