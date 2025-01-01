package com.haitrvn.kam.native

import com.haitrvn.kam.AdEvent
import com.haitrvn.kam.AdValue
import com.haitrvn.kam.ResponseInfo
import kotlinx.coroutines.flow.Flow

expect class NativeAd {
    internal val adChoicesInfo: AdChoicesInfo?

    val extras: Any
    val mediaContent: MediaContent?
    val responseInfo: ResponseInfo?
    val icon: Image?
    val starRating: Double?
    val advertiser: String?
    val body: String?
    val callToAction: String?
    val headline: String?
    val price: String?
    val store: String?
    val images: List<Image>
    val muteThisAdReasons: List<MuteThisAdReason>
    val isCustomMuteThisAdEnabled: Boolean
    val paidEventFlow: Flow<AdValue>
    val unconfirmedClickFlow: Flow<UnconfirmedClickEvent>
    val adMutedFlow: Flow<Unit>
    val adListenerEvent: Flow<AdEvent>
    fun cancelUnconfirmedClick()
    fun destroy()
    fun muteThisAd(reason: MuteThisAdReason)
    fun performClick(bundle: Any)
    fun reportTouchEvent(bundle: Any)
    fun recordImpression(bundle: Any)
}