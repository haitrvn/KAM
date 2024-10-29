package com.haitrvn.kal.banner

import com.applovin.mediation.MaxAdFormat as AndroidMaxAdFormat

actual sealed class MaxAdFormat(
    val adFormat: AndroidMaxAdFormat
) {
    actual data object BANNER : MaxAdFormat(AndroidMaxAdFormat.BANNER)
    actual data object MREC : MaxAdFormat(AndroidMaxAdFormat.MREC)
    actual data object LEADER : MaxAdFormat(AndroidMaxAdFormat.LEADER)
    actual data object INTERSTITIAL : MaxAdFormat(AndroidMaxAdFormat.INTERSTITIAL)
    actual data object APP_OPEN : MaxAdFormat(AndroidMaxAdFormat.APP_OPEN)
    actual data object REWARDED : MaxAdFormat(AndroidMaxAdFormat.REWARDED)
    actual data object REWARDED_INTERSTITIAL : MaxAdFormat(AndroidMaxAdFormat.REWARDED_INTERSTITIAL)
    actual data object NATIVE : MaxAdFormat(AndroidMaxAdFormat.NATIVE)
}