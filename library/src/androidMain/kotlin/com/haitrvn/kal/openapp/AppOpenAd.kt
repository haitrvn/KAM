package com.haitrvn.kal.openapp

import com.applovin.mediation.ads.MaxAppOpenAd
import com.haitrvn.kal.core.Ad
import com.haitrvn.kal.initialization.AppLovinSdk
import com.haitrvn.kal.model.ReviewAd
import com.haitrvn.kal.util.ContextProvider
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

actual class AppOpenAd actual constructor(
    private val adUnitId: String,
    private val appLovinSdk: AppLovinSdk?
) {
    private val appOpenAd: MaxAppOpenAd by lazy {
        if (appLovinSdk != null) {
            MaxAppOpenAd(adUnitId, appLovinSdk.androidAppLovinSdk)
        } else {
            MaxAppOpenAd(adUnitId, ContextProvider.applicationContext)
        }
    }

    init {
        appOpenAd.setListener(null)
        appOpenAd.setRequestListener(null)
        appOpenAd.setRevenueListener(null)
        appOpenAd.setExpirationListener(null)
        appOpenAd.setAdReviewListener(null)
    }

    actual val reviewFlow: Flow<ReviewAd>
        get() = callbackFlow {
            appOpenAd.setAdReviewListener { id, maxAd ->
                trySend(ReviewAd(id, maxAd))
            }
            awaitClose {
                appOpenAd.setAdReviewListener(null)
            }
        }

    actual val expirationFlow: Flow<Pair<Ad, Ad>>
        get() = callbackFlow {
            appOpenAd.setExpirationListener { oldAd, newAd ->
                trySend(oldAd to newAd)
            }
            awaitClose {
                appOpenAd.setExpirationListener(null)
            }
        }

    actual val revenueFlow: Flow<Ad>
        get() = callbackFlow {
            appOpenAd.setRevenueListener { ad ->
                trySend(ad)
            }
            awaitClose {
                appOpenAd.setRevenueListener(null)
            }
        }

    actual val requestFlow: Flow<String>
        get() = callbackFlow {
            appOpenAd.setRequestListener {
                trySend(it)
            }
            awaitClose {
                appOpenAd.setRequestListener(null)
            }
        }

    actual val isReady: Boolean
        get() = appOpenAd.isReady

    actual val unitId: String
        get() = appOpenAd.adUnitId

    actual fun loadAd() {
        appOpenAd.loadAd()
    }

    actual fun setExtraParameter(key: String, value: String) {
        appOpenAd.setExtraParameter(key, value)
    }

    internal actual fun setLocalExtraParameter(key: String, param: Any) {
        appOpenAd.setLocalExtraParameter(key, param)
    }

    actual fun showAd() {
        appOpenAd.showAd()
    }

    actual fun showAd(placement: String) {
        appOpenAd.showAd(placement)
    }

    actual fun showAd(placement: String, customData: String) {
        appOpenAd.showAd(placement, customData)
    }

    actual fun destroy() {
        appOpenAd.destroy()
    }
}