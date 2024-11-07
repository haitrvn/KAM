package com.haitrvn.kal.listener

expect interface ViewAdListener {
    fun onAdExpanded(maxAd: MaxAd)
    fun onAdCollapsed(maxAd: MaxAd)
}