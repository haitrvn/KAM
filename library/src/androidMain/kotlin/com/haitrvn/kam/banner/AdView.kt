package com.haitrvn.kam.banner

import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.LoadAdError
import com.haitrvn.kam.AdError
import com.haitrvn.kam.AdEvent
import com.haitrvn.kam.AdRequest
import com.haitrvn.kam.AdValue
import com.haitrvn.kam.ResponseInfo
import com.haitrvn.kam.toCommon
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import com.google.android.gms.ads.AdView as AndroidAdView

actual class AdView(
    private val android: AndroidAdView
) {
    actual var unitId: String
        get() = android.adUnitId
        set(value) {
            android.adUnitId = value
        }

    actual val responseInfo: ResponseInfo?
        get() = android.responseInfo?.toCommon()

    actual val paidEventFlow: Flow<AdValue>
        get() = callbackFlow {
            android.setOnPaidEventListener {
                trySend(it.toCommon())
            }
            awaitClose { android.onPaidEventListener = null }
        }

    actual val adSize: AdSize?
        get() = android.adSize?.let { AdSize(it) }

    actual val isCollapsible: Boolean
        get() = android.isCollapsible

    actual val isLoading: Boolean
        get() = android.isLoading

    actual val adEventFlow: Flow<AdEvent>
        get() = callbackFlow {
            android.adListener = object : AdListener() {
                override fun onAdClicked() {
                    super.onAdClicked()
                    trySend(AdEvent.Clicked)
                }

                override fun onAdClosed() {
                    super.onAdClosed()
                    trySend(AdEvent.Closed)
                }

                override fun onAdFailedToLoad(p0: LoadAdError) {
                    super.onAdFailedToLoad(p0)
                    trySend(AdEvent.FailedToLoad(AdError(p0)))
                }

                override fun onAdImpression() {
                    super.onAdImpression()
                    trySend(AdEvent.Impression)
                }

                override fun onAdLoaded() {
                    super.onAdLoaded()
                    trySend(AdEvent.Loaded)
                }

                override fun onAdOpened() {
                    super.onAdOpened()
                    trySend(AdEvent.Opened)
                }

                override fun onAdSwipeGestureClicked() {
                    super.onAdSwipeGestureClicked()
                    trySend(AdEvent.SwipeGestureClicked)
                }
            }
        }

    actual fun destroy() {
        android.destroy()
    }

    actual suspend fun load(request: AdRequest) {
        android.loadAd(request.android)
    }

    actual fun resume() {
        android.resume()
    }

    actual fun pause() {
        android.pause()
    }

    actual fun setAdSize(adSize: AdSize) {
        android.setAdSize(adSize.android)
    }
}