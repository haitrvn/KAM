package com.haitrvn.kam

actual class AdRequestBuilder {
    actual fun build(): AdRequest {
        TODO("Not yet implemented")
    }

    actual fun addCustomTargeting(key: String, value: String) {
    }

    actual fun addCustomTargeting(
        key: String,
        values: List<String>
    ) {
    }

    actual fun addKeyword(keyword: String) {
    }

    actual fun addNetworkExtrasBundle() {
    }

    actual fun setAdString(adString: String) {
    }

    actual fun setContentUrl(contentUrl: String) {
    }

    actual fun setHttpTimeoutMillis(timeout: Int) {
    }

    actual fun setNeighboringContentUrls(urls: List<String>) {
    }

    actual fun setRequestAgent(requestAgent: String) {
    }
}