package com.haitrvn.kal.initialization

actual class AppLovinSdkInitializationConfiguration {
    actual companion object {
        actual fun builder(sdkKey: String): Builder {
            TODO("Not yet implemented")
        }
    }

    actual val sdkKey: String?
        get() = TODO("Not yet implemented")
    actual val mediationProvider: String?
        get() = TODO("Not yet implemented")
    actual val pluginVersion: String?
        get() = TODO("Not yet implemented")
    actual val testDevicesAdvertisingIds: List<String>
        get() = TODO("Not yet implemented")
    actual val adUnitIds: List<String>
        get() = TODO("Not yet implemented")
    actual val isExceptionHandlerEnabled: Boolean
        get() = TODO("Not yet implemented")
    internal actual var segmentCollection: MaxSegmentCollection
        get() = TODO("Not yet implemented")
        set(value) {}

}