package com.haitrvn.kam.reward

import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.admanager.AdManagerAdRequest
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.haitrvn.kam.AdError
import com.haitrvn.kam.AdRequest
import com.haitrvn.kam.AdValue
import com.haitrvn.kam.FullScreenContent
import com.haitrvn.kam.ResponseInfo
import com.haitrvn.kam.RootView
import com.haitrvn.kam.ServerSideVerificationOptions
import com.haitrvn.kam.toCommon
import com.haitrvn.kam.util.ContextProvider
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

actual class Rewarded(
    private val android: RewardedAd
) {
    actual companion object {
        actual suspend fun load(unitId: String, request: AdRequest): Rewarded? {
            return suspendCancellableCoroutine { continuation ->
                RewardedAd.load(
                    ContextProvider.context,
                    unitId,
                    request.android,
                    object : RewardedAdLoadCallback() {
                        override fun onAdFailedToLoad(p0: LoadAdError) {
                            super.onAdFailedToLoad(p0)
                            continuation.resume(null)
                        }

                        override fun onAdLoaded(p0: RewardedAd) {
                            super.onAdLoaded(p0)
                            continuation.resume(Rewarded(p0))
                        }
                    }
                )
            }
        }

        internal suspend fun load(unitId: String, request: AdManagerAdRequest): Rewarded? {
            return suspendCancellableCoroutine { continuation ->
                RewardedAd.load(
                    ContextProvider.context,
                    unitId,
                    request,
                    object : RewardedAdLoadCallback() {
                        override fun onAdFailedToLoad(p0: LoadAdError) {
                            super.onAdFailedToLoad(p0)
                            continuation.resume(null)
                        }

                        override fun onAdLoaded(p0: RewardedAd) {
                            super.onAdLoaded(p0)
                            continuation.resume(Rewarded(p0))
                        }
                    }
                )
            }
        }

        actual fun pollAd(unitId: String): Rewarded? =
            RewardedAd.pollAd(ContextProvider.context, unitId)?.let { Rewarded(it) }

        actual fun isAvailable(unitId: String): Boolean =
            RewardedAd.isAdAvailable(ContextProvider.context, unitId)
    }

    actual val unitId: String
        get() = android.adUnitId

    actual val responseInfo: ResponseInfo
        get() = android.responseInfo.toCommon()

    actual val rewardedItem: RewardItem
        get() = android.rewardItem.toCommon()

    actual val adMetaData: Any
        get() = android.adMetadata

    actual val dataChangedFlow: Flow<Unit>
        get() = callbackFlow {
            android.setOnAdMetadataChangedListener {
                trySend(Unit)

            }
            awaitClose {
                android.onAdMetadataChangedListener = null
            }
        }

    actual val fullScreenContentFlow: Flow<FullScreenContent>
        get() = callbackFlow {
            android.fullScreenContentCallback = object : FullScreenContentCallback() {
                override fun onAdClicked() {
                    super.onAdClicked()
                    trySend(FullScreenContent.Clicked)
                }

                override fun onAdDismissedFullScreenContent() {
                    super.onAdDismissedFullScreenContent()
                    trySend(FullScreenContent.Dismissed)
                }

                override fun onAdFailedToShowFullScreenContent(p0: com.google.android.gms.ads.AdError) {
                    super.onAdFailedToShowFullScreenContent(p0)
                    trySend(FullScreenContent.ShowFailed(AdError(p0)))
                }

                override fun onAdImpression() {
                    super.onAdImpression()
                    trySend(FullScreenContent.Impression)
                }

                override fun onAdShowedFullScreenContent() {
                    super.onAdShowedFullScreenContent()
                    trySend(FullScreenContent.Showed)
                }
            }
            awaitClose {
                android.fullScreenContentCallback = null
            }
        }

    actual val paidEventFlow: Flow<AdValue>
        get() = callbackFlow {
            android.setOnPaidEventListener {
                trySend(it.toCommon())
            }
            awaitClose { android.onPaidEventListener = null }
        }

    actual fun setImmersiveMode(immersive: Boolean) {
        android.setImmersiveMode(immersive)
    }

    actual fun setServerSideVerificationOptions(option: ServerSideVerificationOptions) {
        android.setServerSideVerificationOptions(option.android)
    }

    actual fun show(rootView: RootView, onUserEarnedReward: (RewardItem) -> Unit) {
        android.show(rootView) {
            onUserEarnedReward(RewardItem(it))
        }
    }
}