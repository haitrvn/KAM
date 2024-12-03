package com.haitrvn.kam.reward

import cocoapods.Google_Mobile_Ads_SDK.GADAdMetadataDelegateProtocol
import cocoapods.Google_Mobile_Ads_SDK.GADAdMetadataProviderProtocol
import cocoapods.Google_Mobile_Ads_SDK.GADRewardedAd
import com.haitrvn.kam.AdRequest
import com.haitrvn.kam.AdValue
import com.haitrvn.kam.FullScreenContent
import com.haitrvn.kam.ResponseInfo
import com.haitrvn.kam.RootView
import com.haitrvn.kam.ServerSideVerificationOptions
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.suspendCancellableCoroutine
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
            return null
        }

        actual fun isAvailable(unitId: String): Boolean {
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
        get() = TODO()

    actual val fullScreenContentFlow: Flow<FullScreenContent>
        get() = TODO()

    actual val paidEventFlow: Flow<AdValue>
        get() = TODO()

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
    }

    actual fun show(
        rootView: RootView,
        onUserEarnedReward: (RewardItem) -> Unit
    ) {
        ios.presentFromRootViewController(rootView) {

        }
    }
}

actual class RewardItem(
    private val ios: GADReward
) {

}