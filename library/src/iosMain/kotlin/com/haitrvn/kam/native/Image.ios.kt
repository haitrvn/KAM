package com.haitrvn.kam.native

actual class Image {
    actual val scale: Double
        get() = TODO("Not yet implemented")
    actual val drawable: Drawable?
        get() = TODO("Not yet implemented")
    actual val uri: Uri?
        get() = TODO("Not yet implemented")
}

actual class Drawable

actual class Uri
actual data class MuteThisAdReason(
    actual val description: String
) {
}

actual class AdChoicesInfo {
    actual val text: String
        get() = TODO("Not yet implemented")
    actual val images: List<Image>
        get() = TODO("Not yet implemented")
}

actual class MediaContent {
    actual val aspectRatio: Float
        get() = TODO("Not yet implemented")
    actual val currentTime: Float
        get() = TODO("Not yet implemented")
    actual val duration: Float
        get() = TODO("Not yet implemented")
    actual var mainImage: Drawable?
        get() = TODO("Not yet implemented")
        set(value) {}
    actual val videoController: VideoController
        get() = TODO("Not yet implemented")
    actual val isHasVideoContent: Boolean
        get() = TODO("Not yet implemented")
}

actual class VideoController