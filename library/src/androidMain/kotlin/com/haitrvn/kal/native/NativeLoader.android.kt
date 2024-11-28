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
import kotlinx.coroutines.CancellableContinuation
import kotlinx.coroutines.ExperimentalCoroutinesApi
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

@OptIn(ExperimentalCoroutinesApi::class)
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

    private var cancellableContinuation: CancellableContinuation<NativeAdView?>? = null

    actual val nativeAdEventFlow: Flow<NativeAdEvent>
        get() = callbackFlow {
            android.setNativeAdListener(object : MaxNativeAdListener() {
                override fun onNativeAdLoaded(p0: MaxNativeAdView?, p1: MaxAd) {
                    super.onNativeAdLoaded(p0, p1)
                    trySend(NativeAdEvent.Loaded(p0?.let { NativeAdView(it) }, MAd(p1)))
                    cancellableContinuation?.resume(p0?.let { NativeAdView(it) })
                    cancellableContinuation = null
                }

                override fun onNativeAdLoadFailed(p0: String, p1: MaxError) {
                    super.onNativeAdLoadFailed(p0, p1)
                    trySend(NativeAdEvent.LoadFailed(p0, p1.toCommonError()))
                    cancellableContinuation?.resume(null)
                    cancellableContinuation = null
                }

                override fun onNativeAdClicked(p0: MaxAd) {
                    super.onNativeAdClicked(p0)
                    trySend(NativeAdEvent.Clicked(MAd(p0)))
                }

                override fun onNativeAdExpired(p0: MaxAd) {
                    super.onNativeAdExpired(p0)
                    trySend(NativeAdEvent.Expired(MAd(p0)))
                }
            })
            awaitClose { android.setNativeAdListener(null) }
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
            cancellableContinuation.invokeOnCancellation {
                this.cancellableContinuation = null
            }
            this.cancellableContinuation = cancellableContinuation
            android.loadAd()
        }
    }

    actual fun destroy(mAd: MAd) {
        android.destroy(mAd.android)
    }

    actual fun destroy() {
        android.destroy()
    }

    fun setLocalExtraParameter(key: String, value: String) {
        android.setLocalExtraParameter(key, value)
    }

    fun setExtraParameter(key: String, value: String) {
        android.setExtraParameter(key, value)
    }

    fun setPlacement(placement: String) {
        android.placement = placement
    }

    fun render(adView: NativeAdView, ad: MAd) {
        android.render(adView.android, ad.android)
    }

    actual fun setLocalExtraParameter() {
    }

    actual fun setExtraParameter() {
    }

    actual fun setPlacement() {
    }

    actual fun render() {
    }
}