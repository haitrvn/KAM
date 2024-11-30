package com.haitrvn.kal.native

import cocoapods.AppLovinSDK.MAAd
import cocoapods.AppLovinSDK.MAError
import cocoapods.AppLovinSDK.MANativeAdDelegateProtocol
import cocoapods.AppLovinSDK.MANativeAdLoader
import cocoapods.AppLovinSDK.MANativeAdView
import kotlinx.cinterop.ExperimentalForeignApi
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class)
class NativeAdListenerGroup(
    loader: MANativeAdLoader,
    val listenerList: MutableList<MANativeAdDelegateProtocol>
) {
    init {
        loader.nativeAdDelegate = object : NSObject(), MANativeAdDelegateProtocol {
            override fun didClickNativeAd(ad: MAAd) {
                listenerList.forEach { it.didClickNativeAd(ad) }
            }

            override fun didFailToLoadNativeAdForAdUnitIdentifier(
                adUnitIdentifier: String,
                withError: MAError
            ) {
                listenerList.forEach {
                    it.didFailToLoadNativeAdForAdUnitIdentifier(
                        adUnitIdentifier,
                        withError
                    )
                }
            }

            override fun didLoadNativeAd(nativeAdView: MANativeAdView?, forAd: MAAd) {
                listenerList.forEach { it.didLoadNativeAd(nativeAdView, forAd) }
            }
        }
    }
}