package com.haitrvn.kal.listener

import com.applovin.mediation.MaxAdViewAdListener

actual interface ViewAdListener : MaxAdViewAdListener {
    actual override fun onAdExpanded(maxAd: MaxAd)
    actual override fun onAdCollapsed(maxAd: MaxAd)
}