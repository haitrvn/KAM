package com.haitrvn.kal.banner

import cocoapods.AppLovinSDK.MAAd
import cocoapods.AppLovinSDK.MAAdView
import cocoapods.AppLovinSDK.MAAdViewAdDelegateProtocol
import cocoapods.AppLovinSDK.MAError
import kotlinx.cinterop.ExperimentalForeignApi
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class)
class BannerAdListenerGroup(
    loader: MAAdView,
    val listenerList: MutableList<MAAdViewAdDelegateProtocol>
) {
    init {
        loader.delegate = object : NSObject(), MAAdViewAdDelegateProtocol {
            override fun didCollapseAd(ad: MAAd) {
                listenerList.forEach { it.didCollapseAd(ad) }
            }

            override fun didClickAd(ad: MAAd) {
                listenerList.forEach { it.didClickAd(ad) }
            }

            override fun didDisplayAd(ad: MAAd) {
                listenerList.forEach { it.didDisplayAd(ad) }
            }

            override fun didExpandAd(ad: MAAd) {
                listenerList.forEach { it.didExpandAd(ad) }
            }

            override fun didFailToDisplayAd(ad: MAAd, withError: MAError) {
                listenerList.forEach { it.didFailToDisplayAd(ad, withError) }
            }

            override fun didFailToLoadAdForAdUnitIdentifier(
                adUnitIdentifier: String,
                withError: MAError
            ) {
                listenerList.forEach { it.didFailToLoadAdForAdUnitIdentifier(adUnitIdentifier, withError) }
            }

            override fun didHideAd(ad: MAAd) {
                listenerList.forEach { it.didHideAd(ad) }
            }

            override fun didLoadAd(ad: MAAd) {
                listenerList.forEach { it.didLoadAd(ad) }
            }
        }
    }
}