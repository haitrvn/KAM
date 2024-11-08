package com.haitrvn.kal.initialization

actual class Builder(
    private val build: com.applovin.sdk.AppLovinSdkInitializationConfiguration.Builder
) {
    actual val sdkKey: String
        get() = build.sdkKey

    actual var mediationProvider: String?
        get() = build.mediationProvider
        set(value) {
            build.mediationProvider = value
        }
    actual var pluginVersion: String?
        get() = build.pluginVersion
        set(value) {
            build.pluginVersion = value
        }
    actual var testDevicesAdvertisingIds: List<String>
        get() = build.testDeviceAdvertisingIds
        set(value) {
            build.testDeviceAdvertisingIds = value
        }
    actual var adUnitIds: List<String>
        get() = build.adUnitIds
        set(value) {
            build.adUnitIds = value
        }
    actual var isExceptionHandlerEnabled: Boolean
        get() = build.isExceptionHandlerEnabled
        set(value) {
            build.isExceptionHandlerEnabled = value
        }
    internal actual var segmentCollection: SegmentCollection
        get() = build.segmentCollection as SegmentCollection
        set(value) {
            build.segmentCollection = value
        }

    actual fun build(): AppLovinSdkInitializationConfiguration {
        return AppLovinSdkInitializationConfiguration(build.build())
    }
}