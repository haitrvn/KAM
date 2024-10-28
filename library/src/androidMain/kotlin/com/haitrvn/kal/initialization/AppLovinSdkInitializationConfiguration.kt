package com.haitrvn.kal.initialization

import com.applovin.sdk.AppLovinSdkInitializationConfiguration
import com.haitrvn.kal.util.ContextProvider

actual class AppLovinSdkInitializationConfiguration(
    val configuration: AppLovinSdkInitializationConfiguration
) {
    actual companion object {
        actual fun builder(sdkKey: String): Builder {
            return Builder(
                AppLovinSdkInitializationConfiguration.builder(
                    sdkKey, ContextProvider.applicationContext
                )
            )
        }
    }

    actual val sdkKey: String?
        get() = configuration.sdkKey

    actual val mediationProvider: String?
        get() = configuration.mediationProvider

    actual val pluginVersion: String?
        get() = configuration.pluginVersion

    actual val testDevicesAdvertisingIds: List<String>
        get() = configuration.testDeviceAdvertisingIds

    actual val adUnitIds: List<String>
        get() = configuration.adUnitIds

    actual val isExceptionHandlerEnabled: Boolean
        get() = configuration.isExceptionHandlerEnabled

    internal actual var segmentCollection: MaxSegmentCollection
        get() = TODO("Not yet implemented")
        set(value) {}
}