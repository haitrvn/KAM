package com.haitrvn.kam.banner

import com.haitrvn.kam.AdEvent
import com.haitrvn.kam.AdRequest
import com.haitrvn.kam.AdValue
import com.haitrvn.kam.ResponseInfo
import kotlinx.coroutines.flow.Flow

expect class AdView {
    var unitId: String
    val responseInfo: ResponseInfo?
    val paidEventFlow: Flow<AdValue>
    val adSize: AdSize?
    val isCollapsible: Boolean
    val isLoading: Boolean
    val adEventFlow: Flow<AdEvent>
    fun destroy()
    suspend fun load(request: AdRequest)
    fun resume()
    fun pause()
    fun setAdSize(adSize: AdSize)
}