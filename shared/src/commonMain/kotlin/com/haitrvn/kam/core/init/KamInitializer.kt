package com.haitrvn.kam.core.init

expect class KamInitializer {
    fun initialize(onComplete: () -> Unit = {})
    fun disableMediationAdapterInitialization()
    fun setRequestConfiguration(requestConfiguration: RequestConfiguration)
    internal fun startPreload()
}