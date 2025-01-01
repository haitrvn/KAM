package com.haitrvn.kam

import com.google.android.gms.ads.MobileAds
import com.haitrvn.kam.util.ContextProvider
import com.google.android.gms.ads.initialization.InitializationStatus as AndroidInitializationStatus
import com.google.android.gms.ads.initialization.AdapterStatus as AndroidAdapterStatus
actual object Initializer {
    actual fun initialize(complete: (InitializationStatus) -> Unit) {
        MobileAds.initialize(ContextProvider.context) {
            complete(InitializationStatus(it))
        }
    }
}

actual class InitializationStatus(
    private val android: AndroidInitializationStatus
) {
    actual val adapterStatusMap: Map<String, AdapterStatus>
        get() = android.adapterStatusMap.mapValues { AdapterStatus(it.value) }
}

actual class AdapterStatus(
    private val android: AndroidAdapterStatus
) {
    actual val latency: Int
        get() = android.latency

    actual val state: State
        get() = android.initializationState.toCommon()

    actual val description: String
        get() = android.description
}

private fun AndroidAdapterStatus.State.toCommon(): State {
    return when (this) {
        AndroidAdapterStatus.State.NOT_READY -> State.NOT_READY
        AndroidAdapterStatus.State.READY -> State.READY
    }
}
