package com.haitrvn.kal.native

import com.haitrvn.kal.core.Ad
import com.haitrvn.kal.initialization.AppLovinSdk
import com.haitrvn.kal.model.ReviewAd
import kotlinx.coroutines.flow.Flow
import cocoapods.AppLovinSDK.MAAd
import cocoapods.AppLovinSDK.MAAdExpirationDelegateProtocol
import cocoapods.AppLovinSDK.MAAdRequestDelegateProtocol
import cocoapods.AppLovinSDK.MAAdRevenueDelegateProtocol
import cocoapods.AppLovinSDK.MAAdReviewDelegateProtocol
import cocoapods.AppLovinSDK.MAError
import cocoapods.AppLovinSDK.MAReward
import cocoapods.AppLovinSDK.MANativeAdView
import cocoapods.AppLovinSDK.MARewardedAd
import cocoapods.AppLovinSDK.MARewardedAdDelegateProtocol
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import platform.darwin.NSObject

actual class NativeAdView(
    val ios: MANativeAdView
)

actual class MAd(
    val ios: MAAd
)

actual class NativeLoader actual constructor(
    adUnitId: String,
    appLovinSdk: AppLovinSdk?
) {
    private val ios: MANativeAdLoader by lazy {
        if (appLovinSdk != null) {
            MANativeAdLoader.initWithAdUnitIdentifier(adUnitId, appLovinSdk.ios)
        } else {
            MANativeAdLoader.initWithAdUnitIdentifier(adUnitId)
        }
    }

    actual val nativeAdEventFlow: Flow<NativeAdEvent>
        get() = TODO("Not yet implemented")

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
                        trySend(ReviewAd(creativeIdentifier,Ad(forAd)))
                    }
                }
            awaitClose { ios.adReviewDelegate = null }
        }

    actual suspend fun loadAd(): NativeAdView? {
        TODO("Not yet implemented")
    }

    actual suspend fun loadAd(ad: NativeAdView): NativeAdView? {
        TODO("Not yet implemented")
    }

    actual fun destroy(mAd: MAd) {
        ios.destroy(mAd.ios)
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
        return ios.renderNativeAdView(adView.ios. ad.ios)
    }
}