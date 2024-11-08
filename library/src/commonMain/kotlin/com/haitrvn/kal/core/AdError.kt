package com.haitrvn.kal.core

data class AdError(
    val code: Int,
    val message: String,
    val mediatedNetworkErrorCode: Int,
    val mediatedNetworkErrorMessage: String,
    val waterfallInfo: WaterfallInfo,
    val requestLatencyMillis: Long,
)

data class WaterfallInfo(
    val loadedAd: Ad?,
    val name: String?,
    val testName: String?,
    val networkResponses: List<NetworkResponseInfo?>?,
    val latencyMillis: Long,
)

data class NetworkResponseInfo(
    val loadStates: AdLoadStates?,
    val mediatedNetwork: MediatedNetworkInfo?,
    val credential: Any,
    val isBidding: Boolean,
    val latencyMillis: Long,
    val adError: AdError?,
) {
    enum class AdLoadStates {
        AD_LOAD_NOT_ATTEMPTED,
        AD_LOADED,
        FAILED_TO_LOAD
    }
}


data class MediatedNetworkInfo(
    val name: String?,
    val adapterClassName: String?,
    val adapterVersion: String?,
    val sdkVersion: String?,
)