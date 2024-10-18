package com.haitrvn.kam.core.init

import com.haitrvn.kam.core.callback.CustomTabsCallback
import com.haitrvn.kam.core.callback.CustomTabsClient
import com.haitrvn.kam.core.model.InitializationStatus
import com.haitrvn.kam.core.model.RequestConfiguration
import kotlin.reflect.KClass

expect class KamInitializer {
    fun initialize(onComplete: () -> Unit = {})
    fun disableMediationAdapterInitialization()
    fun setAppVolume(volume: Float)
    fun getVersion(): String
    fun setAppMuted(muted: Boolean)
    internal fun setRequestConfiguration(requestConfiguration: RequestConfiguration)
    internal fun startPreload()
    internal fun getInitializationStatus(): InitializationStatus
    internal fun getRequestConfiguration(): RequestConfiguration
    internal fun openAdInspector()
    internal fun openDebugMenu(adUnitId: String)
    internal fun putPublisherFirstPartyIdEnabled(enabled: Boolean)
    internal fun registerCustomTabsSession(customTabsClient: CustomTabsClient, origin: String, customTabsCallback: CustomTabsCallback)
    internal fun registerRtbAdapter(rtbAdapter: KClass<RtbAdapter>)
    internal fun registerWebView(webView: WebView)
}