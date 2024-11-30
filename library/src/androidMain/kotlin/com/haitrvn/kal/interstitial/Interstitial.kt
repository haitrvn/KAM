package com.haitrvn.kal.interstitial

import androidx.lifecycle.Lifecycle
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxAdListener
import com.applovin.mediation.MaxError
import com.applovin.mediation.ads.MaxInterstitialAd
import com.haitrvn.kal.core.Ad
import com.haitrvn.kal.core.RootView
import com.haitrvn.kal.initialization.AppLovinSdk
import com.haitrvn.kal.model.AdEvent
import com.haitrvn.kal.model.ReviewAd
import com.haitrvn.kal.rewarded.ViewGroup
import com.haitrvn.kal.util.ContextProvider
import com.haitrvn.kal.util.toCommonError
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

actual class InterstitialAd actual constructor(
    adUnitId: String,
    appLovinSdk: AppLovinSdk?
) {
    private val android by lazy {
        if (appLovinSdk != null) {
            MaxInterstitialAd(
                adUnitId,
                appLovinSdk.android,
                ContextProvider.context
            )
        } else {
            MaxInterstitialAd(adUnitId, ContextProvider.context)
        }
    }
    private val listenerGroup: NativeAdListenerGroup by lazy {
        NativeAdListenerGroup(android, mutableListOf())
    }

    actual val unitId: String
        get() = android.adUnitId

    actual val isReady: Boolean
        get() = android.isReady

    actual val reviewFlow: Flow<ReviewAd>
        get() = callbackFlow {
            android.setAdReviewListener { id, maxAd ->
                ReviewAd(id, Ad(maxAd))
            }
            awaitClose { android.setAdReviewListener(null) }
        }

    actual val expirationFlow: Flow<Pair<Ad, Ad>>
        get() = callbackFlow {
            android.setExpirationListener { old, new ->
                trySend(Ad(old) to Ad(new))
            }
            awaitClose { android.setExpirationListener(null) }
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
            val listener = object : MaxAdListener {
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
            }
            listenerGroup.listenerList.add(listener)
            awaitClose {
                android.setListener(null)
                listenerGroup.listenerList.remove(listener)
            }
        }

    actual suspend fun loadAd(): InterstitialAd? {
        return suspendCancellableCoroutine { continuation ->
            val listener = object : MaxAdListener {
                override fun onAdLoaded(ad: MaxAd) {
                    continuation.resume(this@InterstitialAd)
                }

                override fun onAdDisplayed(ad: MaxAd) {
                }

                override fun onAdHidden(ad: MaxAd) {
                }

                override fun onAdClicked(ad: MaxAd) {
                }

                override fun onAdLoadFailed(message: String, error: MaxError) {
                    continuation.resume(null)
                }

                override fun onAdDisplayFailed(ad: MaxAd, error: MaxError) {
                }
            }
            listenerGroup.listenerList.add(listener)
            continuation.invokeOnCancellation { listenerGroup.listenerList.remove(listener) }
            android.loadAd()
        }
    }

    actual fun setExtraParameter(key: String, value: String) {
        android.setExtraParameter(key, value)
    }

    internal actual fun setLocalExtraParameter(key: String, param: Any) {
        android.setLocalExtraParameter(key, param)
    }

    actual fun showAd(rootView: RootView) {
        android.showAd(rootView)
    }

    actual fun showAd(placement: String, rootView: RootView) {
        android.showAd(placement, rootView)
    }

    actual fun showAd(placement: String, customData: String, rootView: RootView) {
        android.showAd(placement, customData, rootView)
    }

    actual fun showAd(viewGroup: ViewGroup, lifecycle: Lifecycle, rootView: RootView) {
        android.showAd(viewGroup.android, lifecycle, rootView)
    }

    actual fun showAd(
        placement: String,
        viewGroup: ViewGroup,
        lifecycle: Lifecycle,
        rootView: RootView
    ) {
        android.showAd(placement, viewGroup.android, lifecycle, rootView)
    }

    actual fun showAd(
        placement: String,
        customData: String,
        viewGroup: ViewGroup,
        lifecycle: Lifecycle,
        rootView: RootView
    ) {
        android.showAd(placement, customData, viewGroup.android, lifecycle, rootView)
    }

    actual fun destroy() {
        android.destroy()
    }
}