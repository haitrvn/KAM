package com.haitrvn.kam.native

expect class Image {
    val scale: Double
    val drawable: Drawable?
    val uri: Uri?
}

expect class Drawable
expect class Uri

expect class MuteThisAdReason {
    val description: String
}

expect class AdChoicesInfo {
    val text: String
    val images: List<Image>
}

expect class MediaContent {
    val aspectRatio: Float
    val currentTime: Float
    val duration: Float
    var mainImage: Drawable?
    val videoController: VideoController
    val isHasVideoContent: Boolean
}

expect class VideoController