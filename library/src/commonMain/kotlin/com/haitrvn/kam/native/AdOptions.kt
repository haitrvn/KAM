package com.haitrvn.kam.native

expect class AdOptions(
    mediaAspectRatio: NativeMediaAspectRatio?,
    adChoices: AdChoices?,
    isShouldRequestMultipleImages: Boolean?,
    isShouldReturnUrlsForImageAssets: Boolean?,
    videoOptions: VideoOptions?
) {
    val mediaAspectRatio: NativeMediaAspectRatio?
    val adChoices: AdChoices?
    val isShouldRequestMultipleImages: Boolean?
    val isShouldReturnUrlsForImageAssets: Boolean?
    val videoOptions: VideoOptions?
}