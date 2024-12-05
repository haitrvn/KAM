package com.haitrvn.kam.native

import com.haitrvn.kam.AdRequest

actual class NativeAd {
}

actual class AdLoader {
    actual companion object {
        actual suspend fun load(
            unitId: String,
            request: AdRequest,
            adOptions: AdOptions?
        ): NativeAd? {
            TODO("Not yet implemented")
        }

        internal actual suspend fun loads(
            unitId: String,
            request: AdRequest,
            maxNumberOfAds: Int,
            adOptions: AdOptions?
        ): NativeAd? {
            TODO("Not yet implemented")
        }

    }

    actual val isLoading: Boolean
        get() = TODO("Not yet implemented")

}