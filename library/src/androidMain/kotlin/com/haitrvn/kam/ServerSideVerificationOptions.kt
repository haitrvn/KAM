package com.haitrvn.kam

import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.admanager.AdManagerAdRequest
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback
import com.haitrvn.kam.AdError
import com.haitrvn.kam.AdRequest
import com.haitrvn.kam.AdValue
import com.haitrvn.kam.FullScreenContent
import com.haitrvn.kam.ResponseInfo
import com.haitrvn.kam.RootView
import com.haitrvn.kam.toCommon
import com.haitrvn.kam.util.ContextProvider
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

actual class ServerSideVerificationOptions(
    val android: com.google.android.gms.ads.rewarded.ServerSideVerificationOptions
) {
    actual companion object {
        actual fun createInstance(
            block: ServerSideVerificationOptions.Builder.() -> Unit
        ): ServerSideVerificationOptions {
            val builder = ServerSideVerificationOptions.Builder()
                .apply(block)
            return builder.build()
        }
    }

    actual class Builder(
        private val android: com.google.android.gms.ads.rewarded.ServerSideVerificationOptions.Builder =
            com.google.android.gms.ads.rewarded.ServerSideVerificationOptions.Builder()
    ) {
        actual fun setCustomData(customData: String): Builder {
            android.setCustomData(customData)
            return this
        }

        actual fun setUserId(userId: String): Builder {
            android.setUserId(userId)
            return this
        }

        actual fun build(): ServerSideVerificationOptions {
            return ServerSideVerificationOptions(android.build())
        }
    }
}