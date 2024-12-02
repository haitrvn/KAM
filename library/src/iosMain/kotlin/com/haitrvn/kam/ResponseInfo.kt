package com.haitrvn.kam

import cocoapods.Google_Mobile_Ads_SDK.GADResponseInfo
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
actual class ResponseInfo(
    private val ios: GADResponseInfo
) {
    actual val responseExtras: Any
        get() = ios.extrasDictionary
    actual val adapterResponseInfo: AdapterResponseInfo?
        get() = /*ios.loadedAdNetworkResponseInfo*/ TODO()
    actual val mediationAdapterClassName: String?
        get() = /*ios.dictionaryRepresentation*/ TODO()
    actual val responseId: String?
        get() = ios.responseIdentifier
    actual val adapterResponses: List<AdapterResponseInfo>
        get() = /*ios.adNetworkInfoArray*/ TODO()
}