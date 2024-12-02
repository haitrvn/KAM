package com.haitrvn.kam

import android.os.Bundle
import com.google.android.gms.ads.mediation.MediationExtrasReceiver

actual class AdRequestBuilder(
    private val android: com.google.android.gms.ads.AdRequest.Builder
) {
    actual fun build(): AdRequest {
        return AdRequest(android.build())
    }

    actual fun addCustomTargeting(key: String, value: String) {
        android.addCustomTargeting(key, value)
    }

    actual fun addCustomTargeting(key: String, values: List<String>) {
        android.addCustomTargeting(key, values)
    }

    actual fun addKeyword(keyword: String) {
        android.addKeyword(keyword)
    }

    actual fun addNetworkExtrasBundle() {
        android.addNetworkExtrasBundle(MediationExtrasReceiver::class.java, Bundle())
    }

    actual fun setAdString(adString: String) {
        android.setAdString(adString)
    }

    actual fun setContentUrl(contentUrl: String) {
        android.setContentUrl(contentUrl)
    }

    actual fun setHttpTimeoutMillis(timeout: Int) {
        android.setHttpTimeoutMillis(timeout)
    }

    actual fun setNeighboringContentUrls(urls: List<String>) {
        android.setNeighboringContentUrls(urls)
    }

    actual fun setRequestAgent(requestAgent: String) {
        android.setRequestAgent(requestAgent)
    }
}