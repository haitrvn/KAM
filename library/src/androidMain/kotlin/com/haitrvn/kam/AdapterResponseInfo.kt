package com.haitrvn.kam

import com.google.android.gms.ads.AdapterResponseInfo as AndroidAdapterResponseInfo

actual data class AdapterResponseInfo (
    actual val adError: AdError?,
    actual val adSourceId: String,
    actual val adSourceInstanceId: String,
    actual val adapterClassName: String,
    actual val adSourceName: String,
    actual val adSourceInstanceName: String,
    actual val credentials: Any,
    actual val latencyInMillis: Long,
) {
    constructor(android: AndroidAdapterResponseInfo) : this(
        adError = android.adError?.toCommon(),
        adSourceId = android.adSourceId,
        adSourceInstanceId = android.adSourceInstanceId,
        adSourceInstanceName = android.adSourceInstanceName,
        adSourceName = android.adSourceName,
        adapterClassName = android.adapterClassName,
        credentials = android.credentials,
        latencyInMillis = android.latencyMillis,
    )
}