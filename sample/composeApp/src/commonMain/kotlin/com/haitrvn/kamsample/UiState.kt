package com.haitrvn.kamsample

import com.haitrvn.kam.appopen.AppOpen
import com.haitrvn.kam.interstitial.Interstitial
import com.haitrvn.kam.native.NativeAd
import com.haitrvn.kam.reward.Rewarded

data class UiState(
    val interstitial: Interstitial? = null,
    val appOpen: AppOpen? = null,
    val rewarded: Rewarded? = null,
    val nativeAd: NativeAd? = null,
    val isInterstitialLoading: Boolean,
    val isAppOpenLoading: Boolean,
    val isRewardedLoading: Boolean,
    val isNativeLoading: Boolean,
) {
    companion object {
        val LOADING = UiState(
            isInterstitialLoading = true,
            isAppOpenLoading = true,
            isRewardedLoading = true,
            isNativeLoading = true,
        )
    }
}
