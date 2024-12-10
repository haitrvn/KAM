package com.haitrvn.kamsample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haitrvn.kam.FullScreenContent
import com.haitrvn.kam.appopen.AppOpen
import com.haitrvn.kam.interstitial.Interstitial
import com.haitrvn.kam.reward.Rewarded
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.getAndUpdate
import kotlinx.coroutines.launch

class SampleViewModel : ViewModel() {
    private val _state = MutableStateFlow<UiState>(UiState.LOADING)
    val state: StateFlow<UiState> = _state.asStateFlow()

    init {
        loadInterstitial()
        loadAppOpen()
        loadRewarded()
    }

    private fun loadInterstitial() {
        viewModelScope.launch {
            _state.getAndUpdate { it.copy(isInterstitialLoading = true) }
            val interstitial =
                Interstitial.load(AdUnitId.INTERSTITIAL, com.haitrvn.kam.AdRequest.createInstance())
            interstitial?.let { loadedInterstitial ->
                _state.getAndUpdate {
                    it.copy(isInterstitialLoading = false, interstitial = loadedInterstitial)
                }
                loadedInterstitial.fullScreenContentFlow.collectLatest {
                    println("Interstitial fullScreenContentFlow: $it")
                    when (it) {
                        FullScreenContent.Dismissed,
                        is FullScreenContent.ShowFailed -> loadInterstitial()

                        else -> {}
                    }
                }
            }
        }
    }

    private fun loadAppOpen() {
        viewModelScope.launch {
            _state.getAndUpdate { it.copy(isAppOpenLoading = true) }
            val interstitial =
                AppOpen.load(AdUnitId.APP_OPEN, com.haitrvn.kam.AdRequest.createInstance())
            interstitial?.let { appOpen ->
                _state.getAndUpdate {
                    it.copy(isAppOpenLoading = false, appOpen = appOpen)
                }
                appOpen.fullScreenContentFlow.collectLatest {
                    println("AppOpen fullScreenContentFlow: $it")
                    when (it) {
                        FullScreenContent.Dismissed,
                        is FullScreenContent.ShowFailed -> loadAppOpen()

                        else -> {}
                    }
                }
            }
        }
    }

    private fun loadRewarded() {
        viewModelScope.launch {
            _state.getAndUpdate { it.copy(isRewardedLoading = true) }
            val interstitial =
                Rewarded.load(AdUnitId.REWARDED, com.haitrvn.kam.AdRequest.createInstance())
            interstitial?.let { rewarded ->
                _state.getAndUpdate {
                    it.copy(isRewardedLoading = false, rewarded = rewarded)
                }
                rewarded.fullScreenContentFlow.collectLatest {
                    println("Rewarded fullScreenContentFlow: $it")
                    when (it) {
                        FullScreenContent.Dismissed,
                        is FullScreenContent.ShowFailed -> loadRewarded()

                        else -> {}
                    }
                }
            }
        }
    }
}