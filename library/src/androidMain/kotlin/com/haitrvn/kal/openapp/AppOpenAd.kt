package com.haitrvn.kal.openapp

import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxAdListener
import com.applovin.mediation.MaxError
import com.applovin.mediation.ads.MaxAppOpenAd
import com.haitrvn.kal.core.Ad
import com.haitrvn.kal.initialization.AppLovinSdk
import com.haitrvn.kal.model.AdEvent
import com.haitrvn.kal.model.ReviewAd
import com.haitrvn.kal.util.ContextProvider
import com.haitrvn.kal.util.toCommonError
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

actual class AppOpenAd actual constructor(
    adUnitId: String,
    appLovinSdk: AppLovinSdk?
) {
    private val android: MaxAppOpenAd by lazy {
        if (appLovinSdk != null) {
            MaxAppOpenAd(adUnitId, appLovinSdk.android)
        } else {
            MaxAppOpenAd(adUnitId, ContextProvider.context)
        }
    }

    private val listenerGroup: OpenAdListenerGroup by lazy {
        OpenAdListenerGroup(android, mutableListOf())
    }

    actual val isReady: Boolean
        get() = android.isReady

    actual val unitId: String
        get() = android.adUnitId

    actual val reviewFlow: Flow<ReviewAd>
        get() = callbackFlow {
            android.setAdReviewListener { id, maxAd ->
                trySend(ReviewAd(id, Ad(maxAd)))
            }
            awaitClose {
                android.setAdReviewListener(null)
            }
        }

    actual val expirationFlow: Flow<Pair<Ad, Ad>>
        get() = callbackFlow {
            android.setExpirationListener { oldAd, newAd ->
                trySend(Ad(oldAd) to Ad(newAd))
            }
            awaitClose {
                android.setExpirationListener(null)
            }
        }

    actual val revenueFlow: Flow<Ad>
        get() = callbackFlow {
            android.setRevenueListener { ad ->
                trySend(Ad(ad))
            }
            awaitClose {
                android.setRevenueListener(null)
            }
        }

    actual val requestFlow: Flow<String>
        get() = callbackFlow {
            android.setRequestListener {
                trySend(it)
            }
            awaitClose {
                android.setRequestListener(null)
            }
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

    actual suspend fun loadAd(): AppOpenAd? {
        return suspendCancellableCoroutine { cancellableContinuation ->
            val listener = object : MaxAdListener {
                override fun onAdLoaded(ad: MaxAd) {
                    cancellableContinuation.resume(this@AppOpenAd)
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
            }
            listenerGroup.listenerList.add(listener)
            cancellableContinuation.invokeOnCancellation {
                listenerGroup.listenerList.remove(listener)
            }
            android.loadAd()
        }
    }

    actual fun setExtraParameter(key: String, value: String) {
        android.setExtraParameter(key, value)
    }

    internal actual fun setLocalExtraParameter(key: String, param: Any) {
        android.setLocalExtraParameter(key, param)
    }

    actual fun showAd() {
        android.showAd()
    }

    actual fun showAd(placement: String) {
        android.showAd(placement)
    }

    actual fun showAd(placement: String, customData: String) {
        android.showAd(placement, customData)
    }

    actual fun destroy() {
        android.destroy()
    }
}