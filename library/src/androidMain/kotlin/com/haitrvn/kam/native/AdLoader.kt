package com.haitrvn.kam.native

import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.LoadAdError
import com.haitrvn.kam.AdRequest
import com.haitrvn.kam.util.ContextProvider
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

actual class AdLoader(
    private val android: AdLoader
) {
    actual companion object {

        actual suspend fun load(
            unitId: String,
            request: AdRequest,
            adOptions: AdOptions?,
        ): NativeAd? {
            return suspendCancellableCoroutine { continuation ->
                val adListener = object : AdListener() {
                    override fun onAdFailedToLoad(p0: LoadAdError) {
                        super.onAdFailedToLoad(p0)
                        continuation.resume(null)
                    }
                }
                val listenerGroup = AdListenerGroup(mutableListOf(adListener))
                AdLoader.Builder(ContextProvider.context, unitId).apply {
                    adOptions?.android?.let { it1 -> withNativeAdOptions(it1) }
                    withAdListener(listenerGroup)
                    forNativeAd {
                        continuation.resume(NativeAd(it).apply {
                            this.listenerGroup = listenerGroup
                        })
                    }
                }.build().also {
                    it.loadAd(request.android)
                }
            }
        }

        actual suspend fun loads(
            unitId: String,
            request: AdRequest,
            maxNumberOfAds: Int,
            adOptions: AdOptions?,
        ): NativeAd? {
            return suspendCancellableCoroutine { continuation ->
                val adListener = object : AdListener() {
                    override fun onAdFailedToLoad(p0: LoadAdError) {
                        super.onAdFailedToLoad(p0)
                        continuation.resume(null)
                    }
                }
                val listenerGroup = AdListenerGroup(mutableListOf(adListener))
                AdLoader.Builder(ContextProvider.context, unitId).apply {
                    adOptions?.android?.let { it1 -> withNativeAdOptions(it1) }
                    withAdListener(listenerGroup)
                    forNativeAd {
                        continuation.resume(NativeAd(it).apply {
                            this.listenerGroup = listenerGroup
                        })
                    }
                }.build().also {
                    it.loadAds(request.android, maxNumberOfAds)
                }
            }
        }
    }

    actual val isLoading: Boolean
        get() = android.isLoading
}

