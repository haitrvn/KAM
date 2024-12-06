package com.haitrvn.kam.native

import com.google.android.gms.ads.MediaContent as AndroidMediaContent
import com.google.android.gms.ads.MuteThisAdReason as AndroidMuteThisAdReason
import com.google.android.gms.ads.nativead.NativeAd.AdChoicesInfo as AndroidAdChoicesInfo

actual class MuteThisAdReason(
    val android: AndroidMuteThisAdReason
) {
    actual val description: String
        get() = android.description
}

actual data class AdChoicesInfo(
    actual val text: String,
    actual val images: List<Image>,
) {
    constructor(android: AndroidAdChoicesInfo) : this(
        text = android.text.toString(),
        images = android.images.map { Image(it) }
    )
}

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

actual class VideoController(
    private val android: com.google.android.gms.ads.VideoController
)