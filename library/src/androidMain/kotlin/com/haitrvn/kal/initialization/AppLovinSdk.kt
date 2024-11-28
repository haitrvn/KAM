package com.haitrvn.kal.initialization

import com.applovin.sdk.AppLovinSdkConfiguration
import com.applovin.sdk.AppLovinSdkInitializationConfiguration
import com.haitrvn.kal.util.ContextProvider
import kotlin.concurrent.Volatile

actual class AppLovinSdk(
    var android: com.applovin.sdk.AppLovinSdk
) {
    actual companion object {
        @Volatile
        private var instance: AppLovinSdk? = null

        actual fun getInstance(): AppLovinSdk {
            return instance ?: synchronized(this) {
                instance ?: AppLovinSdk(
                    com.applovin.sdk.AppLovinSdk.getInstance(ContextProvider.context)
                ).also { instance = it }
            }
        }
    }

    actual fun initializeSdk(
        configuration: InitConfiguration,
        completedInformation: (SdkInformation) -> Unit
    ) {
        android.initialize(configuration.toAndroidConfiguration()) {
            completedInformation(it.toCommon())
        }
    }
}

private fun InitConfiguration.toAndroidConfiguration(): AppLovinSdkInitializationConfiguration {
    return AppLovinSdkInitializationConfiguration.builder(sdkKey, ContextProvider.context).apply {
        mediationProvider = this@toAndroidConfiguration.mediationProvider
        pluginVersion = this@toAndroidConfiguration.pluginVersion
        testDeviceAdvertisingIds = this@toAndroidConfiguration.testDevicesAdvertisingIds
        adUnitIds = this@toAndroidConfiguration.adUnitIds
        isExceptionHandlerEnabled = this@toAndroidConfiguration.isExceptionHandlerEnabled
//        segmentCollection = this@toAndroidConfiguration.segmentCollection
    }.build()
}

private fun AppLovinSdkConfiguration.toCommon() = SdkInformation(
    consentFlowUserGeography = this.consentFlowUserGeography.toCommon(),
    countryCode = countryCode,
    enabledAmazonAdUnitIds = enabledAmazonAdUnitIds.orEmpty(),
    isTestModeEnabled = isTestModeEnabled
)

private fun AppLovinSdkConfiguration.ConsentFlowUserGeography.toCommon(): ConsentFlowUserGeography =
    when (this) {
        AppLovinSdkConfiguration.ConsentFlowUserGeography.UNKNOWN -> ConsentFlowUserGeography.UNKNOWN
        AppLovinSdkConfiguration.ConsentFlowUserGeography.GDPR -> ConsentFlowUserGeography.GDPR
        AppLovinSdkConfiguration.ConsentFlowUserGeography.OTHER -> ConsentFlowUserGeography.OTHER
    }
