package com.haitrvn.kam

import cocoapods.Google_Mobile_Ads_SDK.GADResponseInfo
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
actual class ResponseInfo(
    private val ios: GADResponseInfo
) {
    actual val adapterResponses: List<AdapterResponseInfo>
        get() = /*ios.adNetworkInfoArray*/ TODO()

    actual val loadedAdapterResponseInfo: AdapterResponseInfo?
        get() = /*ios.loadedAdNetworkResponseInfo*/ TODO()

    actual val mediationAdapterClassName: String?
        get() = /*ios.adNetworkClassName*/ TODO()

    actual val responseId: String?
        get() = ios.responseIdentifier

    actual val responseExtras: Any
        get() = ios.extrasDictionary
}