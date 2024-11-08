package com.haitrvn.kal.listener

import com.haitrvn.kal.core.Ad

actual interface ViewAdListener {
    actual fun onAdExpanded(ad: Ad)
    actual fun onAdCollapsed(ad: Ad)
}