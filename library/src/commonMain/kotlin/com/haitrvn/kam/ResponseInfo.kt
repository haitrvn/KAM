package com.haitrvn.kam

expect class ResponseInfo {
    val responseExtras: Any
    val adapterResponseInfo: AdapterResponseInfo?
    val mediationAdapterClassName: String?
    val responseId: String?
    val adapterResponses: List<AdapterResponseInfo>
}