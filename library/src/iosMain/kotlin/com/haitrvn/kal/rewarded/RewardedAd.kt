package com.haitrvn.kal.rewarded

import androidx.lifecycle.Lifecycle
import cocoapods.AppLovinSDK.MAAd
import cocoapods.AppLovinSDK.MAAdExpirationDelegateProtocol
import cocoapods.AppLovinSDK.MAAdRequestDelegateProtocol
import cocoapods.AppLovinSDK.MAAdRevenueDelegateProtocol
import cocoapods.AppLovinSDK.MAAdReviewDelegateProtocol
import cocoapods.AppLovinSDK.MAError
import cocoapods.AppLovinSDK.MAReward
import cocoapods.AppLovinSDK.MARewardedAd
import cocoapods.AppLovinSDK.MARewardedAdDelegateProtocol
import com.haitrvn.kal.core.Ad
import com.haitrvn.kal.core.AdError
import com.haitrvn.kal.core.Reward
import com.haitrvn.kal.core.RootView
import com.haitrvn.kal.initialization.AppLovinSdk
import com.haitrvn.kal.model.AdEvent
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import platform.UIKit.UIViewController
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class)
actual class RewardedAd actual constructor(
    adUnitId: String,
    appLovinSdk: AppLovinSdk?
) {
    private val ios: MARewardedAd by lazy {
        if (appLovinSdk != null) {
            MARewardedAd.sharedWithAdUnitIdentifier(adUnitId, appLovinSdk.ios)
        } else {
            MARewardedAd.sharedWithAdUnitIdentifier(adUnitId)
        }
    }
    actual val isReady: Boolean
        get() = ios.ready
    actual val unitId: String
        get() = ios.adUnitIdentifier
    actual val reviewFlow: Flow<Ad>
        get() = callbackFlow {
            ios.adReviewDelegate =
                object : NSObject(), MAAdReviewDelegateProtocol {
                    override fun didGenerateCreativeIdentifier(
                        creativeIdentifier: String,
                        forAd: MAAd
                    ) {
                        trySend(Ad(forAd))
                    }
                }
            awaitClose { ios.adReviewDelegate = null }
        }

    actual val expirationFlow: Flow<Pair<Ad, Ad>>
        get() = callbackFlow {
            ios.expirationDelegate = object : NSObject(), MAAdExpirationDelegateProtocol {
                override fun didReloadExpiredAd(expiredAd: MAAd, withNewAd: MAAd) {
                    trySend(Ad(expiredAd) to Ad(withNewAd))
                }
            }
            awaitClose { ios.expirationDelegate = null }
        }

    actual val revenueFlow: Flow<Ad>
        get() = callbackFlow {
            ios.revenueDelegate = object : NSObject(), MAAdRevenueDelegateProtocol {
                override fun didPayRevenueForAd(ad: MAAd) {
                    trySend(Ad(ad))
                }
            }
            awaitClose { ios.revenueDelegate = null }
        }

    actual val requestFlow: Flow<String>
        get() = callbackFlow {
            ios.requestDelegate = object : NSObject(), MAAdRequestDelegateProtocol {
                override fun didStartAdRequestForAdUnitIdentifier(adUnitIdentifier: String) {
                    trySend(adUnitIdentifier)
                }
            }
            awaitClose { ios.requestDelegate = null }
        }

    actual val rewardedAdFlow: Flow<AdEvent>
        get() = callbackFlow {
            ios.delegate = object : NSObject(), MARewardedAdDelegateProtocol {
                override fun didRewardUserForAd(ad: MAAd, withReward: MAReward) {
                    trySend(AdEvent.UserRewarded(Ad(ad), Reward(withReward)))
                }

                override fun didClickAd(ad: MAAd) {
                    trySend(AdEvent.Clicked(Ad(ad)))
                }

                override fun didDisplayAd(ad: MAAd) {
                    trySend(AdEvent.Displayed(Ad(ad)))
                }

                override fun didFailToDisplayAd(ad: MAAd, withError: MAError) {
                    trySend(AdEvent.DisplayFailed(Ad(ad), withError.toCommonError()))
                }

                override fun didFailToLoadAdForAdUnitIdentifier(
                    adUnitIdentifier: String,
                    withError: MAError
                ) {
                    trySend(AdEvent.LoadFailed(adUnitIdentifier, withError.toCommonError()))
                }

                override fun didHideAd(ad: MAAd) {
                    trySend(AdEvent.Hidden(Ad(ad)))
                }

                override fun didLoadAd(ad: MAAd) {
                    trySend(AdEvent.Loaded(Ad(ad)))
                }
            }
            awaitClose { ios.delegate = null }
        }

    actual fun loadAd() {
        ios.loadAd()
    }

    actual fun setExtraParameter(key: String, value: String) {
        ios.setExtraParameterForKey(key, value)
    }

    internal actual fun setLocalExtraParameter(key: String, param: Any) {
        ios.setLocalExtraParameterForKey(key, param)
    }

    actual fun showAd(rootView: RootView) {
        ios.showAd()
    }

    actual fun showAd(placement: String, rootView: RootView) {
        ios.showAdForPlacement(placement)
    }

    actual fun showAd(
        placement: String,
        customData: String,
        rootView: RootView
    ) {
        ios.showAdForPlacement(placement, customData)
    }

    internal actual fun showAd(
        viewGroup: ViewGroup,
        lifecycle: Lifecycle,
        rootView: RootView
    ) {
        ios.showAdForPlacement(null, null, viewGroup.ios)
    }

    internal actual fun showAd(
        placement: String,
        viewGroup: ViewGroup,
        lifecycle: Lifecycle,
        rootView: RootView
    ) {
        ios.showAdForPlacement(placement, null, viewGroup.ios)
    }

    internal actual fun showAd(
        placement: String,
        customData: String,
        viewGroup: ViewGroup,
        lifecycle: Lifecycle,
        rootView: RootView
    ) {
        ios.showAdForPlacement(placement, customData, viewGroup.ios)
    }

    actual fun destroy() {
        //TODO
    }
}

actual class ViewGroup(
    val ios: UIViewController
)

@OptIn(ExperimentalForeignApi::class)
fun MAError.toCommonError(): AdError {
    TODO()
}
