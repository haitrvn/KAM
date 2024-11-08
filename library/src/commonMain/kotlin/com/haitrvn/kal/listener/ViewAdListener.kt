package com.haitrvn.kal.listener

import com.haitrvn.kal.core.Ad

expect interface ViewAdListener {
    fun onAdExpanded(ad: Ad)
    fun onAdCollapsed(ad: Ad)
}