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

    actual fun addCustomTargeting(key: String, value: String): AdRequestBuilder {
        customTargeting[key] = value
        return this
    }

    actual fun addCustomTargeting(
        key: String,
        values: List<String>
    ): AdRequestBuilder {
        return addCustomTargeting(key, values.joinToString(separator = ",") { it })
    }

    actual fun addKeyword(keyword: String): AdRequestBuilder {
        keywords.add(keyword)
        return this
    }

    actual fun addNetworkExtrasBundle(): AdRequestBuilder {
        //TODO
        return this
    }

    actual fun setAdString(adString: String): AdRequestBuilder {
        this.adString = adString
        return this
    }

    actual fun setContentUrl(contentUrl: String): AdRequestBuilder {
        this.contentUrl = contentUrl
        return this
    }

    actual fun setHttpTimeoutMillis(timeout: Int): AdRequestBuilder {
        httpTimeoutMillis = timeout
        return this
    }

    actual fun setNeighboringContentUrls(urls: List<String>): AdRequestBuilder {
        neighboringContentUrls = urls
        return this
    }

    actual fun setRequestAgent(requestAgent: String): AdRequestBuilder {
        this.requestAgent = requestAgent
        return this
    }
}