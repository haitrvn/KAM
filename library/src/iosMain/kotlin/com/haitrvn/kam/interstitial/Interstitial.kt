package com.haitrvn.kam.interstitial

import androidx.compose.runtime.Composable
import cocoapods.Google_Mobile_Ads_SDK.GADFullScreenContentDelegateProtocol
import cocoapods.Google_Mobile_Ads_SDK.GADFullScreenPresentingAdProtocol
import cocoapods.Google_Mobile_Ads_SDK.GADInterstitialAd
import com.haitrvn.kam.AdRequest
import com.haitrvn.kam.AdValue
import com.haitrvn.kam.FullScreenContent
import com.haitrvn.kam.ResponseInfo
import com.haitrvn.kam.RootView
import com.haitrvn.kam.getRootView
import com.haitrvn.kam.toCommon
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
            //ios not support preload
            return null
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
            ios.setPaidEventHandler { it?.let { trySend(AdValue(it)) } }
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
                    }

                    override fun adDidDismissFullScreenContent(ad: GADFullScreenPresentingAdProtocol) {
                    }

                    override fun adDidRecordClick(ad: GADFullScreenPresentingAdProtocol) {
                    }

                    override fun adDidRecordImpression(ad: GADFullScreenPresentingAdProtocol) {
                    }

                    override fun adWillDismissFullScreenContent(ad: GADFullScreenPresentingAdProtocol) {
                    }

                    override fun adWillPresentFullScreenContent(ad: GADFullScreenPresentingAdProtocol) {
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