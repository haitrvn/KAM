package com.haitrvn.kam.native

import com.google.android.gms.ads.nativead.NativeAdOptions

actual data class AdOptions actual constructor(
    actual val mediaAspectRatio: NativeMediaAspectRatio?,
    actual val adChoices: AdChoices?,
    actual val isShouldRequestMultipleImages: Boolean?,
    actual val isShouldReturnUrlsForImageAssets: Boolean?,
    actual val videoOptions: VideoOptions?,
) {
    constructor(android: NativeAdOptions) : this(
        mediaAspectRatio = NativeMediaAspectRatio.entries.firstOrNull { it.value == android.mediaAspectRatio },
        adChoices = AdChoices.entries.firstOrNull { it.value == android.adChoicesPlacement },
        isShouldRequestMultipleImages = android.shouldRequestMultipleImages(),
        isShouldReturnUrlsForImageAssets = android.shouldReturnUrlsForImageAssets(),
        videoOptions = android.videoOptions?.let { VideoOptions(it) }
    )

    val android: NativeAdOptions by lazy {
        NativeAdOptions.Builder().apply {
            mediaAspectRatio?.value?.let { setMediaAspectRatio(it) }
            adChoices?.value?.let { setAdChoicesPlacement(it) }
            isShouldRequestMultipleImages?.let { setRequestMultipleImages(it) }
            isShouldReturnUrlsForImageAssets?.let { setReturnUrlsForImageAssets(it) }
            videoOptions?.android?.let { setVideoOptions(it) }
        }.build()
    }
}