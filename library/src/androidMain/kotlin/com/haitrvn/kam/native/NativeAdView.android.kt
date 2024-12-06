package com.haitrvn.kam.native

import com.google.android.gms.ads.nativead.NativeAdView

actual class NativeAdView(
    private val android: NativeAdView
) {
    fun setAdChoicesView() {
        android.setAdChoicesView()
    }

    fun setAdvertiserView() {
        android.setAdvertiserView()
    }

    fun setBodyView() {
        android.setBodyView()
    }

    fun setCallToActionView() {
        android.setCallToActionView()
    }

    fun setClickConfirmingView() {
        android.setClickConfirmingView()
    }

    fun setHeadlineView() {
        android.setHeadlineView()
    }

    fun setIconView() {
        android.setIconView()
    }

    fun setImageView() {
        android.setImageView()
    }

    fun setMediaView() {
        android.setMediaView()
    }

    fun setNativeAd() {
        android.setNativeAd()
    }

    fun setPriceView() {
        android.setPriceView()
    }

    fun setStarRatingView() {
        android.setStarRatingView()
    }

    fun setStoreView() {
        android.setStoreView()
    }
}