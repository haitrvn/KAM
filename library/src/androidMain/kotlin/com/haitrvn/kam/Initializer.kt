package com.haitrvn.kam

import com.google.android.gms.ads.MobileAds
import com.haitrvn.kam.util.ContextProvider

actual object Initializer {
    actual fun initialize(complete: (InitializationStatus) -> Unit) {
        MobileAds.initialize(ContextProvider.context) {
            complete(InitializationStatus(it))
        }
    }
}

actual class InitializationStatus(
    private val android: com.google.android.gms.ads.initialization.InitializationStatus
) {
    actual val adapterStatusMap: Map<String, AdapterStatus>
        get() = android.adapterStatusMap.mapValues { AdapterStatus(it.value) }
}

actual class AdapterStatus(
    private val android: com.google.android.gms.ads.initialization.AdapterStatus
) {
    actual val latency: Int
        get() = android.latency

    actual val state: State
        get() = android.initializationState.toCommon()

    actual val description: String
        get() = android.description
}

private fun com.google.android.gms.ads.initialization.AdapterStatus.State.toCommon(): State {
    return when (this) {
        com.google.android.gms.ads.initialization.AdapterStatus.State.NOT_READY -> State.NOT_READY
        com.google.android.gms.ads.initialization.AdapterStatus.State.READY -> State.READY
    }
}
