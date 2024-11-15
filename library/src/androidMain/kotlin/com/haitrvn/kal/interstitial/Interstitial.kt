package com.haitrvn.kal.interstitial

import androidx.compose.runtime.MutableState
import androidx.lifecycle.Lifecycle
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxAdListener
import com.applovin.mediation.MaxError
import com.applovin.mediation.ads.MaxInterstitialAd
import com.haitrvn.kal.core.RootView
import com.haitrvn.kal.initialization.AppLovinSdk
import com.haitrvn.kal.rewarded.ViewGroup
import com.haitrvn.kal.util.ContextProvider

actual class InterstitialAd actual constructor(
    private val adUnitId: String,
    private val appLovinSdk: AppLovinSdk?
): MutableState<InterstitialAdStates> {
    private val interstitial by lazy {
        if (appLovinSdk != null) {
            MaxInterstitialAd(
                adUnitId,
                appLovinSdk.androidAppLovinSdk,
                ContextProvider.applicationContext
            )
        } else {
            MaxInterstitialAd(adUnitId, ContextProvider.applicationContext)
        }
    }

    val states: InterstitialAdStates

    init {
        interstitial.setAdReviewListener { value, ad ->

        }
        interstitial.setExpirationListener { ad1, ad2 ->

        }
        interstitial.setListener(object : MaxAdListener {
            override fun onAdLoaded(p0: MaxAd) {
                TODO("Not yet implemented")
            }

            override fun onAdDisplayed(p0: MaxAd) {
                TODO("Not yet implemented")
            }

            override fun onAdHidden(p0: MaxAd) {
                TODO("Not yet implemented")
            }

            override fun onAdClicked(p0: MaxAd) {
                TODO("Not yet implemented")
            }

            override fun onAdLoadFailed(p0: String, p1: MaxError) {
                TODO("Not yet implemented")
            }

            override fun onAdDisplayFailed(p0: MaxAd, p1: MaxError) {
                TODO("Not yet implemented")
            }
        })
        interstitial.setRequestListener {

        }
        interstitial.setRevenueListener {

        }
    }

    actual fun loadAd() {
        interstitial.loadAd()
    }

    actual fun setExtraParameter(key: String, value: String) {
        interstitial.setExtraParameter(key, value)
    }

    internal actual fun setLocalExtraParameter(key: String, param: Any) {
        interstitial.setLocalExtraParameter(key, param)
    }

    actual fun showAd(rootView: RootView) {
        interstitial.showAd(rootView)
    }

    actual fun showAd(placement: String, rootView: RootView) {
        interstitial.showAd(placement, rootView)
    }

    actual fun showAd(placement: String, customData: String, rootView: RootView) {
        interstitial.showAd(placement, customData, rootView)
    }

    actual fun showAd(viewGroup: ViewGroup, lifecycle: Lifecycle, rootView: RootView) {
        interstitial.showAd(viewGroup, lifecycle, rootView)
    }

    actual fun showAd(
        placement: String,
        viewGroup: ViewGroup,
        lifecycle: Lifecycle,
        rootView: RootView
    ) {
        interstitial.showAd(placement, viewGroup, lifecycle, rootView)
    }

    actual fun showAd(
        placement: String,
        customData: String,
        viewGroup: ViewGroup,
        lifecycle: Lifecycle,
        rootView: RootView
    ) {
        interstitial.showAd(placement, customData, viewGroup, lifecycle, rootView)
    }

    actual fun destroy() {
        interstitial.destroy()
    }

    override var value: InterstitialAdStates
        get() = TODO("Not yet implemented")
        set(value) {}

    override fun component1(): InterstitialAdStates {
        TODO("Not yet implemented")
    }

    override fun component2(): (InterstitialAdStates) -> Unit {
        TODO("Not yet implemented")
    }
}