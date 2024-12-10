package com.haitrvn.kam.native

import com.google.android.gms.ads.nativead.NativeAd.AdChoicesInfo as AndroidAdChoicesInfo

actual data class AdChoicesInfo(
    actual val text: String,
    actual val images: List<Image>,
) {
    constructor(android: AndroidAdChoicesInfo) : this(
        text = android.text.toString(),
        images = android.images.map { Image(it) }
    )
}