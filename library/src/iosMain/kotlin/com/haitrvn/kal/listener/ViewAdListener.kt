package com.haitrvn.kal.listener

actual interface ViewAdListener {
    actual fun onAdExpanded(ad: Ad)
    actual fun onAdCollapsed(ad: Ad)
}