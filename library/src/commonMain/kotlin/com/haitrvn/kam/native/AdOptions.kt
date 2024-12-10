package com.haitrvn.kam.native

expect class AdOptions {
    val mediaAspectRatio: NativeMediaAspectRatio?
    val adChoices: AdChoices?
    val isShouldRequestMultipleImages: Boolean?
    val isShouldReturnUrlsForImageAssets: Boolean?
    val videoOptions: VideoOptions?
}