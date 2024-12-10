package com.haitrvn.kam.native

import com.haitrvn.kam.AdEvent
import com.haitrvn.kam.AdRequest
import com.haitrvn.kam.AdValue
import com.haitrvn.kam.ResponseInfo
import kotlinx.coroutines.flow.Flow

actual class NativeAd {
    actual val extras: Any
        get() = TODO("Not yet implemented")
    actual val mediaContent: MediaContent?
        get() = TODO("Not yet implemented")
    actual val responseInfo: ResponseInfo?
        get() = TODO("Not yet implemented")
    actual val adChoicesInfo: AdChoicesInfo?
        get() = TODO("Not yet implemented")
    actual val icon: Image?
        get() = TODO("Not yet implemented")
    actual val starRating: Double?
        get() = TODO("Not yet implemented")
    actual val advertiser: String?
        get() = TODO("Not yet implemented")
    actual val body: String?
        get() = TODO("Not yet implemented")
    actual val callToAction: String?
        get() = TODO("Not yet implemented")
    actual val headline: String?
        get() = TODO("Not yet implemented")
    actual val price: String?
        get() = TODO("Not yet implemented")
    actual val store: String?
        get() = TODO("Not yet implemented")
    actual val images: List<Image>
        get() = TODO("Not yet implemented")
    actual val muteThisAdReasons: List<MuteThisAdReason>
        get() = TODO("Not yet implemented")
    actual val isCustomMuteThisAdEnabled: Boolean
        get() = TODO("Not yet implemented")
    actual val paidEventFlow: Flow<AdValue>
        get() = TODO("Not yet implemented")
    actual val unconfirmedClickFlow: Flow<UnconfirmedClickEvent>
        get() = TODO("Not yet implemented")
    actual val adMutedFlow: Flow<Unit>
        get() = TODO("Not yet implemented")
    actual val adListenerEvent: Flow<AdEvent>
        get() = TODO("Not yet implemented")

    actual fun cancelUnconfirmedClick() {
    }

    actual fun destroy() {
    }

    actual fun muteThisAd(reason: MuteThisAdReason) {
    }

    actual fun performClick(bundle: Any) {
    }

    actual fun reportTouchEvent(bundle: Any) {
    }

    actual fun recordImpression(bundle: Any) {
    }
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