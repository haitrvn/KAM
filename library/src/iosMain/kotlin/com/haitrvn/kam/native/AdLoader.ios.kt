package com.haitrvn.kam.native

import cocoapods.Google_Mobile_Ads_SDK.GADNativeAd
import com.haitrvn.kam.AdEvent
import com.haitrvn.kam.AdRequest
import com.haitrvn.kam.AdValue
import com.haitrvn.kam.ResponseInfo
import com.haitrvn.kam.toCommon
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

@OptIn(ExperimentalForeignApi::class)
actual class NativeAd(
    val ios: GADNativeAd
) {
    internal actual val adChoicesInfo: AdChoicesInfo?
        get() = TODO("Not yet implemented")

    actual val extras: Any
        get() = TODO("Not yet implemented")
    actual val mediaContent: MediaContent?
        get() = TODO("Not yet implemented")
    actual val responseInfo: ResponseInfo?
        get() = ios.responseInfo.toCommon()
    actual val icon: Image?
        get() = TODO("Not yet implemented")
    actual val starRating: Double?
        get() = ios.starRating?.doubleValue
    actual val advertiser: String?
        get() = ios.advertiser
    actual val body: String?
        get() = ios.body
    actual val callToAction: String?
        get() = ios.callToAction
    actual val headline: String?
        get() = ios.headline
    actual val price: String?
        get() = ios.price
    actual val store: String?
        get() = ios.store
    actual val images: List<Image>
        get() = TODO("Not yet implemented")
    actual val muteThisAdReasons: List<MuteThisAdReason>
        get() = TODO("Not yet implemented")
    actual val isCustomMuteThisAdEnabled: Boolean
        get() = ios.isCustomMuteThisAdAvailable()
    actual val paidEventFlow: Flow<AdValue>
        get() = callbackFlow {
            ios.setPaidEventHandler {
                it?.let { trySend(AdValue(it)) }
            }
            awaitClose { ios.paidEventHandler = null }
        }
    actual val unconfirmedClickFlow: Flow<UnconfirmedClickEvent>
        get() = TODO("Not yet implemented")
    actual val adMutedFlow: Flow<Unit>
        get() = callbackFlow {
        }
    actual val adListenerEvent: Flow<AdEvent>
        get() = TODO()

    actual fun cancelUnconfirmedClick() {
    }

    actual fun destroy() {
    }

    actual fun muteThisAd(reason: MuteThisAdReason) {
//        ios.muteThisAdWithReason(reason)
        TODO()
    }

    actual fun performClick(bundle: Any) {
    }

    actual fun reportTouchEvent(bundle: Any) {
    }

    actual fun recordImpression(bundle: Any) {
    }
}

actual class AdLoader {
    @OptIn(ExperimentalForeignApi::class)
    actual companion object {
        actual suspend fun load(
            unitId: String,
            request: AdRequest,
            adOptions: AdOptions?
        ): NativeAd? {
//            GADAdLoader(unitId, null, listOf(GADAdLoaderAdTypeNative), adOptions)
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