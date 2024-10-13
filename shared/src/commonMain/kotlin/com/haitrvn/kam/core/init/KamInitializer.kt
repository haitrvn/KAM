package com.haitrvn.kam.core.init

expect class KamInitializer {
    fun initialize(onComplete: () -> Unit = {})
    fun disableMediationAdapterInitialization()
    fun setApplicationVolume(volume: Float)
    fun getVersion(): String
    fun setMuted(muted: Boolean)
    internal fun setRequestConfiguration(requestConfiguration: RequestConfiguration)
    internal fun startPreload()
}