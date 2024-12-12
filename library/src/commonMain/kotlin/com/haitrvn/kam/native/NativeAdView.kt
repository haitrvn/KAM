package com.haitrvn.kam.native

import androidx.compose.runtime.Composable

expect class AdChoicesView
expect class View
expect class MediaView

expect class NativeAdView

@Composable
expect fun NativeAdView(
    nativeAd: NativeAd,
    binding: NativeAdViewBinding,
)

interface NativeAdViewBinding {
    val rootView: NativeAdView
    val adChoicesView: AdChoicesView?
    val advertiserView: View?
    val bodyView: View
    val callToActionView: View?
    val clickConfirmingView: View
    val headlineView: View
    val iconView: View
    val imageView: View?
    val mediaView: MediaView
    val priceView: View?
    val starRatingView: View
    val storeView: View?
}