package com.haitrvn.kal.initialization

actual class AppLovinSdk {
    actual companion object {
        actual fun getInstance(): AppLovinSdk {
            TODO("Not yet implemented")
        }
    }

    actual fun initializeSdk(
        configuration: AppLovinSdkInitializationConfiguration,
        listener: SdkInitializationListener
    ) {
    }

}

