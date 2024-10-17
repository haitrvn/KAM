package com.haitrvn.kam.core.init

import cocoapods.Google_Mobile_Ads_SDK.GADMobileAds
import com.haitrvn.kam.core.model.InitializationStatus
import kotlinx.cinterop.ExperimentalForeignApi
import kotlin.reflect.KClass

@OptIn(ExperimentalForeignApi::class)
actual class KamInitializer {
    actual fun initialize(onComplete: () -> Unit) {
        GADMobileAds.sharedInstance().startWithCompletionHandler {
        }
        GADMobileAds.sharedInstance().requestConfiguration
    }

    actual fun disableMediationAdapterInitialization() {
        GADMobileAds.sharedInstance().disableMediationInitialization()
    }

    actual fun setRequestConfiguration(requestConfiguration: RequestConfiguration) {
    }

    internal actual fun startPreload() {
    }

    actual fun setAppVolume(volume: Float) {
        GADMobileAds.sharedInstance().setApplicationVolume(volume)
    }

    actual fun getVersion(): String {
        return GADMobileAds.sharedInstance().versionNumber().toString()
    }

    actual fun setAppMuted(muted: Boolean) {
        GADMobileAds.sharedInstance().setApplicationMuted(muted)
    }

    internal actual fun getInitializationStatus(): InitializationStatus {
        TODO()
    }

    internal actual fun getRequestConfiguration(): RequestConfiguration {
        TODO()
    }

    internal actual fun openAdInspector() {
    }

    internal actual fun openDebugMenu(adUnitId: String) {
    }

    internal actual fun putPublisherFirstPartyIdEnabled(enabled: Boolean) {
    }

    internal actual fun registerCustomTabsSession(customTabsClient: CustomTabsClient, origin: String, customTabsCallback: CustomTabsCallback) {
    }

    internal actual fun registerRtbAdapter(rtbAdapter: KClass<RtbAdapter>) {
    }

    internal actual fun registerWebView(webView: WebView) {
    }
}