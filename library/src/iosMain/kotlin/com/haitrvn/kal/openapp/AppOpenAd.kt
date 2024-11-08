package com.haitrvn.kal.openapp

import com.haitrvn.kal.initialization.AppLovinSdk
import com.haitrvn.kal.listener.AdListener
import com.haitrvn.kal.listener.ExpirationListener
import com.haitrvn.kal.listener.RequestListener
import com.haitrvn.kal.listener.RevenueListener
import com.haitrvn.kal.listener.ReviewListener

actual class AppOpenAd actual constructor(
    private val adUnitId: String,
    private val appLovinSdk: AppLovinSdk?
) {
    actual val isReady: Boolean
        get() = TODO("Not yet implemented")
    actual val unitId: String
        get() = TODO("Not yet implemented")

    actual fun loadAd() {
    }

    actual fun setAdReviewListener(reviewListener: ReviewListener) {
    }

    actual fun setExpirationListener(expirationListener: ExpirationListener) {
    }

    actual fun setExtraParameter(key: String, value: String) {
    }

    actual fun setListener(viewAdListener: AdListener) {
    }

    internal actual fun setLocalExtraParameter(key: String, param: Any) {
    }

    actual fun setRequestListener(requestListener: RequestListener) {
    }

    actual fun setRevenueListener(revenueListener: RevenueListener) {
    }

    actual fun showAd() {
    }

    actual fun showAd(placement: String) {
    }

    actual fun showAd(placement: String, customData: String) {
    }

    actual fun destroy() {
    }
}