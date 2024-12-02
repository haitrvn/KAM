package com.haitrvn.kam

expect class AdRequest {
    companion object {
        fun createInstance(block: AdRequestBuilder.() -> Unit): AdRequest
    }

    val customTargeting: Any
    val adString: String?
    val contentUrl: String
    val requestAgent: String
    val neighboringContentUrls: List<String>
    val keywords: Set<String>
    val isTestDevice: Boolean
    internal val networkExtrasBundle: Any?
}