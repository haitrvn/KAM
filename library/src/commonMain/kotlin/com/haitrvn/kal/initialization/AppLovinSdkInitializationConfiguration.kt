package com.haitrvn.kal.initialization

expect class AppLovinSdkInitializationConfiguration {
    companion object {
        fun builder(sdkKey: String): Builder
    }

    val sdkKey: String?
    val mediationProvider: String?
    val pluginVersion: String?
    val testDevicesAdvertisingIds: List<String>
    val adUnitIds: List<String>
    val isExceptionHandlerEnabled: Boolean
    internal val segmentCollection: SegmentCollection
}