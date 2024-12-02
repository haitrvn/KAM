package com.haitrvn.kam

expect class AdapterResponseInfo {
    val latencyInMillis: Long
    val credentials: Any
    val adError: AdError?
    val adSourceId: String
    val adSourceInstanceId: String
    val adSourceInstanceName: String
    val adSourceName: String
    val adapterClassName: String
}