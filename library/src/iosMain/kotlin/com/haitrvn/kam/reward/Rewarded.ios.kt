package com.haitrvn.kam.reward

import cocoapods.Google_Mobile_Ads_SDK.GADAdMetadataDelegateProtocol
import cocoapods.Google_Mobile_Ads_SDK.GADAdMetadataProviderProtocol
import cocoapods.Google_Mobile_Ads_SDK.GADFullScreenContentDelegateProtocol
import cocoapods.Google_Mobile_Ads_SDK.GADFullScreenPresentingAdProtocol
import cocoapods.Google_Mobile_Ads_SDK.GADRewardedAd
import com.haitrvn.kam.AdError
import com.haitrvn.kam.AdRequest
import com.haitrvn.kam.AdValue
import com.haitrvn.kam.FullScreenContent
import com.haitrvn.kam.ResponseInfo
import com.haitrvn.kam.RootView
import com.haitrvn.kam.ServerSideVerificationOptions
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
actual class Rewarded(
    private val ios: GADRewardedAd
) {
    actual companion object {
        actual suspend fun load(
            unitId: String,
            request: AdRequest
        ): Rewarded? {
            return suspendCancellableCoroutine {
                GADRewardedAd.loadWithAdUnitID(unitId, request.ios) { ad, error ->
                    if (ad != null) {
                        it.resume(Rewarded(ad))
                    } else {
                        it.resume(null)
                    }
                }
            }
        }

        actual fun pollAd(unitId: String): Rewarded? {
            //iOS not support preload
            return null
        }

        actual fun isAvailable(unitId: String): Boolean {
            //iOS not support preload
            return false
        }
    }

    actual val unitId: String
        get() = ios.adUnitID

    actual val responseInfo: ResponseInfo
        get() = ios.responseInfo.toCommon()

    actual val rewardedItem: RewardItem
        get() = ios.adReward.toCommon()

    actual val adMetaData: Any
        get() = ios.adMetadata as Any

    actual val fullScreenContentFlow: Flow<FullScreenContent>
        get() = callbackFlow {
            ios.fullScreenContentDelegate =
                object : NSObject(), GADFullScreenContentDelegateProtocol {
                    override fun ad(
                        ad: GADFullScreenPresentingAdProtocol,
                        didFailToPresentFullScreenContentWithError: NSError
                    ) {
                        super.ad(ad, didFailToPresentFullScreenContentWithError)
                        trySend(
                            FullScreenContent.ShowFailed(
                                AdError(
                                    didFailToPresentFullScreenContentWithError
                                )
                            )
                        )
                    }

                    override fun adDidDismissFullScreenContent(ad: GADFullScreenPresentingAdProtocol) {
                        super.adDidDismissFullScreenContent(ad)
                        trySend(FullScreenContent.Dismissed)
                    }

                    override fun adDidRecordClick(ad: GADFullScreenPresentingAdProtocol) {
                        super.adDidRecordClick(ad)
                        trySend(FullScreenContent.Clicked)
                    }

                    override fun adDidRecordImpression(ad: GADFullScreenPresentingAdProtocol) {
                        super.adDidRecordImpression(ad)
                        trySend(FullScreenContent.Impression)
                    }

                    override fun adWillDismissFullScreenContent(ad: GADFullScreenPresentingAdProtocol) {
                        super.adWillDismissFullScreenContent(ad)
                        //TODO
                    }

                    override fun adWillPresentFullScreenContent(ad: GADFullScreenPresentingAdProtocol) {
                        super.adWillPresentFullScreenContent(ad)
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

    actual val dataChangedFlow: Flow<Unit>
        get() = callbackFlow {
            ios.adMetadataDelegate = object : NSObject(), GADAdMetadataDelegateProtocol {
                override fun adMetadataDidChange(ad: GADAdMetadataProviderProtocol) {
                }
            }
        }

    actual fun setServerSideVerificationOptions(option: ServerSideVerificationOptions) {
        ios.serverSideVerificationOptions = option.ios
    }

    actual fun setImmersiveMode(immersive: Boolean) {
        //Ios not support immersive mode
    }

    actual fun show(
        rootView: RootView,
        onUserEarnedReward: (RewardItem) -> Unit
    ) {
        ios.presentFromRootViewController(rootView) {
            onUserEarnedReward(RewardItem(ios.adReward))
        }
    }
}