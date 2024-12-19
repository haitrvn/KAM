package com.haitrvn.kam.native

import com.google.android.gms.ads.VideoOptions as AndroidVideoOptions

actual data class VideoOptions (
    actual val clickToExpandRequested: Boolean?,
    actual val customControlsRequested: Boolean?,
    actual val startMuted: Boolean?,
) {
    constructor(android: AndroidVideoOptions) : this(
        clickToExpandRequested = android.clickToExpandRequested,
        customControlsRequested = android.customControlsRequested,
        startMuted = android.startMuted
    )

    val android: AndroidVideoOptions by lazy {
        AndroidVideoOptions.Builder().apply {
            clickToExpandRequested?.let { setClickToExpandRequested(it) }
            customControlsRequested?.let { setCustomControlsRequested(it) }
            startMuted?.let { setStartMuted(it) }
        }.build()
    }
}