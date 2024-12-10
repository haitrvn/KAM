package com.haitrvn.kam.native

expect class MediaContent {
    val aspectRatio: Float
    val currentTime: Float
    val duration: Float
    var mainImage: Drawable?
    val videoController: VideoController
    val isHasVideoContent: Boolean
}