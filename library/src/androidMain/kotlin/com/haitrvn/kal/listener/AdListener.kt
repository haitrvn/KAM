package com.haitrvn.kal.listener

import android.app.WallpaperInfo
import com.applovin.mediation.MaxAd
import com.applovin.mediation.MaxAdListener
import com.applovin.mediation.MaxAdWaterfallInfo
import com.applovin.mediation.MaxError
import com.applovin.mediation.MaxNetworkResponseInfo
import com.haitrvn.kal.core.Ad
import com.haitrvn.kal.core.AdError
import com.haitrvn.kal.core.NetworkResponseInfo
import com.haitrvn.kal.core.WaterfallInfo

actual interface AdListener : MaxAdListener {
    override fun onAdDisplayFailed(p0: MaxAd, p1: MaxError) {
        onAdDisplayFailed(p0, p1.toCommonError())
    }

    override fun onAdLoadFailed(p0: String, p1: MaxError) {
        onAdLoadFailed(p0, p1.toCommonError())
    }
    actual fun onAdDisplayFailed(ad: Ad, adError: AdError)
    actual fun onAdLoadFailed(value: String, adError: AdError)
}

fun MaxError.toCommonError(): AdError {
    return AdError(
        code = code,
        message = message,
        mediatedNetworkErrorCode = mediatedNetworkErrorCode,
        mediatedNetworkErrorMessage = mediatedNetworkErrorMessage,
        waterfallInfo = waterfall.toCommonWaterfallInfo(),
        requestLatencyMillis = requestLatencyMillis
    )
}

private  fun MaxAdWaterfallInfo.toCommonWaterfallInfo(): WaterfallInfo {
    return WaterfallInfo(
        loadedAd = loadedAd,
        name = name,
        testName = testName,
        networkResponses = networkResponses.map { it.toCommonNetworkResponse() },
        latencyMillis = latencyMillis
    )
}

private fun MaxNetworkResponseInfo.toCommonNetworkResponse(): NetworkResponseInfo {
    return NetworkResponseInfo(
        loadStates = adLoadState.toCommonAdLoadState(),
        mediatedNetwork = mediatedNetwork.toCommonNetwork(),
        credential = credentials,
        isBidding = isBidding,
        latencyMillis = latencyMillis,
        adError = error?.toCommonError()
    )
}