package com.haitrvn.kal.rewarded

import androidx.lifecycle.Lifecycle
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxError
import com.applovin.mediation.MaxReward
import com.applovin.mediation.MaxRewardedAdListener
import com.applovin.mediation.ads.MaxRewardedAd
import com.haitrvn.kal.core.Ad
import com.haitrvn.kal.core.Reward
import com.haitrvn.kal.core.RootView
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

actual class RewardedAd actual constructor(
    adUnitId: String,
    appLovinSdk: AppLovinSdk?
) {
    private val android by lazy {
        appLovinSdk?.let {
            MaxRewardedAd.getInstance(adUnitId, it.android, ContextProvider.context)
        } ?: MaxRewardedAd.getInstance(adUnitId, ContextProvider.context)
    }

    private val listenerGroup: RewardAdListenerGroup by lazy {
        RewardAdListenerGroup(android, mutableListOf())
    }

    actual val reviewFlow: Flow<ReviewAd>
        get() = callbackFlow {
            android.setAdReviewListener { id, maxAd ->
                trySend(ReviewAd(id, Ad(maxAd)))
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

    actual val rewardedAdFlow: Flow<AdEvent>
        get() = callbackFlow {
            val listener = object : MaxRewardedAdListener {
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

                override fun onUserRewarded(ad: MaxAd, reward: MaxReward) {
                    trySend(AdEvent.UserRewarded(Ad(ad), Reward(reward)))
                }
            }
            listenerGroup.listenerList.add(listener)
            awaitClose {
                android.setListener(null)
                listenerGroup.listenerList.remove(listener)
            }
        }

    actual val isReady: Boolean
        get() = this.android.isReady
    actual val unitId: String
        get() = this.android.adUnitId

    actual suspend fun loadAd(): RewardedAd? {
        return suspendCancellableCoroutine { cancellableContinuation ->
            val listener = object : MaxRewardedAdListener {
                override fun onAdLoaded(ad: MaxAd) {
                     cancellableContinuation.resume(this@RewardedAd)
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

                override fun onUserRewarded(ad: MaxAd, reward: MaxReward) {
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
        this.android.setExtraParameter(key, value)
    }

    internal actual fun setLocalExtraParameter(key: String, param: Any) {
        this.android.setLocalExtraParameter(key, param)
    }

    actual fun showAd(rootView: RootView) {
        this.android.showAd(rootView)
    }

    actual fun showAd(placement: String, rootView: RootView) {
        this.android.showAd(placement, rootView)
    }

    actual fun showAd(placement: String, customData: String, rootView: RootView) {
        this.android.showAd(placement, customData, rootView)
    }

    actual fun showAd(viewGroup: ViewGroup, lifecycle: Lifecycle, rootView: RootView) {
        this.android.showAd(viewGroup.android, lifecycle, rootView)
    }

    actual fun showAd(
        placement: String,
        viewGroup: ViewGroup,
        lifecycle: Lifecycle,
        rootView: RootView
    ) {
        this.android.showAd(placement, viewGroup.android, lifecycle, rootView)
    }

    actual fun showAd(
        placement: String,
        customData: String,
        viewGroup: ViewGroup,
        lifecycle: Lifecycle,
        rootView: RootView
    ) {
        this.android.showAd(placement, customData, viewGroup.android, lifecycle, rootView)
    }

    actual fun destroy() {
        this.android.destroy()
    }
}

actual class ViewGroup(
    val android: android.view.ViewGroup
)