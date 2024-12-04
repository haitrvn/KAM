package com.haitrvn.kam.banner

import cocoapods.Google_Mobile_Ads_SDK.GADAdSize
import cocoapods.Google_Mobile_Ads_SDK.GADBannerView
import cocoapods.Google_Mobile_Ads_SDK.GADBannerViewDelegateProtocol
import com.haitrvn.kam.AdError
import com.haitrvn.kam.AdEvent
import com.haitrvn.kam.AdRequest
import com.haitrvn.kam.AdValue
import com.haitrvn.kam.EMPTY_STRING
import com.haitrvn.kam.ResponseInfo
import com.haitrvn.kam.toCommon
import kotlinx.cinterop.CValue
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.readValue
import kotlinx.cinterop.useContents
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import platform.Foundation.NSError
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class)
actual class AdView(
    private val ios: GADBannerView
) {
    actual var unitId: String
        get() = ios.adUnitID ?: EMPTY_STRING
        set(value) {
            ios.adUnitID = value
        }

    actual val responseInfo: ResponseInfo?
        get() = ios.responseInfo?.toCommon()

    actual val paidEventFlow: Flow<AdValue>
        get() = TODO("Not yet implemented")

    actual val adSize: AdSize?
        get() = ios.adSize.toCommon()

    actual val isCollapsible: Boolean
        get() = ios.isCollapsible

    actual val isLoading: Boolean
        get() = false

    actual val adEventFlow: Flow<AdEvent>
        get() = callbackFlow {
            ios.delegate = object : NSObject(), GADBannerViewDelegateProtocol {
                override fun bannerViewWillPresentScreen(bannerView: GADBannerView) {
                    super.bannerViewWillPresentScreen(bannerView)
                    trySend(AdEvent.Opened)
                }

                override fun bannerViewWillDismissScreen(bannerView: GADBannerView) {
                    super.bannerViewWillDismissScreen(bannerView)
                }

                override fun bannerViewDidRecordImpression(bannerView: GADBannerView) {
                    super.bannerViewDidRecordImpression(bannerView)
                    trySend(AdEvent.Impression)
                }

                override fun bannerViewDidRecordClick(bannerView: GADBannerView) {
                    super.bannerViewDidRecordClick(bannerView)
                    trySend(AdEvent.Clicked)
                }

                override fun bannerViewDidReceiveAd(bannerView: GADBannerView) {
                    super.bannerViewDidReceiveAd(bannerView)
                    trySend(AdEvent.Loaded)
                }

                override fun bannerViewDidDismissScreen(bannerView: GADBannerView) {
                    super.bannerViewDidDismissScreen(bannerView)
                    trySend(AdEvent.Closed)
                }

                override fun bannerView(
                    bannerView: GADBannerView,
                    didFailToReceiveAdWithError: NSError
                ) {
                    super.bannerView(bannerView, didFailToReceiveAdWithError)
                    trySend(AdEvent.FailedToLoad(AdError(didFailToReceiveAdWithError)))
                }
            }
            awaitClose { ios.delegate = null }
        }

    actual fun destroy() {
        //ios no need destroy
    }

    actual suspend fun load(request: AdRequest) {
        ios.loadRequest(request.ios)
    }

    actual fun resume() {
        //ios no need resume
    }

    actual fun pause() {
        //ios no need pause
    }

    actual fun setAdSize(adSize: AdSize) {
        ios.adSize = adSize.ios.readValue()
    }
}

@OptIn(ExperimentalForeignApi::class)
private fun CValue<GADAdSize>.toCommon(): AdSize {
    return AdSize(useContents { this })
}
