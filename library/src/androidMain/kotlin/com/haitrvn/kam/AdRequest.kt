package com.haitrvn.kam

import com.google.android.gms.ads.mediation.MediationExtrasReceiver
import com.haitrvn.kam.util.ContextProvider

actual class AdRequest internal constructor(
    val android: com.google.android.gms.ads.AdRequest
) {
    actual companion object {
        actual fun createInstance(block: AdRequestBuilder.() -> Unit): AdRequest {
            val builder = AdRequestBuilder(com.google.android.gms.ads.AdRequest.Builder())
            builder.block()
            return builder.build()
        }
    }

    actual val customTargeting: Any
        get() = android.customTargeting

    actual val networkExtrasBundle: Any?
        get() = android.getNetworkExtrasBundle(MediationExtrasReceiver::class.java)

    actual val adString: String?
        get() = android.adString

    actual val contentUrl: String
        get() = android.contentUrl

    actual val requestAgent: String
        get() = android.requestAgent

    actual val neighboringContentUrls: List<String>
        get() = android.neighboringContentUrls

    actual val keywords: Set<String>
        get() = android.keywords

    actual val isTestDevice: Boolean
        get() = android.isTestDevice(ContextProvider.context)
}