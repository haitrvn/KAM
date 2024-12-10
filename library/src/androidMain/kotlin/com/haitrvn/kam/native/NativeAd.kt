package com.haitrvn.kam.native

import android.os.Bundle
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.nativead.NativeAd.UnconfirmedClickListener
import com.haitrvn.kam.AdError
import com.haitrvn.kam.AdEvent
import com.haitrvn.kam.AdValue
import com.haitrvn.kam.ResponseInfo
import com.haitrvn.kam.toCommon
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import com.google.android.gms.ads.nativead.NativeAd as AndroidNativeAd

actual class NativeAd(
    val android: AndroidNativeAd
) {
    lateinit var listenerGroup: AdListenerGroup

    actual val extras: Any
        get() = android.extras

    actual val mediaContent: MediaContent?
        get() = android.mediaContent?.let { MediaContent(it) }

    actual val responseInfo: ResponseInfo?
        get() = android.responseInfo?.toCommon()

    actual val adChoicesInfo: AdChoicesInfo?
        get() = android.adChoicesInfo?.let { AdChoicesInfo(it) }

    actual val icon: Image?
        get() = android.icon?.let { Image(it) }

    actual val starRating: Double?
        get() = android.starRating

    actual val advertiser: String?
        get() = android.advertiser

    actual val body: String?
        get() = android.body

    actual val callToAction: String?
        get() = android.callToAction

    actual val headline: String?
        get() = android.headline

    actual val price: String?
        get() = android.price

    actual val store: String?
        get() = android.store

    actual val images: List<Image>
        get() = android.images.map { Image(it) }

    actual val muteThisAdReasons: List<MuteThisAdReason>
        get() = android.muteThisAdReasons.map { MuteThisAdReason(it) }

    actual val isCustomMuteThisAdEnabled: Boolean
        get() = android.isCustomMuteThisAdEnabled

    actual val adListenerEvent: Flow<AdEvent> by lazy {
        callbackFlow {
            val listener = object : AdListener() {
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
            listenerGroup.listListener.add(listener)
            awaitClose { listenerGroup.listListener.remove(listener) }
        }
    }

    actual val paidEventFlow: Flow<AdValue>
        get() = callbackFlow {
            android.setOnPaidEventListener {
                trySend(it.toCommon())
            }
            awaitClose { android.setOnPaidEventListener(null) }
        }

    actual val unconfirmedClickFlow: Flow<UnconfirmedClickEvent>
        get() = callbackFlow {
            android.setUnconfirmedClickListener(object : UnconfirmedClickListener {
                override fun onUnconfirmedClickCancelled() {
                    trySend(UnconfirmedClickEvent.Canceled)
                }

                override fun onUnconfirmedClickReceived(p0: String) {
                    trySend(UnconfirmedClickEvent.Received(p0))
                }
            })
        }

    actual val adMutedFlow: Flow<Unit>
        get() = callbackFlow {
            android.setMuteThisAdListener { trySend(Unit) }
        }

    actual fun cancelUnconfirmedClick() {
        android.cancelUnconfirmedClick()
    }

    actual fun destroy() {
        android.destroy()
    }

    actual fun muteThisAd(reason: MuteThisAdReason) {
        android.muteThisAd(reason.android)
    }

    actual fun performClick(bundle: Any) {
        android.performClick(bundle as Bundle)
    }

    actual fun reportTouchEvent(bundle: Any) {
        android.reportTouchEvent(bundle as Bundle)
    }

    actual fun recordImpression(bundle: Any) {
        android.recordImpression(bundle as Bundle)
    }
}
