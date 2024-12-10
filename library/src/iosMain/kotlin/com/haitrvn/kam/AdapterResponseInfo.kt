package com.haitrvn.kam

import cocoapods.Google_Mobile_Ads_SDK.GADAdNetworkResponseInfo
import com.haitrvn.kam.until.EMPTY_STRING
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
actual class AdapterResponseInfo(
    private val ios: GADAdNetworkResponseInfo
) {
    actual val adError: AdError?
        get() = ios.error.toCommon()

    actual val adSourceId: String
        get() = ios.adSourceID ?: EMPTY_STRING

    actual val adSourceInstanceId: String
        get() = ios.adSourceInstanceName ?: EMPTY_STRING

    actual val adSourceInstanceName: String
        get() = ios.adSourceInstanceName ?: EMPTY_STRING

    actual val adSourceName: String
        get() = ios.adSourceName ?: EMPTY_STRING

    actual val adapterClassName: String
        get() = ios.adNetworkClassName

    actual val credentials: Any
        get() = ios.adUnitMapping

    actual val latencyInMillis: Long
        get() = ios.latency.toLong()
}

