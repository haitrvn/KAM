package com.haitrvn.kal.interstitial

import androidx.lifecycle.Lifecycle
import cocoapods.AppLovinSDK.MAAd
import cocoapods.AppLovinSDK.MAAdDelegateProtocol
import cocoapods.AppLovinSDK.MAAdExpirationDelegateProtocol
import cocoapods.AppLovinSDK.MAAdRequestDelegateProtocol
import cocoapods.AppLovinSDK.MAAdRevenueDelegateProtocol
import cocoapods.AppLovinSDK.MAAdReviewDelegateProtocol
import cocoapods.AppLovinSDK.MAError
import cocoapods.AppLovinSDK.MAInterstitialAd
import com.haitrvn.kal.core.Ad
import com.haitrvn.kal.core.RootView
import com.haitrvn.kal.initialization.AppLovinSdk
import com.haitrvn.kal.model.AdEvent
import com.haitrvn.kal.model.ReviewAd
import com.haitrvn.kal.rewarded.ViewGroup
import com.haitrvn.kal.rewarded.toCommonError
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class)
actual class InterstitialAd actual constructor(
    private val adUnitId: String,
    private val appLovinSdk: AppLovinSdk?
) {
    private val ios by lazy {
        if (appLovinSdk != null) {
            MAInterstitialAd(adUnitId, appLovinSdk.ios)
        } else {
            MAInterstitialAd(adUnitId)
        }
    }


    actual val reviewFlow: Flow<ReviewAd>
        get() = callbackFlow {
            ios.adReviewDelegate =
                object : NSObject(), MAAdReviewDelegateProtocol {
                    override fun didGenerateCreativeIdentifier(
                        creativeIdentifier: String,
                        forAd: MAAd
                    ) {
                        trySend(ReviewAd(creativeIdentifier, Ad(forAd)))
                    }
                }
        }

    actual val expirationFlow: Flow<Pair<Ad, Ad>>
        get() = callbackFlow {
            ios.expirationDelegate =
                object : NSObject(), MAAdExpirationDelegateProtocol {
                    override fun didReloadExpiredAd(expiredAd: MAAd, withNewAd: MAAd) {
                        trySend(Ad(expiredAd) to Ad(withNewAd))
                    }
                }
        }

    actual val revenueFlow: Flow<Ad>
        get() = callbackFlow {
            ios.revenueDelegate = object : NSObject(), MAAdRevenueDelegateProtocol {
                override fun didPayRevenueForAd(ad: MAAd) {
                    trySend(Ad(ad))
                }
            }
        }

    actual val requestFlow: Flow<String>
        get() = callbackFlow {
            ios.requestDelegate = object : NSObject(), MAAdRequestDelegateProtocol {
                override fun didStartAdRequestForAdUnitIdentifier(adUnitIdentifier: String) {
                    trySend(adUnitIdentifier)
                }
            }
        }

    actual val adEventFlow: Flow<AdEvent>
        get() = callbackFlow {
            ios.delegate = object : NSObject(), MAAdDelegateProtocol {
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
            awaitClose { ios.setDelegate(null) }
        }


    actual fun loadAd() {
        ios.loadAd()
    }

    actual fun setExtraParameter(key: String, value: String) {
    }

    internal actual fun setLocalExtraParameter(key: String, param: Any) {
    }

    actual fun showAd(rootView: RootView) {
    }

    actual fun showAd(placement: String, rootView: RootView) {
    }

    actual fun showAd(
        placement: String,
        customData: String,
        rootView: RootView
    ) {
    }

    internal actual fun showAd(
        viewGroup: ViewGroup,
        lifecycle: Lifecycle,
        rootView: RootView
    ) {
    }

    internal actual fun showAd(
        placement: String,
        viewGroup: ViewGroup,
        lifecycle: Lifecycle,
        rootView: RootView
    ) {
    }

    internal actual fun showAd(
        placement: String,
        customData: String,
        viewGroup: ViewGroup,
        lifecycle: Lifecycle,
        rootView: RootView
    ) {
    }

    actual fun destroy() {
    }
}