package com.haitrvn.kal.native

import cocoapods.AppLovinSDK.MAAd
import cocoapods.AppLovinSDK.MAAdRevenueDelegateProtocol
import cocoapods.AppLovinSDK.MAAdReviewDelegateProtocol
import cocoapods.AppLovinSDK.MAError
import cocoapods.AppLovinSDK.MANativeAdDelegateProtocol
import cocoapods.AppLovinSDK.MANativeAdLoader
import cocoapods.AppLovinSDK.MANativeAdView
import com.haitrvn.kal.core.Ad
import com.haitrvn.kal.initialization.AppLovinSdk
import com.haitrvn.kal.model.ReviewAd
import com.haitrvn.kal.rewarded.toCommonError
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import platform.darwin.NSObject
import kotlin.coroutines.resume

@OptIn(ExperimentalForeignApi::class)
actual class NativeAdView(
    val ios: MANativeAdView
)

@OptIn(ExperimentalForeignApi::class)
actual class MAd(
    val ios: MAAd
)

@OptIn(ExperimentalForeignApi::class)
actual class NativeLoader actual constructor(
    adUnitId: String,
    appLovinSdk: AppLovinSdk?
) {
    private val ios: MANativeAdLoader by lazy {
        if (appLovinSdk != null) {
            MANativeAdLoader(adUnitId, appLovinSdk.ios)
        } else {
            MANativeAdLoader(adUnitId)
        }
    }

    private val listenerGroup: NativeAdListenerGroup by lazy {
        NativeAdListenerGroup(ios, mutableListOf())
    }

    actual val nativeAdEventFlow: Flow<NativeAdEvent>
        get() = callbackFlow {
            val listener = object : NSObject(), MANativeAdDelegateProtocol {
                override fun didClickNativeAd(ad: MAAd) {
                    trySend(NativeAdEvent.Clicked(MAd(ad)))
                }

                override fun didFailToLoadNativeAdForAdUnitIdentifier(
                    adUnitIdentifier: String,
                    withError: MAError
                ) {
                    trySend(NativeAdEvent.LoadFailed(adUnitIdentifier, withError.toCommonError()))
                }

                override fun didLoadNativeAd(nativeAdView: MANativeAdView?, forAd: MAAd) {
                    trySend(
                        NativeAdEvent.Loaded(
                            nativeAdView?.let { NativeAdView(it) },
                            MAd(forAd)
                        )
                    )
                }
            }
            listenerGroup.listenerList.add(listener)
            awaitClose {
                ios.nativeAdDelegate = null
                listenerGroup.listenerList.remove(listener)
            }
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

    actual suspend fun loadAd(): NativeAdView? {
        return suspendCancellableCoroutine { continuation ->
            val listener = object : NSObject(), MANativeAdDelegateProtocol {
                override fun didClickNativeAd(ad: MAAd) {

                }

                override fun didFailToLoadNativeAdForAdUnitIdentifier(
                    adUnitIdentifier: String,
                    withError: MAError
                ) {
                    continuation.resume(null)
                }

                override fun didLoadNativeAd(nativeAdView: MANativeAdView?, forAd: MAAd) {
                    continuation.resume(nativeAdView?.let { NativeAdView(it) })
                }
            }
            listenerGroup.listenerList.add(listener)
            continuation.invokeOnCancellation {
                listenerGroup.listenerList.remove(listener)
            }
            ios.loadAd()
        }
    }

    actual suspend fun loadAd(ad: NativeAdView): NativeAdView? {
        return suspendCancellableCoroutine { continuation ->
            val listener = object : NSObject(), MANativeAdDelegateProtocol {
                override fun didClickNativeAd(ad: MAAd) {
                }

                override fun didFailToLoadNativeAdForAdUnitIdentifier(
                    adUnitIdentifier: String,
                    withError: MAError
                ) {
                    continuation.resume(null)
                }

                override fun didLoadNativeAd(nativeAdView: MANativeAdView?, forAd: MAAd) {
                    continuation.resume(nativeAdView?.let { NativeAdView(it) })
                }
            }
            listenerGroup.listenerList.add(listener)
            continuation.invokeOnCancellation {
                listenerGroup.listenerList.remove(listener)
            }
            ios.loadAdIntoAdView(ad.ios)
        }
    }

    actual fun destroy(mAd: MAd) {
        ios.destroyAd(mAd.ios)
    }

    actual fun destroy() {
    }

    actual fun setLocalExtraParameter(key: String, value: String) {
        ios.setLocalExtraParameterForKey(key, value)
    }

    actual fun setExtraParameter(key: String, value: String) {
        ios.setExtraParameterForKey(key, value)
    }

    actual fun setPlacement(placement: String) {
        ios.placement = placement
    }

    actual fun render(adView: NativeAdView, ad: MAd): Boolean {
        return ios.renderNativeAdView(adView.ios, ad.ios)
    }

    actual val unitId: String
        get() = TODO("Not yet implemented")
}