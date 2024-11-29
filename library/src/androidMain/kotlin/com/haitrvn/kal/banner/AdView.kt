package com.haitrvn.kal.banner

import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxAdViewAdListener
import com.applovin.mediation.MaxError
import com.haitrvn.kal.core.Ad
import com.haitrvn.kal.initialization.AppLovinSdk
import com.haitrvn.kal.model.AdEvent
import com.haitrvn.kal.model.ReviewAd
import com.haitrvn.kal.util.ContextProvider
import com.haitrvn.kal.util.toCommonError
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import com.applovin.mediation.ads.MaxAdView
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

actual class AdView actual constructor(
    adUnitId: String,
    private val adFormat: AdFormat?,
    sdk: AppLovinSdk?,
) {
    val android: MaxAdView by lazy {
        when {
            adFormat != null && sdk != null -> MaxAdView(
                adUnitId,
                adFormat.android,
                sdk.android,
                ContextProvider.context
            )

            adFormat != null -> MaxAdView(
                adUnitId,
                adFormat.android,
                ContextProvider.context
            )

            sdk != null -> MaxAdView(
                adUnitId,
                sdk.android,
                ContextProvider.context
            )

            else -> MaxAdView(
                adUnitId,
                ContextProvider.context
            )
        }
    }
    private val listenerGroup: BannerAdListenerGroup by lazy {
        BannerAdListenerGroup(android, mutableListOf())
    }
    actual val unitId: String
        get() = android.adUnitId

    actual val reviewFlow: Flow<ReviewAd>
        get() = callbackFlow {
            android.setAdReviewListener { id, maxAd ->
                ReviewAd(id, Ad(maxAd))
            }
            awaitClose { android.setAdReviewListener(null) }
        }

    actual val revenueFlow: Flow<Ad>
        get() = callbackFlow {
            android.setRevenueListener {
                trySend(Ad(it))
            }
            awaitClose { android.setRevenueListener(null) }
        }

    actual val requestFlow: Flow<String>
        get() = callbackFlow {
            android.setRequestListener {
                trySend(it)
            }
            awaitClose { android.setRequestListener(null) }
        }

    actual val adEventFlow: Flow<AdEvent>
        get() = callbackFlow {
            val listener = object : MaxAdViewAdListener {
                override fun onAdLoaded(ad: MaxAd) {
                    trySend(AdEvent.Loaded(Ad(ad)))
                }

                override fun onAdDisplayed(ad: MaxAd) {
                    trySend(AdEvent.Displayed(Ad(ad)))
                }

                override fun onAdHidden(ad: MaxAd) {
                    trySend(AdEvent.Hidden(Ad(ad)))
                }

                override fun onAdClicked(ad: MaxAd) {
                    trySend(AdEvent.Clicked(Ad(ad)))
                }

                override fun onAdLoadFailed(message: String, error: MaxError) {
                    trySend(AdEvent.LoadFailed(message, error.toCommonError()))
                }

                override fun onAdDisplayFailed(ad: MaxAd, error: MaxError) {
                    trySend(AdEvent.DisplayFailed(Ad(ad), error.toCommonError()))
                }

                override fun onAdExpanded(ad: MaxAd) {
                    trySend(AdEvent.Expanded(Ad(ad)))
                }

                override fun onAdCollapsed(ad: MaxAd) {
                    trySend(AdEvent.Collapsed(Ad(ad)))
                }
            }
            listenerGroup.listenerList.add(listener)
            awaitClose {
                android.setListener(null)
                listenerGroup.listenerList.remove(listener)
            }
        }

    actual fun destroy() {
        android.destroy()
    }

    actual fun getAdFormat(): AdFormat? {
        return adFormat
    }

    actual fun getPlacement(): String? {
        return android.placement
    }

    actual suspend fun loadAd(): AdView? {
        return suspendCancellableCoroutine { cancellableContinuation ->
            val listener = object : MaxAdViewAdListener {
                override fun onAdLoaded(ad: MaxAd) {
                    cancellableContinuation.resume(this@AdView)
                }

                override fun onAdDisplayed(ad: MaxAd) {
                }

                override fun onAdHidden(ad: MaxAd) {
                }

                override fun onAdClicked(ad: MaxAd) {
                }

                override fun onAdLoadFailed(message: String, error: MaxError) {
                    cancellableContinuation.resume(null)
                }

                override fun onAdDisplayFailed(ad: MaxAd, error: MaxError) {
                }

                override fun onAdExpanded(ad: MaxAd) {
                }

                override fun onAdCollapsed(ad: MaxAd) {
                }
            }
            listenerGroup.listenerList.add(listener)
            cancellableContinuation.invokeOnCancellation {
                listenerGroup.listenerList.remove(listener)
            }
            android.loadAd()
        }
    }

    actual fun setAlpha(alpha: Float) {
        android.alpha = alpha
    }

    actual fun setBackgroundColor() {
    }

    actual fun setCustomData(data: String) {
        android.setCustomData(data)
    }

    actual fun setExtraParameter(param: String, data: String) {
        android.setExtraParameter(param, data)
    }

    actual fun setLocalExtraParameter(param: String, data: Any) {
        android.setLocalExtraParameter(param, data)
    }

    actual fun setPlacement(placement: String) {
        android.placement = placement
    }

    actual fun startAutoRefresh() {
        android.startAutoRefresh()
    }

    actual fun stopAutoRefresh() {
        android.stopAutoRefresh()
    }
}