package com.haitrvn.kam.native

import cocoapods.Google_Mobile_Ads_SDK.GADNativeAdMediaAdLoaderOptions

actual class AdOptions(
    actual val mediaAspectRatio: NativeMediaAspectRatio?,
    actual val adChoices: AdChoices?,
    actual val isShouldRequestMultipleImages: Boolean?,
    actual val isShouldReturnUrlsForImageAssets: Boolean?,
    actual val videoOptions: VideoOptions?
) {
//    constructor(ios: GADNativeAdMediaAdLoaderOptions) : this(
//        mediaAspectRatio = ios.mediaAspectRatio,
//        adChoices = ios.
//        )
}