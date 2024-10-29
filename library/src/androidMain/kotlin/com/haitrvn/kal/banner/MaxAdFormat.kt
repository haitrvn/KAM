package com.haitrvn.kal.banner

import com.applovin.mediation.MaxAdFormat as AndroidMaxAdFormat


actual class MaxAdFormat(
    val adFormat: AndroidMaxAdFormat
) {
    actual companion object {
        actual val BANNER: MaxAdFormat
            get() = MaxAdFormat(AndroidMaxAdFormat.BANNER)
        actual val MREC: MaxAdFormat
            get() = MaxAdFormat(AndroidMaxAdFormat.MREC)
        actual val LEADER: MaxAdFormat
            get() = MaxAdFormat(AndroidMaxAdFormat.LEADER)
        actual val INTERSTITIAL: MaxAdFormat
            get() = MaxAdFormat(AndroidMaxAdFormat.INTERSTITIAL)
        actual val APP_OPEN: MaxAdFormat
            get() = MaxAdFormat(AndroidMaxAdFormat.APP_OPEN)
        actual val REWARDED: MaxAdFormat
            get() = MaxAdFormat(AndroidMaxAdFormat.REWARDED)
        actual val REWARDED_INTERSTITIAL: MaxAdFormat
            get() = MaxAdFormat(AndroidMaxAdFormat.REWARDED_INTERSTITIAL)
        actual val NATIVE: MaxAdFormat
            get() = MaxAdFormat(AndroidMaxAdFormat.NATIVE)
    }
}