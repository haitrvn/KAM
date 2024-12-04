package com.haitrvn.kam

import cocoapods.Google_Mobile_Ads_SDK.GADAdNetworkResponseInfo
import cocoapods.Google_Mobile_Ads_SDK.GADResponseInfo
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
actual class ResponseInfo(
    private val ios: GADResponseInfo
) {
    actual val adapterResponses: List<AdapterResponseInfo>
        get() = ios.adNetworkInfoArray.map { (it as GADAdNetworkResponseInfo).toCommon() }

    actual val loadedAdapterResponseInfo: AdapterResponseInfo?
        get() = ios.loadedAdNetworkResponseInfo?.toCommon()

    actual val mediationAdapterClassName: String?
        get() = TODO()

    actual val responseId: String?
        get() = ios.responseIdentifier

    actual val responseExtras: Any
        get() = ios.extrasDictionary
}