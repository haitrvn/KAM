package com.haitrvn.kam.interstitial

import android.content.Context
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.haitrvn.kam.core.model.KamAdError
import com.haitrvn.kam.core.request.KamRequest
import com.haitrvn.kam.extension.toKamError
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

actual class KamInterstitialLoader(
    private val context: Context
) {

}