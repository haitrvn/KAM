package com.haitrvn.kal.banner

expect sealed class AdFormat {
    data object BANNER: AdFormat
    data object MREC: AdFormat
    data object LEADER: AdFormat
    data object INTERSTITIAL: AdFormat
    data object APP_OPEN: AdFormat
    data object REWARDED: AdFormat
    data object REWARDED_INTERSTITIAL: AdFormat
    data object NATIVE: AdFormat
}