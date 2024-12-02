package com.haitrvn.kam

actual class AdRequest {
    actual companion object {
        actual fun createInstance(block: AdRequestBuilder.() -> Unit): AdRequest {
            TODO("Not yet implemented")
        }
    }

    actual val customTargeting: Any
        get() = TODO("Not yet implemented")
    actual val networkExtrasBundle: Any?
        get() = TODO("Not yet implemented")
    actual val adString: String?
        get() = TODO("Not yet implemented")
    actual val contentUrl: String
        get() = TODO("Not yet implemented")
    actual val requestAgent: String
        get() = TODO("Not yet implemented")
    actual val neighboringContentUrls: List<String>
        get() = TODO("Not yet implemented")
    actual val keywords: Set<String>
        get() = TODO("Not yet implemented")
    actual val isTestDevice: Boolean
        get() = TODO("Not yet implemented")
}