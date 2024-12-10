package com.haitrvn.sample

import com.haitrvn.kam.appopen.AppOpen
import com.haitrvn.kam.interstitial.Interstitial
import com.haitrvn.kam.reward.Rewarded

data class UiState(
    val interstitial: Interstitial? = null,
    val appOpen: AppOpen? = null,
    val rewarded: Rewarded? = null,
    val isInterstitialLoading: Boolean,
    val isAppOpenLoading: Boolean,
    val isRewardedLoading: Boolean,
) {
    companion object {
        val LOADING = UiState(
            isInterstitialLoading = true,
            isAppOpenLoading = true,
            isRewardedLoading = true,
        )
    }
}
