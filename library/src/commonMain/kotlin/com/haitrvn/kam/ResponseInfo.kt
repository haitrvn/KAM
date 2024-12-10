package com.haitrvn.kam

expect class ResponseInfo {
    val adapterResponses: List<AdapterResponseInfo>
    val loadedAdapterResponseInfo: AdapterResponseInfo?
    val mediationAdapterClassName: String?
    val responseId: String?
    val responseExtras: Any
}