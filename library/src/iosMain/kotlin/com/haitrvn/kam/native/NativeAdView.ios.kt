package com.haitrvn.kam.native

import androidx.compose.runtime.Composable

actual class MediaView
actual class View
actual class AdChoicesView

actual class NativeAdView(
    private val string: String
)

@Composable
actual fun NativeAdView(
    nativeAd: NativeAd,
    binding: NativeAdViewBinding
) {
}