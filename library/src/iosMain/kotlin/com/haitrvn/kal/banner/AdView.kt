package com.haitrvn.kal.banner

import cocoapods.AppLovinSDK.MAAd
import cocoapods.AppLovinSDK.MAAdRequestDelegateProtocol
import cocoapods.AppLovinSDK.MAAdRevenueDelegateProtocol
import cocoapods.AppLovinSDK.MAAdReviewDelegateProtocol
import cocoapods.AppLovinSDK.MAAdView
import cocoapods.AppLovinSDK.MAAdViewAdDelegateProtocol
import cocoapods.AppLovinSDK.MAError
import com.haitrvn.kal.core.Ad
import com.haitrvn.kal.initialization.AppLovinSdk
import com.haitrvn.kal.model.AdEvent
import com.haitrvn.kal.model.ReviewAd
import com.haitrvn.kal.rewarded.toCommonError
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import platform.CoreGraphics.CGFloat
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class)
actual class AdView actual constructor(
    adUnitId: String,
    private val adFormat: AdFormat?,
    sdk: AppLovinSdk?
) {
    val ios: MAAdView by lazy {
        when {
            adFormat != null && sdk != null -> MAAdView(
                adUnitId,
                adFormat.ios,
                sdk.ios,
            )

            adFormat != null -> MAAdView(
                adUnitId,
                adFormat.ios,
            )

            sdk != null -> MAAdView(
                adUnitId,
                sdk.ios,
            )

            else -> MAAdView(
                adUnitId,
            )
        }
    }

    actual val unitId: String
        get() = ios.adUnitIdentifier

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
            awaitClose { ios.adReviewDelegate = null }
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
            awaitClose { ios.revenueDelegate = null }
        }

    actual val adEventFlow: Flow<AdEvent>
        get() = callbackFlow {
            ios.delegate = object : NSObject(), MAAdViewAdDelegateProtocol {
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

                override fun didCollapseAd(ad: MAAd) {
                    trySend(AdEvent.Collapsed(Ad(ad)))
                }

                override fun didExpandAd(ad: MAAd) {
                    trySend(AdEvent.Expanded(Ad(ad)))
                }
            }
            awaitClose { ios.delegate = null }
        }

    actual fun getAdFormat(): AdFormat? {
       return adFormat
    }

    actual fun getPlacement(): String? {
        return ios.placement
    }

    actual suspend fun loadAd(): AdView? {
        ios.loadAd()
    }

    actual fun setAlpha(alpha: Float) {
        ios.setAlpha(CGFloat.MAX_VALUE)
    }

    actual fun setBackgroundColor() {
    }

    actual fun setCustomData(data: String) {
    }

    actual fun setExtraParameter(param: String, data: String) {
    }

    actual fun setLocalExtraParameter(param: String, data: Any) {
    }

    actual fun setPlacement(placement: String) {
    }

    actual fun startAutoRefresh() {
    }

    actual fun stopAutoRefresh() {
    }

    actual fun destroy() {
    }
}