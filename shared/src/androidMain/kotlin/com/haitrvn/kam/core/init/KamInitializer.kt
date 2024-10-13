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

    actual fun setApplicationVolume(volume: Float) {
        MobileAds.setAppVolume(volume)
    }

    actual fun setMuted(muted: Boolean) {
        MobileAds.setAppMuted(muted)
    }

    actual fun disableMediationAdapterInitialization() {
        MobileAds.disableMediationAdapterInitialization(context)
    }

    actual fun setRequestConfiguration(requestConfiguration: RequestConfiguration) {
        MobileAds.setRequestConfiguration(requestConfiguration.toPlatformConfiguration())
    }

    internal actual fun startPreload() {
    }

    actual fun getVersion(): String {
        return MobileAds.getVersion().toString()
    }
}
