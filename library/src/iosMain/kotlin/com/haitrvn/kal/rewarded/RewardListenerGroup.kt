package com.haitrvn.kal.rewarded

import cocoapods.AppLovinSDK.MAAd
import cocoapods.AppLovinSDK.MAError
import cocoapods.AppLovinSDK.MAReward
import cocoapods.AppLovinSDK.MARewardedAd
import cocoapods.AppLovinSDK.MARewardedAdDelegateProtocol
import kotlinx.cinterop.ExperimentalForeignApi
import platform.darwin.NSObject

@OptIn(ExperimentalForeignApi::class)
class RewardListenerGroup(
    loader: MARewardedAd,
    val listenerList: MutableList<MARewardedAdDelegateProtocol>
) {
    init {
        loader.delegate = object : NSObject(), MARewardedAdDelegateProtocol {
            override fun didRewardUserForAd(ad: MAAd, withReward: MAReward) {
                listenerList.forEach { it.didRewardUserForAd(ad, withReward) }
            }

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