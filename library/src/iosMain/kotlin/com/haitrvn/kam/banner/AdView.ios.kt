package com.haitrvn.kam.banner

import com.haitrvn.kam.AdEvent
import com.haitrvn.kam.AdRequest
import com.haitrvn.kam.AdValue
import com.haitrvn.kam.ResponseInfo
import kotlinx.coroutines.flow.Flow
import cocoapods.Google_Mobile_Ads_SDK.GADBannerView

actual class AdView(
    private val ios: GADBannerView
) {
    actual var unitId: String
        get() = ios.adUnitID
        set(value) { ios.adUnitID = value }

    actual val responseInfo: ResponseInfo?
        get() = TODO("Not yet implemented")

    actual val paidEventFlow: Flow<AdValue>
        get() = TODO("Not yet implemented")

    actual val adSize: AdSize?
        get() = TODO("Not yet implemented")

    actual val isCollapsible: Boolean
        get() = TODO("Not yet implemented")

    actual val isLoading: Boolean
        get() = TODO("Not yet implemented")

    actual val adEventFlow: Flow<AdEvent>
        get() = TODO("Not yet implemented")

    actual fun destroy() {
    }

    actual suspend fun load(request: AdRequest) {
    }

    actual fun resume() {
    }

    actual fun pause() {
    }

    actual fun setAdSize(adSize: AdSize) {
    }
}