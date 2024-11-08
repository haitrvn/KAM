package com.haitrvn.kal.listener

import com.applovin.mediation.MaxAdViewAdListener

actual interface ViewAdListener : MaxAdViewAdListener {
    actual override fun onAdExpanded(ad: Ad)
    actual override fun onAdCollapsed(ad: Ad)
}

