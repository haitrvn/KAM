package com.haitrvn.kam.native

import com.haitrvn.kam.AdRequest

expect class AdLoader {
    companion object {
        suspend fun load(
            unitId: String,
            request: AdRequest,
            adOptions: AdOptions? = null
        ): NativeAd?

        internal suspend fun loads(
            unitId: String,
            request: AdRequest,
            maxNumberOfAds: Int,
            adOptions: AdOptions? = null
        ): NativeAd?
    }

    val isLoading: Boolean
}

sealed interface UnconfirmedClickEvent {
    data object Canceled: UnconfirmedClickEvent
    data class Received(val value: String): UnconfirmedClickEvent
}