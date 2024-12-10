package com.haitrvn.kam.native

import com.google.android.gms.ads.MediaContent as AndroidMediaContent

actual data class MediaContent(
    actual val aspectRatio: Float,
    actual val currentTime: Float,
    actual val duration: Float,
    actual var mainImage: Drawable?,
    actual val videoController: VideoController,
    actual val isHasVideoContent: Boolean,
) {
    constructor(android: AndroidMediaContent) : this(
        aspectRatio = android.aspectRatio,
        currentTime = android.currentTime,
        duration = android.duration,
        mainImage = android.mainImage?.let { Drawable(it) },
        videoController = VideoController(android.videoController),
        isHasVideoContent = android.hasVideoContent(),
    )
}