package com.haitrvn.kal.initialization

import com.haitrvn.kal.util.ContextProvider
import kotlin.concurrent.Volatile

actual class AppLovinSdk(
    var androidAppLovinSdk: com.applovin.sdk.AppLovinSdk
) {
    actual companion object {
        @Volatile
        private var instance: AppLovinSdk? = null

        actual fun getInstance(): AppLovinSdk {
            return instance ?: synchronized(this) {
                instance ?: AppLovinSdk(
                    com.applovin.sdk.AppLovinSdk.getInstance(ContextProvider.applicationContext)
                ).also { instance = it }
            }
        }
    }

    actual fun initializeSdk(
        configuration: AppLovinSdkInitializationConfiguration,
        listener: SdkInitializationListener
    ) {
        androidAppLovinSdk.initialize(configuration.configuration) {
            listener.onSdkInitialized()
        }
    }
}