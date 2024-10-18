package com.haitrvn.kam.interstitial

import cocoapods.Google_Mobile_Ads_SDK.GADAdValue
import cocoapods.Google_Mobile_Ads_SDK.GADFullScreenContentDelegateProtocol
import cocoapods.Google_Mobile_Ads_SDK.GADFullScreenPresentingAdProtocol
import cocoapods.Google_Mobile_Ads_SDK.GADInterstitialAd
import com.haitrvn.kam.core.RootView
import com.haitrvn.kam.core.callback.KamFullScreenContentCallBack
import com.haitrvn.kam.core.model.KamAdError
import com.haitrvn.kam.core.model.KamAdValue
import com.haitrvn.kam.core.model.PrecisionType
import com.haitrvn.kam.core.model.ResponseInfo
import com.haitrvn.kam.core.request.KamRequest
import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCClass
import kotlinx.coroutines.suspendCancellableCoroutine
import platform.Foundation.NSError
import platform.darwin.NSUInteger
import kotlin.coroutines.resume
import kotlin.math.absoluteValue

@ExperimentalForeignApi
actual class KamInterstitial(
    private val interstitialAd: GADInterstitialAd
) {
    private var immersive: Boolean = false

    actual fun show(rootView: RootView) {
        interstitialAd.presentFromRootViewController(rootView)
    }

    internal actual fun setImmersiveMode(immersive: Boolean) {
        this.immersive = immersive
    }

    actual fun setFullScreenContentCallback(callback: KamFullScreenContentCallBack) {
//        interstitialAd.fullScreenContentDelegate =
//            GADFullScreenContentDelegateProtocolImpl(callback)
    }

    actual fun setOnPaidEventListener(callback: (KamAdValue?) -> Unit) {
        interstitialAd.setPaidEventHandler {
            callback(it?.toKamAdValue())
        }
    }

    private fun GADAdValue.toKamAdValue(): KamAdValue {
        return KamAdValue(
            precisionType = PrecisionType.entries.firstOrNull { it.type == precision.absoluteValue.toInt() }
                ?: PrecisionType.UNKNOWN,
            valueMicros = value.longValue,
            currencyCode = currencyCode
        )
    }

    internal actual fun getAdUnitId(): String {
        TODO("Not yet implemented")
    }

    internal actual fun getFullScreenContentCallback(): KamFullScreenContentCallBack {
        TODO("Not yet implemented")
    }

    internal actual fun getResponseInfo(): ResponseInfo {
        TODO("Not yet implemented")
    }

    actual companion object {
        actual suspend fun load(
            adUnitId: String,
            request: KamRequest,
        ): KamInterstitial? = suspendCancellableCoroutine { continuation ->
            GADInterstitialAd.loadWithAdUnitID(adUnitId, request) { ad, error ->
                when {
                    ad != null -> continuation.resume(KamInterstitial(ad))
                    else -> continuation.resume(null)
                }
            }
        }

        actual suspend fun load(
            adUnitId: String,
            request: KamRequest,
            callback: (KamInterstitial?, KamAdError?) -> Unit
        ) {
            GADInterstitialAd.loadWithAdUnitID(adUnitId, request) { ad, error ->
                when {
                    ad != null -> callback(KamInterstitial(ad), null)
                    else -> callback(null, error?.toKamAdError())
                }
            }
        }

        private fun NSError.toKamAdError(): KamAdError {
            return KamAdError(
                code = code.toInt(),
                cause = localizedFailureReason().orEmpty(),
                domain = domain.toString(),
                message = localizedDescription
            )
        }
        internal actual fun isAdAvailable(adUnitId: String): Boolean {
            TODO("Not yet implemented")
        }

        internal actual fun pollAd(adUnitId: String): KamInterstitial? {
            TODO("Not yet implemented")
        }
    }
}

//@ExperimentalForeignApi
//class GADFullScreenContentDelegateProtocolImpl(
//    private val callback: KamFullScreenContentCallBack
//) : GADFullScreenContentDelegateProtocol {
//    override fun adDidDismissFullScreenContent(ad: GADFullScreenPresentingAdProtocol) {
//        super.adDidDismissFullScreenContent(ad)
//        callback.onAdDismissedFullScreenContent()
//    }
//
//    override fun adDidRecordClick(ad: GADFullScreenPresentingAdProtocol) {
//        super.adDidRecordClick(ad)
//        callback.onAdClicked()
//    }
//
//    override fun adDidRecordImpression(ad: GADFullScreenPresentingAdProtocol) {
//        super.adDidRecordImpression(ad)
//        callback.onAdShowedFullScreenContent()
//    }
//
//    override fun description(): String? {
//        return super.description
//    }
//
//    @BetaInteropApi
//    override fun `class`(): ObjCClass? {
//        return super.superclass
//    }
//
//    override fun hash(): NSUInteger {
//        return super.hash
//    }
//
//    override fun isEqual(`object`: Any?): Boolean {
//        return super.equals(`object`)
//    }
//
//    @OptIn(BetaInteropApi::class)
//    override fun isKindOfClass(aClass: ObjCClass?): Boolean {
//        return aClass != null && isKindOfClass(aClass)
//    }
//
//    @OptIn(BetaInteropApi::class)
//    override fun isMemberOfClass(aClass: ObjCClass?): Boolean {
//        return aClass != null && this.isKindOfClass(aClass)
//    }
//
//    override fun isProxy(): Boolean {
//        return false
//    }
//
//    override fun performSelector(aSelector: COpaquePointer?): Any {
//        return kotlin.runCatching {
//            Any()
//        }
//    }
//
//    override fun performSelector(aSelector: COpaquePointer?, withObject: Any?): Any {
//        return kotlin.runCatching {
//            Any()
//        }
//    }
//
//    override fun performSelector(
//        aSelector: COpaquePointer?,
//        withObject: Any?,
//        _withObject: Any?
//    ): Any {
//        return kotlin.runCatching {
//            Any()
//        }
//    }
//
//    override fun respondsToSelector(aSelector: COpaquePointer?): Boolean {
//        return false
//    }
//
//    @OptIn(BetaInteropApi::class)
//    override fun superclass(): ObjCClass? {
//        return super.superclass
//    }
//
//    override fun conformsToProtocol(aProtocol: Protocol?): Boolean {
//        TODO("Not yet implemented")
//    }
//}
