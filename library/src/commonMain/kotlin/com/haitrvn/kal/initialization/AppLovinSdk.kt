package com.haitrvn.kal.initialization

expect class AppLovinSdk {
    companion object {
        fun getInstance(): AppLovinSdk
    }

    fun initializeSdk(
        configuration: AppLovinSdkInitializationConfiguration,
        initializationListener: SdkInitializationListener
    )
}