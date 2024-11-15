package com.haitrvn.kal.initialization

data class InitConfiguration(
    val sdkKey: String?,
    val mediationProvider: String?,
    val pluginVersion: String?,
    val testDevicesAdvertisingIds: List<String>,
    val adUnitIds: List<String>,
    val isExceptionHandlerEnabled: Boolean,
) {
    internal lateinit var segmentCollection: SegmentCollection
}
