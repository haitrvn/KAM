package com.haitrvn.kal.interstitial

import androidx.lifecycle.Lifecycle
import com.haitrvn.kal.core.Ad
import com.haitrvn.kal.core.AdError
import com.haitrvn.kal.core.Reward
import com.haitrvn.kal.core.RootView
import com.haitrvn.kal.initialization.AppLovinSdk
import com.haitrvn.kal.rewarded.ViewGroup

expect class InterstitialAd(
    adUnitId: String, appLovinSdk: AppLovinSdk? = null
) {
    fun loadAd()
    fun setExtraParameter(key: String, value: String)
    internal fun setLocalExtraParameter(key: String, param: Any)
    fun showAd(rootView: RootView)
    fun showAd(placement: String, rootView: RootView)
    fun showAd(placement: String, customData: String, rootView: RootView)
    internal fun showAd(viewGroup: ViewGroup, lifecycle: Lifecycle, rootView: RootView)
    internal fun showAd(
        placement: String, viewGroup: ViewGroup, lifecycle: Lifecycle, rootView: RootView
    )

    internal fun showAd(
        placement: String,
        customData: String,
        viewGroup: ViewGroup,
        lifecycle: Lifecycle,
        rootView: RootView
    )

    fun destroy()
}

data class InterstitialAdStates(

)

sealed interface ExpirationState {
    data object Unknow: ExpirationState
    data class ExpiredAdReloaded(
        val ad1: Ad,
        val ad2: Ad,
    ): ExpirationState
}

sealed interface ReviewState {
    data object NotYetAwarded : ReviewState
    data class Rewarded(
        val ad: Ad,
        val reward: Reward,
    ) : ReviewState
}

sealed interface ViewAdState {
    data class AdLoaded(val adMax: Ad) : ViewAdState
    data class AdDisplayed(val adMax: Ad) : ViewAdState
    data class AdHidden(val adMax: Ad) : ViewAdState
    data class AdClicked(val adMax: Ad) : ViewAdState
    data class AdLoadFailed(
        val value: String,
        val error: AdError,
    ) : ViewAdState

    data class AdDisplayFailed(
        val adMax: Ad,
        val error: AdError,
    ) : ViewAdState
}

sealed interface RequestState {
    data object NotStartedYet : RequestState
    data class Started(
        val value: String,
    ) : RequestState
}

sealed interface RevenueState {
    data object UnPaid : RevenueState
    data class Paid(
        val revenue: Double, val precision: String
    ) : RevenueState
}