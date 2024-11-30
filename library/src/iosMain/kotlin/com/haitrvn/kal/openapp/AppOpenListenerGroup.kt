package com.haitrvn.kal.openapp

import cocoapods.AppLovinSDK.MAAd
import cocoapods.AppLovinSDK.MAAdDelegateProtocol
import cocoapods.AppLovinSDK.MAAppOpenAd
import cocoapods.AppLovinSDK.MAError
import kotlinx.cinterop.ExperimentalForeignApi
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class)
class AppOpenListenerGroup(
    loader: MAAppOpenAd,
    val listenerList: MutableList<MAAdDelegateProtocol>
) {
    init {
        loader.delegate = object : NSObject(), MAAdDelegateProtocol {
            override fun didClickAd(ad: MAAd) {
                listenerList.forEach { it.didClickAd(ad) }
            }

            override fun didDisplayAd(ad: MAAd) {
                listenerList.forEach { it.didDisplayAd(ad) }
            }

            override fun didFailToDisplayAd(ad: MAAd, withError: MAError) {
                listenerList.forEach { it.didFailToDisplayAd(ad, withError) }
            }

            override fun didFailToLoadAdForAdUnitIdentifier(
                adUnitIdentifier: String,
                withError: MAError
            ) {
                listenerList.forEach {
                    it.didFailToLoadAdForAdUnitIdentifier(
                        adUnitIdentifier,
                        withError
                    )
                }
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