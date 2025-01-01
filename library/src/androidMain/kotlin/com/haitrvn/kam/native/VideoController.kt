package com.haitrvn.kam.native

import com.google.android.gms.ads.VideoController as AndroidVideoController

actual class VideoController(
    private val android: AndroidVideoController
)