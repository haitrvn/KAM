package com.haitrvn.sample

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haitrvn.kam.AdRequest
import com.haitrvn.kam.interstitial.Interstitial
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
                Config.INTERSTITIAL_AD_UNIT_ID,
                AdRequest.createInstance()
            )?.let { interstitial ->
                _state.update { InterstitialContract.Loaded(interstitial) }
            }
        }
    }

    fun removeInterstitial() {
        _state.update { InterstitialContract.Unknown }
    }
}

/**
 * class SampleViewModel : ViewModel() {
 *     private val _state = MutableStateFlow<InterstitialContract>(InterstitialContract.Unknown)
 *     val state: StateFlow<InterstitialContract> = _state.asStateFlow()
 *
 *     private var currentInterstitial: Interstitial? = null
 *
 *     init {
 *         viewModelScope.launch {
 *             _state.update { InterstitialContract.Loading }
 *             Interstitial.load(
 *                 Config.INTERSTITIAL_AD_UNIT_ID,
 *                 AdRequest.createInstance()
 *             )?.let { interstitial ->
 *                 currentInterstitial = interstitial
 *
 *                 // Theo dõi fullScreenContentFlow
 *                 interstitial.fullScreenContentFlow.collect { fullScreenContent ->
 *                     when (fullScreenContent) {
 *                         is FullScreenContent.Shown -> {
 *                             _state.update { InterstitialContract.Displayed }
 *                         }
 *                         is FullScreenContent.Dismissed -> {
 *                             _state.update { InterstitialContract.Unknown }
 *                             removeInterstitial()
 *                         }
 *                         is FullScreenContent.Failed -> {
 *                             _state.update { InterstitialContract.Error(fullScreenContent.error) }
 *                         }
 *                     }
 *                 }
 *
 *                 // Theo dõi responseInfo
 *                 _state.update {
 *                     InterstitialContract.Loaded(
 *                         interstitial = interstitial,
 *                         responseInfo = interstitial.responseInfo
 *                     )
 *                 }
 *             }
 *         }
 *     }
 *
 *     fun showInterstitial() {
 *         currentInterstitial?.show()
 *     }
 *
 *     fun removeInterstitial() {
 *         currentInterstitial = null
 *         _state.update { InterstitialContract.Unknown }
 *     }
 * }
 *
 * // Mở rộng sealed class để hỗ trợ nhiều trạng thái
 * sealed class InterstitialContract {
 *     object Unknown : InterstitialContract()
 *     object Loading : InterstitialContract()
 *     data class Loaded(
 *         val interstitial: Interstitial,
 *         val responseInfo: ResponseInfo? = null
 *     ) : InterstitialContract()
 *     object Displayed : InterstitialContract()
 *     data class Error(val throwable: Throwable) : InterstitialContract()
 * }
 */