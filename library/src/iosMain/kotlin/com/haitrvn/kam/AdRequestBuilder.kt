package com.haitrvn.kam

import cocoapods.Google_Mobile_Ads_SDK.GADRequest

actual class AdRequestBuilder {
    private var keywords: MutableList<String> = mutableListOf()
    private var customTargeting: MutableMap<String, String> = mutableMapOf()
    private var adString: String? = null
    private var contentUrl: String? = null
    private var httpTimeoutMillis: Int? = null
    private var neighboringContentUrls: List<String> = listOf()
    private var requestAgent: String? = null

    actual fun build(): AdRequest {
        val request = GADRequest().apply {
            this.keywords = this@AdRequestBuilder.keywords
            this.customTargeting = this@AdRequestBuilder.customTargeting
            this.adString = this@AdRequestBuilder.adString
            this.contentUrl = this@AdRequestBuilder.contentUrl
            this.httpTimeoutMillis = this@AdRequestBuilder.httpTimeoutMillis
            this.neighboringContentUrls = this@AdRequestBuilder.neighboringContentUrls
            this.requestAgent = this@AdRequestBuilder.requestAgent
        }
        return AdRequest(request)
    }

    actual fun addCustomTargeting(key: String, value: String) {
        customTargeting[key] = value
    }

    actual fun addCustomTargeting(
        key: String,
        values: List<String>
    ) {
        addCustomTargeting(key, values.joinToString(separator = ",") { it })
    }

    actual fun addKeyword(keyword: String) {
        keywords.add(keyword)
    }

    actual fun addNetworkExtrasBundle() {

    }

    actual fun setAdString(adString: String) {
        this.adString = adString
    }

    actual fun setContentUrl(contentUrl: String) {
        this.contentUrl = contentUrl
    }

    actual fun setHttpTimeoutMillis(timeout: Int) {
        httpTimeoutMillis = timeout
    }

    actual fun setNeighboringContentUrls(urls: List<String>) {
        neighboringContentUrls = urls
    }

    actual fun setRequestAgent(requestAgent: String) {
        this.requestAgent = requestAgent
    }
}