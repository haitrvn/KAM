package com.haitrvn.kam.core.request

import com.google.android.gms.ads.AdRequest

actual typealias KamRequest = AdRequest

actual fun KamRequest(): KamRequest {
    return AdRequest.Builder().build()
}