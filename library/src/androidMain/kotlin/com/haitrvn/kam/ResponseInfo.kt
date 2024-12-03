package com.haitrvn.kam

actual class ResponseInfo(
    private val android: com.google.android.gms.ads.ResponseInfo
) {
    actual val adapterResponses: List<AdapterResponseInfo>
        get() = android.adapterResponses.toCommon()

    actual val loadedAdapterResponseInfo: AdapterResponseInfo?
        get() = android.loadedAdapterResponseInfo?.toCommon()

    actual val mediationAdapterClassName: String?
        get() = android.mediationAdapterClassName

    actual val responseId: String?
        get() = android.responseId

    actual val responseExtras: Any
        get() = android.responseExtras
}