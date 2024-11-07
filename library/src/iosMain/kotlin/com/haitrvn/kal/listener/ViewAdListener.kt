package com.haitrvn.kal.listener

actual interface ViewAdListener {
    actual fun onAdExpanded(maxAd: MaxAd)
    actual fun onAdCollapsed(maxAd: MaxAd)
}