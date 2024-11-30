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
import kotlinx.coroutines.suspendCancellableCoroutine
import platform.CoreGraphics.CGFloat
import platform.darwin.NSObject
import kotlin.coroutines.resume

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

    val listenerGroup: BannerAdListenerGroup by lazy {
        BannerAdListenerGroup(ios, mutableListOf())
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
            val listener = object : NSObject(), MAAdViewAdDelegateProtocol {
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
            listenerGroup.listenerList.add(listener)
            awaitClose {
                ios.delegate = null
                listenerGroup.listenerList.remove(listener)
            }
        }

    actual fun getAdFormat(): AdFormat? {
        return adFormat
    }

    actual fun getPlacement(): String? {
        return ios.placement
    }

    actual suspend fun loadAd(): AdView? {
        return suspendCancellableCoroutine { continuation ->
            val listener = object : NSObject(), MAAdViewAdDelegateProtocol {
                override fun didClickAd(ad: MAAd) {
                }

                override fun didDisplayAd(ad: MAAd) {
                }

                override fun didFailToDisplayAd(ad: MAAd, withError: MAError) {
                }

                override fun didFailToLoadAdForAdUnitIdentifier(
                    adUnitIdentifier: String,
                    withError: MAError
                ) {
                    continuation.resume(null)
                }

                override fun didHideAd(ad: MAAd) {
                }

                override fun didLoadAd(ad: MAAd) {
                    continuation.resume(this@AdView)
                }

                override fun didCollapseAd(ad: MAAd) {
                }

                override fun didExpandAd(ad: MAAd) {
                }
            }
            listenerGroup.listenerList.add(listener)
            continuation.invokeOnCancellation { listenerGroup.listenerList.remove(listener) }
            ios.loadAd()
        }
    }

    actual fun setAlpha(alpha: Float) {
        ios.setAlpha(CGFloat.MAX_VALUE)
    }

    actual fun setBackgroundColor() {
        //TODO
    }

    actual fun setCustomData(data: String) {
        ios.customData = data
    }

    actual fun setExtraParameter(key: String, value: String) {
        ios.setExtraParameterForKey(key, value)
    }

    actual fun setLocalExtraParameter(key: String, value: Any) {
        ios.setLocalExtraParameterForKey(key, value)
    }

    actual fun setPlacement(placement: String) {
        ios.placement = placement
    }

    actual fun startAutoRefresh() {
        ios.startAutoRefresh()
    }

    actual fun stopAutoRefresh() {
        ios.stopAutoRefresh()
    }

    actual fun destroy() {
        //TODO
    }
}