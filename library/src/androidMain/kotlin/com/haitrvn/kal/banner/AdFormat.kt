package com.haitrvn.kal.banner

import com.applovin.mediation.MaxAdFormat as AndroidMaxAdFormat

actual sealed class AdFormat(
    val adFormat: AndroidMaxAdFormat
) {
    actual data object BANNER : AdFormat(AndroidMaxAdFormat.BANNER)
    actual data object MREC : AdFormat(AndroidMaxAdFormat.MREC)
    actual data object LEADER : AdFormat(AndroidMaxAdFormat.LEADER)
    actual data object INTERSTITIAL : AdFormat(AndroidMaxAdFormat.INTERSTITIAL)
    actual data object APP_OPEN : AdFormat(AndroidMaxAdFormat.APP_OPEN)
    actual data object REWARDED : AdFormat(AndroidMaxAdFormat.REWARDED)
    actual data object REWARDED_INTERSTITIAL : AdFormat(AndroidMaxAdFormat.REWARDED_INTERSTITIAL)
    actual data object NATIVE : AdFormat(AndroidMaxAdFormat.NATIVE)
}