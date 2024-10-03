package com.haitrvn.kam.core

import com.google.android.gms.ads.AdRequest.Builder

actual object KadRequestBuilder {
    actual fun build(): KAdRequest {
        return Builder().build()
    }
}