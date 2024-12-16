package com.haitrvn.kam.appopen

import androidx.compose.runtime.Composable
import cocoapods.Google_Mobile_Ads_SDK.GADAppOpenAd
import cocoapods.Google_Mobile_Ads_SDK.GADFullScreenContentDelegateProtocol
import cocoapods.Google_Mobile_Ads_SDK.GADFullScreenPresentingAdProtocol
import com.haitrvn.kam.AdError
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
actual class AppOpen(
    private val ios: GADAppOpenAd
) {
    actual companion object {
        actual suspend fun load(
            unitId: String,
            request: AdRequest
        ): AppOpen? {
            return suspendCancellableCoroutine { continuation ->
                GADAppOpenAd.loadWithAdUnitID(unitId, request.ios) { ad, error ->
                    if (ad != null) {
                        continuation.resume(AppOpen(ad))
                    }
                    continuation.resume(null)
                }
                continuation.invokeOnCancellation { }
            }
        }

        actual fun pollAd(unitId: String): AppOpen? {
            return null
        }

        actual fun isAvailable(unitId: String): Boolean {
            return false //iOS always return true not support preload
        }
    }

    actual val unitId: String
        get() = "" //TODO()

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
                        trySend(
                            FullScreenContent.ShowFailed(
                                AdError(
                                    didFailToPresentFullScreenContentWithError
                                )
                            )
                        )
                    }

                    override fun adDidDismissFullScreenContent(ad: GADFullScreenPresentingAdProtocol) {
                        trySend(FullScreenContent.Dismissed)
                    }

                    override fun adDidRecordClick(ad: GADFullScreenPresentingAdProtocol) {
                        trySend(FullScreenContent.Clicked)
                    }

                    override fun adDidRecordImpression(ad: GADFullScreenPresentingAdProtocol) {
                        trySend(FullScreenContent.Impression)
                    }

                    override fun adWillDismissFullScreenContent(ad: GADFullScreenPresentingAdProtocol) {
                        //TODO
                    }

                    override fun adWillPresentFullScreenContent(ad: GADFullScreenPresentingAdProtocol) {
                        trySend(FullScreenContent.Showed)
                    }
                }
            awaitClose { ios.fullScreenContentDelegate = null }
        }

    actual val paidEventFlow: Flow<AdValue>
        get() = callbackFlow {
            ios.setPaidEventHandler {
                it?.let { trySend(AdValue(it)) }
            }
            awaitClose { ios.paidEventHandler = null }
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