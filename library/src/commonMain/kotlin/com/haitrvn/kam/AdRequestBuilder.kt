package com.haitrvn.kam

expect class AdRequestBuilder {
    fun build(): AdRequest
    fun addCustomTargeting(key: String, value: String)
    fun addCustomTargeting(key: String, values: List<String>)
    fun addKeyword(keyword: String)
    internal fun addNetworkExtrasBundle()
    fun setAdString(adString: String)
    fun setContentUrl(contentUrl: String)
    fun setHttpTimeoutMillis(timeout: Int)
    fun setNeighboringContentUrls(urls: List<String>)
    fun setRequestAgent(requestAgent: String)
}