package com.haitrvn.kam

import android.content.Context
import com.google.android.gms.ads.MobileAds

actual class KAdsInitializer(private val context: Context) {
    actual fun initialize(onComplete: () -> Unit) {
        MobileAds.initialize(context) { status ->
            status.adapterStatusMap.forEach {
            }
            onComplete()
        }
    }
}