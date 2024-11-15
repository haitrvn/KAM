package com.haitrvn.kal.initialization

expect class AppLovinSdk {
    companion object {
        fun getInstance(): AppLovinSdk
    }

    fun initializeSdk(
        configuration: InitConfiguration,
        completedInformation: (SdkInformation) -> Unit = {}
    )
}

data class SdkInformation(
    val consentFlowUserGeography: ConsentFlowUserGeography,
    val countryCode: String,
    val enabledAmazonAdUnitIds: List<String>,
    val isTestModeEnabled: Boolean
)

enum class ConsentFlowUserGeography {
    UNKNOWN,
    GDPR,
    OTHER;
}