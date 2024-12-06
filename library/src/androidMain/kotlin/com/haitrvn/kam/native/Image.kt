package com.haitrvn.kam.native

import com.google.android.gms.ads.nativead.NativeAd.Image as AndroidImage

actual data class Image(
    actual val scale: Double,
    actual val drawable: Drawable?,
    actual val uri: Uri?,
) {
    constructor(android: AndroidImage): this(
        scale = android.scale,
        drawable = android.drawable?.let { Drawable(it) },
        uri = android.uri?.let { Uri(it) }
    )
}

actual class Drawable(
    private val android: android.graphics.drawable.Drawable
)

actual class Uri(
    private val android: android.net.Uri
)