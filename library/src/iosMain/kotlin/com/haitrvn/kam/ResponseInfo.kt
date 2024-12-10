package com.haitrvn.kam

import cocoapods.Google_Mobile_Ads_SDK.GADAdNetworkResponseInfo
import cocoapods.Google_Mobile_Ads_SDK.GADResponseInfo
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
actual data class ResponseInfo (
    actual val adapterResponses: List<AdapterResponseInfo>,
    actual val loadedAdapterResponseInfo: AdapterResponseInfo?,
    actual val mediationAdapterClassName: String?,
    actual val responseId: String?,
    actual val responseExtras: Any,
) {

    constructor(ios: GADResponseInfo) : this(
        adapterResponses = ios.adNetworkInfoArray.map { (it as GADAdNetworkResponseInfo).toCommon() },
        loadedAdapterResponseInfo = ios.loadedAdNetworkResponseInfo?.toCommon(),
        mediationAdapterClassName = TODO(),
        responseId = ios.responseIdentifier,
        responseExtras = ios.extrasDictionary,
    )
}