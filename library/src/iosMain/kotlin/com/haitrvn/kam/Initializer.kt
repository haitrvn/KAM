package com.haitrvn.kam

import cocoapods.Google_Mobile_Ads_SDK.GADAdapterStatus
import cocoapods.Google_Mobile_Ads_SDK.GADMobileAds
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
actual object Initializer {
    actual fun initialize(complete: (InitializationStatus) -> Unit) {
        GADMobileAds.sharedInstance().startWithCompletionHandler { status ->
            complete(InitializationStatus(
                status?.adapterStatusesByClassName
                    ?.mapKeys { it.key as String }
                    ?.mapValues { AdapterStatus(it.value as GADAdapterStatus) } ?: mapOf()
            ))
        }
    }
}

@OptIn(ExperimentalForeignApi::class)
actual class AdapterStatus(
    private val ios: GADAdapterStatus
) {
    actual val latency: Int
        get() = ios.latency.toInt()
    actual val state: State
        get() = ios.state.toCommon()
    actual val description: String
        get() = ios.description.toString()

    actual enum class State {
        NOT_READY, READY
    }

}

actual class InitializationStatus(
    actual val adapterStatusMap: Map<String, AdapterStatus>
)