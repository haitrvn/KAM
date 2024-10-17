package com.haitrvn.kam.core.init

import android.content.Context
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.mediation.rtb.RtbAdapter
import com.haitrvn.kam.core.model.AdapterStatus
import com.haitrvn.kam.core.model.InitializationStatus
import com.haitrvn.kam.core.model.State
import com.haitrvn.kam.extension.toCommonConfiguration
import com.haitrvn.kam.extension.toPlatformConfiguration
import kotlin.reflect.KClass
import com.google.android.gms.ads.initialization.AdapterStatus.State as AndroidState

actual class KamInitializer(private val context: Context) {
    actual fun initialize(onComplete: () -> Unit) {
        MobileAds.initialize(context) {
            onComplete()
        }
    }

    actual fun setAppVolume(volume: Float) {
        MobileAds.setAppVolume(volume)
    }

    actual fun setAppMuted(muted: Boolean) {
        MobileAds.setAppMuted(muted)
    }

    actual fun disableMediationAdapterInitialization() {
        MobileAds.disableMediationAdapterInitialization(context)
    }

    actual fun setRequestConfiguration(requestConfiguration: RequestConfiguration) {
        MobileAds.setRequestConfiguration(requestConfiguration.toPlatformConfiguration())
    }

    internal actual fun startPreload() {
    }

    actual fun getVersion(): String {
        return MobileAds.getVersion().toString()
    }

    internal actual fun getInitializationStatus(): InitializationStatus {
        val adapterStatus = MobileAds.getInitializationStatus()?.adapterStatusMap?.mapValues {
            AdapterStatus(
                latency = it.value.latency,
                initializationState = it.value.initializationState.toKamInitializationState(),
                description = it.value.description
            )
        } ?: emptyMap()
        return InitializationStatus(adapterStatus)
    }

    internal actual fun getRequestConfiguration(): RequestConfiguration {
        return MobileAds.getRequestConfiguration().toCommonConfiguration()
    }

    internal actual fun openAdInspector() {
        MobileAds.openAdInspector(context) {

        }
    }

    internal actual fun openDebugMenu(adUnitId: String) {
        MobileAds.openDebugMenu(context, adUnitId)
    }

    internal actual fun putPublisherFirstPartyIdEnabled(enabled: Boolean) {
        MobileAds.putPublisherFirstPartyIdEnabled(enabled)
    }

    internal actual fun registerCustomTabsSession(
        customTabsClient: CustomTabsClient,
        origin: String,
        customTabsCallback: CustomTabsCallback
    ) {
        MobileAds.registerCustomTabsSession(context, customTabsClient, origin, customTabsCallback)
    }

    internal actual fun registerRtbAdapter(rtbAdapter: KClass<RtbAdapter>) {
        MobileAds.registerRtbAdapter(rtbAdapter.java)
    }

    internal actual fun registerWebView(webView: WebView) {
        MobileAds.registerWebView(webView)
    }
}

private fun AndroidState.toKamInitializationState(): State {
    return when (this) {
        AndroidState.NOT_READY -> State.NOT_READY
        AndroidState.READY -> State.READY
    }
}
