package com.haitrvn.sample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haitrvn.kam.AdRequest
import com.haitrvn.kam.appopen.AppOpen
import com.haitrvn.kam.interstitial.Interstitial
import com.haitrvn.kam.reward.Rewarded
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SampleViewModel : ViewModel() {
    private val _state = MutableStateFlow<InterstitialContract>(InterstitialContract.Unknown)
    val state: StateFlow<InterstitialContract> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { InterstitialContract.Loading }
            Interstitial.load(
                AdUnitId.INTERSTITIAL,
                AdRequest.createInstance()
            )?.let { interstitial ->
                if(_state.value is InterstitialContract.Loaded) {
                    _state.update { (_state.value as InterstitialContract.Loaded).copy(interstitial = interstitial) }
                } else {
                    _state.update { InterstitialContract.Loaded(interstitial = interstitial) }
                }
            }
        }
        viewModelScope.launch {
            AppOpen.load(
                AdUnitId.APP_OPEN,
                AdRequest.createInstance()
            )?.let { appOpen ->
                if(_state.value is InterstitialContract.Loaded) {
                    _state.update { (_state.value as InterstitialContract.Loaded).copy(appOpen = appOpen) }
                } else {
                    _state.update { InterstitialContract.Loaded(appOpen = appOpen) }
                }
            }
        }
        viewModelScope.launch {
            Rewarded.load(
                AdUnitId.REWARDED,
                AdRequest.createInstance()
            )?.let { rewarded ->
                if(_state.value is InterstitialContract.Loaded) {
                    _state.update { (_state.value as InterstitialContract.Loaded).copy(rewarded = rewarded) }
                } else {
                    _state.update { InterstitialContract.Loaded(rewarded = rewarded) }
                }
            }
        }
    }

    fun removeInterstitial() {
        _state.update { InterstitialContract.Unknown }
    }
}