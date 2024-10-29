package com.haitrvn.kal.banner

expect sealed class MaxAdFormat {
    data object BANNER: MaxAdFormat
    data object MREC: MaxAdFormat
    data object LEADER: MaxAdFormat
    data object INTERSTITIAL: MaxAdFormat
    data object APP_OPEN: MaxAdFormat
    data object REWARDED: MaxAdFormat
    data object REWARDED_INTERSTITIAL: MaxAdFormat
    data object NATIVE: MaxAdFormat
}