package com.haitrvn.kam.core.init

data class RequestConfiguration(
    val maxAdContentRating: String,
    val tagForChildDirectedTreatment: Int,
    val tagForUnderAgeOfConsent: Int,
    val testDeviceIds: List<String>,
    val publisherPrivacyPersonalizationState: PublisherPrivacyPersonalizationState,
)

enum class PublisherPrivacyPersonalizationState(val value: Int) {
    DEFAULT(0),
    ENABLED(1),
    DISABLED(2);
}