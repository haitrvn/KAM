package com.haitrvn.kam.native

import androidx.compose.runtime.Composable
import androidx.compose.ui.viewinterop.AndroidView
import com.google.android.gms.ads.nativead.NativeAdView

actual typealias AdChoicesView = com.google.android.gms.ads.nativead.AdChoicesView
actual typealias View = android.view.View
actual typealias MediaView = com.google.android.gms.ads.nativead.MediaView

actual class NativeAdView(
    val android: NativeAdView
)

@Composable
actual fun NativeAdView(
    nativeAd: NativeAd,
    binding: NativeAdViewBinding,
) {
    AndroidView(
        factory = {
            binding.rootView.android.apply {
                setNativeAd(nativeAd.android)
                adChoicesView = binding.adChoicesView
                advertiserView = binding.advertiserView
                bodyView = binding.bodyView
                callToActionView = binding.callToActionView
                setClickConfirmingView(binding.clickConfirmingView)
                headlineView = binding.headlineView
                iconView = binding.iconView
                imageView = binding.imageView
                mediaView = binding.mediaView
                priceView = binding.priceView
                starRatingView = binding.starRatingView
                storeView = binding.storeView
                setNativeAd(nativeAd.android)
            }
            binding.rootView.android
        },
        update = {
            binding.rootView.android.requestLayout()
        },
        onRelease = {
            nativeAd.destroy()
        }
    )
}