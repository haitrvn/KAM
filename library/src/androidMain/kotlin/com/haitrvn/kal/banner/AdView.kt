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
import com.applovin.mediation.ads.MaxAdView as AndroidMaxAdView

actual class MaxAdView actual constructor(
    private val adUnitId: String,
    private val adFormat: AdFormat?,
    private val sdk: AppLovinSdk?,
) {
    val android: AndroidMaxAdView by lazy {
        when {
            adFormat != null && sdk != null -> AndroidMaxAdView(
                adUnitId,
                adFormat.android,
                sdk.ios,
                ContextProvider.context
            )

            adFormat != null -> AndroidMaxAdView(
                adUnitId,
                adFormat.android,
                ContextProvider.context
            )

            sdk != null -> AndroidMaxAdView(
                adUnitId,
                sdk.ios,
                ContextProvider.context
            )

            else -> AndroidMaxAdView(
                adUnitId,
                ContextProvider.context
            )
        }
    }

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
            android.setListener(object : MaxAdViewAdListener {
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
            })

            awaitClose {
                android.setListener(null)
            }
        }

    actual fun destroy() {
        android.destroy()
    }

    actual fun getAdFormat(): AdFormat? {
        return adFormat
    }

    actual fun getAdUnitId(): String {
        return adUnitId
    }

    actual fun getPlacement(): String? {
        return android.placement
    }

    actual fun loadAd() {
        android.loadAd()
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

