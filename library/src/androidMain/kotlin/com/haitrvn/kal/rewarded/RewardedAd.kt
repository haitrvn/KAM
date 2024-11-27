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

actual class RewardedAd actual constructor(
    private val adUnitId: String,
    private val appLovinSdk: AppLovinSdk?
) {
    private val adReward: MaxRewardedAd by lazy {
        if (appLovinSdk != null) {
            MaxRewardedAd.getInstance(
                adUnitId,
                appLovinSdk.ios,
                ContextProvider.context
            )
        } else {
            MaxRewardedAd.getInstance(adUnitId, ContextProvider.context)
        }
    }

    actual val reviewFlow: Flow<Ad>
        get() = callbackFlow {
            adReward.setAdReviewListener { id, maxAd ->
                ReviewAd(id, Ad(maxAd))
            }
            awaitClose { adReward.setAdReviewListener(null) }
        }

    actual val expirationFlow: Flow<Pair<Ad, Ad>>
        get() = callbackFlow {
            adReward.setExpirationListener { old, new ->
                trySend(Ad(old) to Ad(new))
            }
            awaitClose { adReward.setExpirationListener(null) }
        }

    actual val revenueFlow: Flow<Ad>
        get() = callbackFlow {
            adReward.setRevenueListener {
                trySend(Ad(it))
            }
            awaitClose { adReward.setRevenueListener(null) }
        }

    actual val requestFlow: Flow<String>
        get() = callbackFlow {
            adReward.setRequestListener {
                trySend(it)
            }
            awaitClose { adReward.setRequestListener(null) }
        }

    actual val rewardedAdFlow: Flow<AdEvent>
        get() = callbackFlow {
            adReward.setListener(object : MaxRewardedAdListener {
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
            })

            awaitClose {
                adReward.setListener(null)
            }
        }

    actual val isReady: Boolean
        get() = this.adReward.isReady
    actual val unitId: String
        get() = this.adReward.adUnitId

    actual fun loadAd() {
        this.adReward.loadAd()
    }

    actual fun setExtraParameter(key: String, value: String) {
        this.adReward.setExtraParameter(key, value)
    }

    internal actual fun setLocalExtraParameter(key: String, param: Any) {
        this.adReward.setLocalExtraParameter(key, param)
    }

    actual fun showAd(rootView: RootView) {
        this.adReward.showAd(rootView)
    }

    actual fun showAd(placement: String, rootView: RootView) {
        this.adReward.showAd(placement, rootView)
    }

    actual fun showAd(placement: String, customData: String, rootView: RootView) {
        this.adReward.showAd(placement, customData, rootView)
    }

    actual fun showAd(viewGroup: ViewGroup, lifecycle: Lifecycle, rootView: RootView) {
        this.adReward.showAd(viewGroup.android, lifecycle, rootView)
    }

    actual fun showAd(
        placement: String,
        viewGroup: ViewGroup,
        lifecycle: Lifecycle,
        rootView: RootView
    ) {
        this.adReward.showAd(placement, viewGroup.android, lifecycle, rootView)
    }

    actual fun showAd(
        placement: String,
        customData: String,
        viewGroup: ViewGroup,
        lifecycle: Lifecycle,
        rootView: RootView
    ) {
        this.adReward.showAd(placement, customData, viewGroup.android, lifecycle, rootView)
    }

    actual fun destroy() {
        this.adReward.destroy()
    }
}

actual class ViewGroup(
    val android: android.view.ViewGroup
)