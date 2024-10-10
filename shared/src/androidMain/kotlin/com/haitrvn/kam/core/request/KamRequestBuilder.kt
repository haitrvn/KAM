package com.haitrvn.kam.core.request

import com.google.android.gms.ads.AdRequest

actual object KamRequestBuilder {
    actual fun build(): KamRequest {
        return AdRequest.Builder().build()
    }
}