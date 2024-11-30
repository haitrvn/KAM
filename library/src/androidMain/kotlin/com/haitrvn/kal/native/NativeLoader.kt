package com.haitrvn.kal.native

import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxError
import com.applovin.mediation.nativeAds.MaxNativeAdListener
import com.applovin.mediation.nativeAds.MaxNativeAdLoader
import com.applovin.mediation.nativeAds.MaxNativeAdView
import com.haitrvn.kal.core.Ad
import com.haitrvn.kal.initialization.AppLovinSdk
import com.haitrvn.kal.model.ReviewAd
import com.haitrvn.kal.util.ContextProvider
import com.haitrvn.kal.util.toCommonError
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

actual class NativeAdView(
    val android: MaxNativeAdView
)

actual class MAd(
    val android: MaxAd
)

actual class NativeLoader actual constructor(
    adUnitId: String,
    appLovinSdk: AppLovinSdk?
) {
    private val android: MaxNativeAdLoader by lazy {
        if (appLovinSdk != null) {
            MaxNativeAdLoader(adUnitId, appLovinSdk.android, ContextProvider.context)
        } else {
            MaxNativeAdLoader(adUnitId, ContextProvider.context)
        }
    }

    private val listenerGroup: NativeAdListenerGroup by lazy {
        NativeAdListenerGroup(android, mutableListOf())
    }

    actual val unitId: String
        get() = android.adUnitId

    actual val nativeAdEventFlow: Flow<NativeAdEvent>
        get() = callbackFlow {
            val listener = object : MaxNativeAdListener() {
                override fun onNativeAdLoaded(p0: MaxNativeAdView?, p1: MaxAd) {
                    super.onNativeAdLoaded(p0, p1)
                    trySend(NativeAdEvent.Loaded(p0?.let { NativeAdView(it) }, MAd(p1)))
                }

                override fun onNativeAdLoadFailed(p0: String, p1: MaxError) {
                    super.onNativeAdLoadFailed(p0, p1)
                    trySend(NativeAdEvent.LoadFailed(p0, p1.toCommonError()))
                }

                override fun onNativeAdClicked(p0: MaxAd) {
                    super.onNativeAdClicked(p0)
                    trySend(NativeAdEvent.Clicked(MAd(p0)))
                }

                override fun onNativeAdExpired(p0: MaxAd) {
                    super.onNativeAdExpired(p0)
                    trySend(NativeAdEvent.Expired(MAd(p0)))
                }
            }
            listenerGroup.listenerList.add(listener)
            awaitClose {
                android.setNativeAdListener(null)
                listenerGroup.listenerList.remove(listener)
            }
        }

    actual val reviewFlow: Flow<ReviewAd>
        get() = callbackFlow {
            android.setAdReviewListener { id, maxAd ->
                trySend(ReviewAd(id, Ad(maxAd)))
            }
            awaitClose {
                android.setAdReviewListener(null)
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

    actual suspend fun loadAd(): NativeAdView? {
        return suspendCancellableCoroutine { cancellableContinuation ->
            val listener = object : MaxNativeAdListener() {
                override fun onNativeAdLoaded(p0: MaxNativeAdView?, p1: MaxAd) {
                    super.onNativeAdLoaded(p0, p1)
                    cancellableContinuation.resume(p0?.let { NativeAdView(it) })
                }

                override fun onNativeAdLoadFailed(p0: String, p1: MaxError) {
                    super.onNativeAdLoadFailed(p0, p1)
                    cancellableContinuation.resume(null)
                }
            }
            listenerGroup.listenerList.add(listener)
            cancellableContinuation.invokeOnCancellation {
                listenerGroup.listenerList.remove(listener)
            }
            android.loadAd()
        }
    }

    actual suspend fun loadAd(ad: NativeAdView): NativeAdView? {
        return suspendCancellableCoroutine { cancellableContinuation ->
            val listener = object : MaxNativeAdListener() {
                override fun onNativeAdLoaded(p0: MaxNativeAdView?, p1: MaxAd) {
                    super.onNativeAdLoaded(p0, p1)
                    cancellableContinuation.resume(p0?.let { NativeAdView(it) })
                }

                override fun onNativeAdLoadFailed(p0: String, p1: MaxError) {
                    super.onNativeAdLoadFailed(p0, p1)
                    cancellableContinuation.resume(null)
                }
            }
            listenerGroup.listenerList.add(listener)
            cancellableContinuation.invokeOnCancellation {
                listenerGroup.listenerList.remove(
                    listener
                )
            }
            android.loadAd(ad.android)
        }
    }

    actual fun destroy(mAd: MAd) {
        android.destroy(mAd.android)
    }

    actual fun destroy() {
        android.destroy()
    }

    actual fun setLocalExtraParameter(key: String, value: String) {
        android.setLocalExtraParameter(key, value)
    }

    actual fun setExtraParameter(key: String, value: String) {
        android.setExtraParameter(key, value)
    }

    actual fun setPlacement(placement: String) {
        android.placement = placement
    }

    actual fun render(adView: NativeAdView, ad: MAd): Boolean {
        return android.render(adView.android, ad.android)
    }
}