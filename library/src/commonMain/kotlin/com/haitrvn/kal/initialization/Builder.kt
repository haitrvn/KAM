package com.haitrvn.kal.initialization

expect class Builder {
    val sdkKey: String
    var mediationProvider: String?
    var pluginVersion: String?
    var testDevicesAdvertisingIds: List<String>
    var adUnitIds: List<String>
    var isExceptionHandlerEnabled: Boolean
    internal var segmentCollection: MaxSegmentCollection

    fun build(): AppLovinSdkInitializationConfiguration
}