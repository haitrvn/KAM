package com.haitrvn.kal.initialization

actual class Builder {
    actual val sdkKey: String
        get() = TODO("Not yet implemented")
    actual var mediationProvider: String?
        get() = TODO("Not yet implemented")
        set(value) {}
    actual var pluginVersion: String?
        get() = TODO("Not yet implemented")
        set(value) {}
    actual var testDevicesAdvertisingIds: List<String>
        get() = TODO("Not yet implemented")
        set(value) {}
    actual var adUnitIds: List<String>
        get() = TODO("Not yet implemented")
        set(value) {}
    actual var isExceptionHandlerEnabled: Boolean
        get() = TODO("Not yet implemented")
        set(value) {}
    internal actual var segmentCollection: MaxSegmentCollection
        get() = TODO("Not yet implemented")
        set(value) {}

    actual fun build(): AppLovinSdkInitializationConfiguration {
        TODO("Not yet implemented")
    }
}