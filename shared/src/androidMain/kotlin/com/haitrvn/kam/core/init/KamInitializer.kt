package com.haitrvn.kam.core.init

import android.content.Context
import com.google.android.gms.ads.MobileAds
import com.haitrvn.kam.extension.toPlatformConfiguration

actual class KamInitializer(private val context: Context) {
    actual fun initialize(onComplete: () -> Unit) {
        MobileAds.initialize(context) {
            onComplete()
        }
    }

    actual fun disableMediationAdapterInitialization() {
        MobileAds.disableMediationAdapterInitialization(context)
    }

    actual fun setRequestConfiguration(requestConfiguration: RequestConfiguration) {
        MobileAds.setRequestConfiguration(requestConfiguration.toPlatformConfiguration())
    }

    internal actual fun startPreload() {
        TODO("Support android only")
    }
}