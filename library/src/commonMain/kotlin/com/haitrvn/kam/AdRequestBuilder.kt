package com.haitrvn.kam

expect class AdRequestBuilder {
    fun build(): AdRequest
    fun addCustomTargeting(key: String, value: String): AdRequestBuilder
    fun addCustomTargeting(key: String, values: List<String>): AdRequestBuilder
    fun addKeyword(keyword: String): AdRequestBuilder
    internal fun addNetworkExtrasBundle(): AdRequestBuilder
    fun setAdString(adString: String): AdRequestBuilder
    fun setContentUrl(contentUrl: String): AdRequestBuilder
    fun setHttpTimeoutMillis(timeout: Int): AdRequestBuilder
    fun setNeighboringContentUrls(urls: List<String>): AdRequestBuilder
    fun setRequestAgent(requestAgent: String): AdRequestBuilder
}