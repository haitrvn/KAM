package com.haitrvn.kam.native

actual data class VideoOptions actual constructor(
    actual val clickToExpandRequested: Boolean?,
    actual val customControlsRequested: Boolean?,
    actual val startMuted: Boolean?,
) {
    constructor(android: com.google.android.gms.ads.VideoOptions) : this(
        clickToExpandRequested = android.clickToExpandRequested,
        customControlsRequested = android.customControlsRequested,
        startMuted = android.startMuted
    )

    val android: com.google.android.gms.ads.VideoOptions by lazy {
        com.google.android.gms.ads.VideoOptions.Builder().apply {
            clickToExpandRequested?.let { setClickToExpandRequested(it) }
            customControlsRequested?.let { setCustomControlsRequested(it) }
            startMuted?.let { setStartMuted(it) }
        }.build()
    }
}