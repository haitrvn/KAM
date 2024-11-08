package com.haitrvn.kal.core

actual interface WaterfallInfo
actual interface MediatedNetworkInfo {
    actual fun getName(): String?
    actual fun getAdapterClassName(): String?
    actual fun getAdapterVersion(): String?
    actual fun getSdkVersion(): String?

}

actual interface NetworkResponseInfo {
    actual fun getAdLoadStates(): AdLoadStates?
    actual fun getMediatedNetwork(): MediatedNetworkInfo?
    actual fun getCredentials(): Any
    actual fun isBidding(): Boolean
    actual fun getLatencyMillis(): Long
    actual fun getError(): AdError?
    actual enum class AdLoadState {
        AD_LOAD_NOT_ATTEMPTED, AD_LOADED, FAILED_TO_LOAD
    }

}