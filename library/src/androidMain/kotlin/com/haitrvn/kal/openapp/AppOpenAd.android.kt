package com.haitrvn.kal.openapp

import com.applovin.mediation.ads.MaxAppOpenAd
import com.haitrvn.kal.initialization.AppLovinSdk
import com.haitrvn.kal.util.ContextProvider

actual class AppOpenAd actual constructor(
    private val adUnitId: String,
    private val appLovinSdk: AppLovinSdk?
) {
    private val appOpenAd: MaxAppOpenAd by lazy {
        if (appLovinSdk != null) {
            MaxAppOpenAd(adUnitId, appLovinSdk.androidAppLovinSdk)
        } else {
            MaxAppOpenAd(adUnitId, ContextProvider.applicationContext)
        }
    }

    init {

    }
}