package com.haitrvn.kam.extension

import com.google.android.gms.ads.AdError
import com.haitrvn.kam.core.model.KamAdError
import com.haitrvn.kam.core.init.PublisherPrivacyPersonalizationState
import com.haitrvn.kam.core.init.RequestConfiguration
import com.google.android.gms.ads.RequestConfiguration.PublisherPrivacyPersonalizationState.DEFAULT
import com.google.android.gms.ads.RequestConfiguration.PublisherPrivacyPersonalizationState.ENABLED
import com.google.android.gms.ads.RequestConfiguration.PublisherPrivacyPersonalizationState.DISABLED
fun AdError.toKamError(): KamAdError {
    return KamAdError(
        code = code,
        cause = cause.toString(),
        domain = domain,
        message = message
    )
}

fun RequestConfiguration.toPlatformConfiguration(): com.google.android.gms.ads.RequestConfiguration {
    return com.google.android.gms.ads.RequestConfiguration.Builder()
        .setMaxAdContentRating(maxAdContentRating)
        .setTagForUnderAgeOfConsent(tagForUnderAgeOfConsent)
        .setTagForChildDirectedTreatment(tagForChildDirectedTreatment)
        .setTestDeviceIds(testDeviceIds)
        .setPublisherPrivacyPersonalizationState(publisherPrivacyPersonalizationState.toPlatformState())
        .build()
}

private fun PublisherPrivacyPersonalizationState.toPlatformState(): com.google.android.gms.ads.RequestConfiguration.PublisherPrivacyPersonalizationState {
    return when (this) {
        PublisherPrivacyPersonalizationState.DEFAULT -> DEFAULT
        PublisherPrivacyPersonalizationState.ENABLED -> ENABLED
        PublisherPrivacyPersonalizationState.DISABLED -> DISABLED
    }
}