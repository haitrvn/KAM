package com.haitrvn.kam

import com.google.android.gms.ads.ResponseInfo as AndroidResponseInfo

actual data class ResponseInfo (
    actual val adapterResponses: List<AdapterResponseInfo>,
    actual val loadedAdapterResponseInfo: AdapterResponseInfo?,
    actual val mediationAdapterClassName: String?,
    actual val responseId: String?,
    actual val responseExtras: Any,
) {
    constructor(android: AndroidResponseInfo) : this(
        adapterResponses = android.adapterResponses.toCommon(),
        loadedAdapterResponseInfo = android.loadedAdapterResponseInfo?.toCommon(),
        mediationAdapterClassName = android.mediationAdapterClassName,
        responseId = android.responseId,
        responseExtras = android.responseExtras,
    )
}