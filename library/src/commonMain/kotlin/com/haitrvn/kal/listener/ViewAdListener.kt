package com.haitrvn.kal.listener

expect interface ViewAdListener {
    fun onAdExpanded(ad: Ad)
    fun onAdCollapsed(ad: Ad)
}