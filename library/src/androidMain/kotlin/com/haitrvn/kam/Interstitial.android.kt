package com.haitrvn.kam

import com.google.android.gms.ads.interstitial.InterstitialAd

class Interstitial(

) {


    fun load(unitId: String, adRequest: AdRequest) {
        InterstitialAd.load()
    }
}

class AdRequest(
    val android: com.google.android.gms.ads.AdRequest
)