package com.haitrvn.kal.banner

import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxAdViewAdListener
import com.applovin.mediation.MaxError
import com.haitrvn.kal.core.Ad
import com.haitrvn.kal.initialization.AppLovinSdk
import com.haitrvn.kal.util.toCommonError
import com.haitrvn.kal.model.AdEvent
import com.haitrvn.kal.model.ReviewAd
import com.haitrvn.kal.util.ContextProvider
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import com.applovin.mediation.ads.MaxAdView as AndroidMaxAdView

actual class MaxAdView actual constructor(
    private val adUnitId: String,
    private val adFormat: AdFormat?,
    private val sdk: AppLovinSdk?,
) {
    val maxAdView: AndroidMaxAdView by lazy {
         when {
            adFormat != null && sdk != null -> AndroidMaxAdView(
                adUnitId,
                adFormat.adFormat,
                sdk.androidAppLovinSdk,
                ContextProvider.applicationContext
            )
            adFormat != null -> AndroidMaxAdView(
                adUnitId,
                adFormat.adFormat,
                ContextProvider.applicationContext
            )
            sdk != null -> AndroidMaxAdView(
                adUnitId,
                sdk.androidAppLovinSdk,
                ContextProvider.applicationContext
            )
            else -> AndroidMaxAdView(
                adUnitId,
                ContextProvider.applicationContext
            )
        }
    }

    actual val reviewFlow: Flow<Ad>
        get() = callbackFlow {
            maxAdView.setAdReviewListener { id, maxAd ->
                ReviewAd(id, maxAd)
            }
            awaitClose { maxAdView.setAdReviewListener(null) }
        }

    actual val revenueFlow: Flow<Ad>
        get() = callbackFlow {
            maxAdView.setRevenueListener {
                trySend(it)
            }
            awaitClose { maxAdView.setRevenueListener(null) }
        }

    actual val requestFlow: Flow<String>
        get() = callbackFlow {
            maxAdView.setRequestListener {
                trySend(it)
            }
            awaitClose { maxAdView.setRequestListener(null) }
        }

    actual val adEventFlow: Flow<AdEvent>
        get() = callbackFlow {
            maxAdView.setListener(object: MaxAdViewAdListener {
                override fun onAdLoaded(ad: MaxAd) {
                    trySend(AdEvent.Loaded(ad))
                }

                override fun onAdDisplayed(ad: MaxAd) {
                    trySend(AdEvent.Displayed(ad))
                }

                override fun onAdHidden(ad: MaxAd) {
                    trySend(AdEvent.Hidden(ad))
                }

                override fun onAdClicked(ad: MaxAd) {
                    trySend(AdEvent.Clicked(ad))
                }

                override fun onAdLoadFailed(message: String, error: MaxError) {
                    trySend(AdEvent.LoadFailed(message, error.toCommonError()))
                }

                override fun onAdDisplayFailed(ad: MaxAd, error: MaxError) {
                    trySend(AdEvent.DisplayFailed(ad, error.toCommonError()))
                }

                override fun onAdExpanded(ad: MaxAd) {
                    trySend(AdEvent.Expanded(ad))
                }

                override fun onAdCollapsed(ad: MaxAd) {
                    trySend(AdEvent.Collapsed(ad))
                }
            })

            awaitClose {
                maxAdView.setListener(null)
            }
        }

    actual fun destroy() {
        maxAdView.destroy()
    }

    actual fun getAdFormat(): AdFormat? {
        return adFormat
    }

    actual fun getAdUnitId(): String {
        return adUnitId
    }

    actual fun getPlacement(): String {
        return maxAdView.placement
    }

    actual fun loadAd() {
        maxAdView.loadAd()
    }

    actual fun setAlpha(alpha: Float) {
        maxAdView.alpha = alpha
    }

    actual fun setBackgroundColor() {
    }

    actual fun setCustomData(data: String) {
        maxAdView.setCustomData(data)
    }

    actual fun setExtraParameter(param: String, data: String) {
        maxAdView.setExtraParameter(param, data)
    }

    actual fun setLocalExtraParameter(param: String, data: Any) {
        maxAdView.setLocalExtraParameter(param, data)
    }

    actual fun setPlacement(placement: String) {
        maxAdView.placement = placement
    }

    actual fun startAutoRefresh() {
        maxAdView.startAutoRefresh()
    }

    actual fun stopAutoRefresh() {
        maxAdView.stopAutoRefresh()
    }
}

