package com.haitrvn.kal.banner

actual sealed class AdFormat {
    actual data object BANNER : AdFormat()
    actual data object MREC : AdFormat()
    actual data object LEADER : AdFormat()
    actual data object INTERSTITIAL : AdFormat()
    actual data object APP_OPEN : AdFormat()
    actual data object REWARDED : AdFormat()
    actual data object REWARDED_INTERSTITIAL : AdFormat()
    actual data object NATIVE : AdFormat()
}