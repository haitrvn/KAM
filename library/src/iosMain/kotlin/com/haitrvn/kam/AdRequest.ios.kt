package com.haitrvn.kam

import cocoapods.Google_Mobile_Ads_SDK.GADRequest
import cocoapods.Google_Mobile_Ads_SDK.adString
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
actual class AdRequest(
    val ios: GADRequest
) {
    actual companion object {
        actual fun createInstance(block: AdRequestBuilder.() -> Unit): AdRequest {
            return AdRequest(GADRequest().apply {

            })
        }
    }

    actual val customTargeting: Any
        get() = TODO()
    actual val networkExtrasBundle: Any?
        get() = ios.adNetworkExtrasFor(TODO())
    actual val adString: String?
        get() = ios.adString
    actual val contentUrl: String
        get() = ios.contentURL ?: ""
    actual val requestAgent: String
        get() = ios.requestAgent ?: ""
    actual val neighboringContentUrls: List<String>
        get() = ios.neighboringContentURLStrings as? List<String> ?: emptyList()
    actual val keywords: Set<String>
        get() = (ios.keywords as List<String>).toSet()
    actual val isTestDevice: Boolean
        get() = TODO()
}