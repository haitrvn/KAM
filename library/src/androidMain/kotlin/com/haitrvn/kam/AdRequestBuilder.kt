package com.haitrvn.kam

import android.os.Bundle
import com.google.android.gms.ads.mediation.MediationExtrasReceiver
import com.google.android.gms.ads.AdRequest as AndroidAdRequest
actual class AdRequestBuilder(
    private val android: AndroidAdRequest.Builder
) {
    actual fun build(): AdRequest {
        return AdRequest(android.build())
    }

    actual fun addCustomTargeting(key: String, value: String): AdRequestBuilder {
        android.addCustomTargeting(key, value)
        return this
    }

    actual fun addCustomTargeting(key: String, values: List<String>): AdRequestBuilder {
        android.addCustomTargeting(key, values)
        return this
    }

    actual fun addKeyword(keyword: String): AdRequestBuilder {
        android.addKeyword(keyword)
        return this
    }

    actual fun addNetworkExtrasBundle(): AdRequestBuilder {
        android.addNetworkExtrasBundle(MediationExtrasReceiver::class.java, Bundle())
        return this
    }

    actual fun setAdString(adString: String): AdRequestBuilder {
        android.setAdString(adString)
        return this
    }

    actual fun setContentUrl(contentUrl: String): AdRequestBuilder {
        android.setContentUrl(contentUrl)
        return this
    }

    actual fun setHttpTimeoutMillis(timeout: Int): AdRequestBuilder {
        android.setHttpTimeoutMillis(timeout)
        return this
    }

    actual fun setNeighboringContentUrls(urls: List<String>): AdRequestBuilder {
        android.setNeighboringContentUrls(urls)
        return this
    }

    actual fun setRequestAgent(requestAgent: String): AdRequestBuilder {
        android.setRequestAgent(requestAgent)
        return this
    }
}