package com.haitrvn.kam

actual class AdapterResponseInfo(
    private val android: com.google.android.gms.ads.AdapterResponseInfo
) {
    actual val adError: AdError?
        get() = android.adError?.toCommon()

    actual val adSourceId: String
        get() = android.adSourceId

    actual val adSourceInstanceId: String
        get() = android.adSourceInstanceId

    actual val adSourceInstanceName: String
        get() = android.adSourceInstanceName

    actual val adSourceName: String
        get() = android.adSourceName

    actual val adapterClassName: String
        get() = android.adapterClassName

    actual val credentials: Any
        get() = android.credentials

    actual val latencyInMillis: Long
        get() = android.latencyMillis
}