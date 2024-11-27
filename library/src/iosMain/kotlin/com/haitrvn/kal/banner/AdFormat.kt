package com.haitrvn.kal.banner

import cocoapods.AppLovinSDK.MAAdFormat
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
actual sealed class AdFormat(
    val ios: MAAdFormat
) {
    actual data object BANNER : AdFormat(MAAdFormat.banner)
    actual data object MREC : AdFormat(MAAdFormat.mrec)
    actual data object LEADER : AdFormat(MAAdFormat.leader)
    actual data object INTERSTITIAL : AdFormat(MAAdFormat.interstitial)
    actual data object APP_OPEN : AdFormat(MAAdFormat.appOpen)
    actual data object REWARDED : AdFormat(MAAdFormat.rewarded)
    actual data object REWARDED_INTERSTITIAL : AdFormat(MAAdFormat.rewarded)
    actual data object NATIVE : AdFormat(MAAdFormat.native)
}