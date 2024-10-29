package com.haitrvn.kal.initialization

class ALSdkInitializationConfiguration {
    val sdkKey: String? = TODO()
    val mediationProvider: String? = TODO()
    val pluginVersion: String? = TODO()
    val testDevicesAdvertisingIds: List<String> = TODO()
    val adUnitIds: List<String> = TODO()
    val isExceptionHandlerEnabled: Boolean = TODO()
    internal var segmentCollection: MaxSegmentCollection = TODO()
}

actual class AppLovinSdkInitializationConfiguration(
    val configuration: ALSdkInitializationConfiguration
) {
    actual companion object {
        actual fun builder(sdkKey: String): Builder {
            TODO("Not yet implemented")
        }
    }

    actual val sdkKey: String?
        get() = configuration.sdkKey
    actual val mediationProvider: String?
        get() = configuration.mediationProvider
    actual val pluginVersion: String?
        get() = configuration.pluginVersion
    actual val testDevicesAdvertisingIds: List<String>
        get() = configuration.testDevicesAdvertisingIds
    actual val adUnitIds: List<String>
        get() = configuration.adUnitIds
    actual val isExceptionHandlerEnabled: Boolean
        get() = configuration.isExceptionHandlerEnabled
    internal actual var segmentCollection: MaxSegmentCollection
        get() = TODO("Not yet implemented")
        set(value) {}

}