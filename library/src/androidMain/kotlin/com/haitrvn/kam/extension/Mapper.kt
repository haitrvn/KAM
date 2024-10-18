package com.haitrvn.kam.extension

import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.RequestConfiguration.PublisherPrivacyPersonalizationState.DEFAULT
import com.google.android.gms.ads.RequestConfiguration.PublisherPrivacyPersonalizationState.DISABLED
import com.google.android.gms.ads.RequestConfiguration.PublisherPrivacyPersonalizationState.ENABLED
import com.haitrvn.kam.core.model.PublisherPrivacyPersonalizationState
import com.haitrvn.kam.core.model.RequestConfiguration
import com.haitrvn.kam.core.model.KamAdError
import com.google.android.gms.ads.RequestConfiguration as AndroidRequestConfiguration
import com.google.android.gms.ads.RequestConfiguration.PublisherPrivacyPersonalizationState as AndroidState


fun AdError.toKamError(): KamAdError {
    return KamAdError(
        code = code,
        cause = cause.toString(),
        domain = domain,
        message = message
    )
}

fun RequestConfiguration.toPlatformConfiguration(): AndroidRequestConfiguration {
    return AndroidRequestConfiguration.Builder()
        .setMaxAdContentRating(maxAdContentRating)
        .setTagForUnderAgeOfConsent(tagForUnderAgeOfConsent)
        .setTagForChildDirectedTreatment(tagForChildDirectedTreatment)
        .setTestDeviceIds(testDeviceIds)
        .setPublisherPrivacyPersonalizationState(publisherPrivacyPersonalizationState.toPlatformState())
        .build()
}


fun AndroidRequestConfiguration.toCommonConfiguration(): RequestConfiguration {
    return RequestConfiguration(
        maxAdContentRating = maxAdContentRating,
        tagForUnderAgeOfConsent = tagForUnderAgeOfConsent,
        tagForChildDirectedTreatment = tagForChildDirectedTreatment,
        testDeviceIds = testDeviceIds,
        publisherPrivacyPersonalizationState = publisherPrivacyPersonalizationState.toCommonState()
    )

}

private fun PublisherPrivacyPersonalizationState.toPlatformState(): AndroidState {
    return when (this) {
        PublisherPrivacyPersonalizationState.DEFAULT -> DEFAULT
        PublisherPrivacyPersonalizationState.ENABLED -> ENABLED
        PublisherPrivacyPersonalizationState.DISABLED -> DISABLED
    }
}

private fun AndroidState.toCommonState(): PublisherPrivacyPersonalizationState {
    return when (this) {
        DEFAULT -> PublisherPrivacyPersonalizationState.DEFAULT
        ENABLED -> PublisherPrivacyPersonalizationState.ENABLED
        DISABLED -> PublisherPrivacyPersonalizationState.DISABLED
    }
}