package com.haitrvn.kam.interstitial

import androidx.compose.runtime.Composable
import cocoapods.Google_Mobile_Ads_SDK.GADFullScreenContentDelegateProtocol
import cocoapods.Google_Mobile_Ads_SDK.GADFullScreenPresentingAdProtocol
import cocoapods.Google_Mobile_Ads_SDK.GADInterstitialAd
import cocoapods.Google_Mobile_Ads_SDK.GADResponseInfo
import com.haitrvn.kam.AdRequest
import com.haitrvn.kam.AdValue
import com.haitrvn.kam.FullScreenContent
import com.haitrvn.kam.ResponseInfo
import com.haitrvn.kam.RootView
import com.haitrvn.kam.getRootView
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import platform.Foundation.NSError
import platform.darwin.NSObject
import kotlin.coroutines.resume

@OptIn(ExperimentalForeignApi::class)
actual class Interstitial(
    private val ios: GADInterstitialAd
) {
    actual companion object {
        actual fun isAvailable(unitId: String): Boolean {
            return false //iOS always return true not support preload
        }

        actual fun pollAd(unitId: String): Interstitial? {
            return GADInterstitialAd.new()?.let { Interstitial(it) }
        }

        actual suspend fun load(
            unitId: String,
            adRequest: AdRequest
        ): Interstitial? {
            return suspendCancellableCoroutine { continuation ->
                GADInterstitialAd.loadWithAdUnitID(unitId, adRequest.ios) { ad, error ->
                    if (ad != null) {
                        continuation.resume(Interstitial(ad))
                    }
                    continuation.resume(null)
                }
                continuation.invokeOnCancellation { }
            }
        }
    }

    actual val unitId: String
        get() = ios.adUnitID
    actual val paidEventFlow: Flow<AdValue>
        get() = callbackFlow {
            ios.setPaidEventHandler { }
            awaitClose { ios.paidEventHandler = null }
        }
    actual val responseInfo: ResponseInfo
        get() = ios.responseInfo.toCommon()
    actual val fullScreenContentFlow: Flow<FullScreenContent>
        get() = callbackFlow {
            ios.fullScreenContentDelegate =
                object : NSObject(), GADFullScreenContentDelegateProtocol {
                    override fun ad(
                        ad: GADFullScreenPresentingAdProtocol,
                        didFailToPresentFullScreenContentWithError: NSError
                    ) {
                        super.ad(ad, didFailToPresentFullScreenContentWithError)
                    }

                    override fun adDidDismissFullScreenContent(ad: GADFullScreenPresentingAdProtocol) {
                        super.adDidDismissFullScreenContent(ad)
                    }

                    override fun adDidRecordClick(ad: GADFullScreenPresentingAdProtocol) {
                        super.adDidRecordClick(ad)
                    }

                    override fun adDidRecordImpression(ad: GADFullScreenPresentingAdProtocol) {
                        super.adDidRecordImpression(ad)
                    }

                    override fun adWillDismissFullScreenContent(ad: GADFullScreenPresentingAdProtocol) {
                        super.adWillDismissFullScreenContent(ad)
                    }

                    override fun adWillPresentFullScreenContent(ad: GADFullScreenPresentingAdProtocol) {
                        super.adWillPresentFullScreenContent(ad)
                    }
                }
            awaitClose { ios.fullScreenContentDelegate = null }
        }

    actual fun setImmersiveMode(immersive: Boolean) {
        //iOS do not thing about immersive mode
    }

    @Composable
    actual fun show() {
        ios.presentFromRootViewController(getRootView())
    }

    actual fun show(rootView: RootView) {
        ios.presentFromRootViewController(rootView)
    }
}

@OptIn(ExperimentalForeignApi::class)
private fun GADResponseInfo.toCommon(): ResponseInfo {
    TODO()
}
