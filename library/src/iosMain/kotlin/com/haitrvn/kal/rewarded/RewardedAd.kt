package com.haitrvn.kal.rewarded

import androidx.lifecycle.Lifecycle
import cocoapods.AppLovinSDK.MAAd
import cocoapods.AppLovinSDK.MAAdReviewDelegateProtocol
import cocoapods.AppLovinSDK.MARewardedAd
import com.haitrvn.kal.core.Ad
import com.haitrvn.kal.core.RootView
import com.haitrvn.kal.initialization.AppLovinSdk
import com.haitrvn.kal.model.MaxRewarded
import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.ObjCClass
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import objcnames.classes.Protocol
import platform.darwin.NSObjectProtocol
import platform.darwin.NSUInteger

@OptIn(ExperimentalForeignApi::class)
class DefaultNSObjectProtocol : NSObjectProtocol, ObjCClass {
    override fun debugDescription(): String? {
        return "DefaultNSObjectProtocol"
    }

    override fun description(): String? {
        return "this is default implementation of NSObjectProtocol"
    }

    override fun hash(): NSUInteger {
        return NSUInteger.MIN_VALUE
    }

    override fun superclass(): ObjCClass? {
        return this
    }

    override fun `class`(): ObjCClass? {
        return this
    }

    override fun conformsToProtocol(aProtocol: Protocol?): Boolean {
        return false
    }

    override fun isEqual(`object`: Any?): Boolean {
        return `object`?.equals(this) ?: false
    }

    override fun isKindOfClass(aClass: ObjCClass?): Boolean {
        return false
    }

    override fun isMemberOfClass(aClass: ObjCClass?): Boolean {
    }

    override fun isProxy(): Boolean {
        return false
    }

    override fun performSelector(aSelector: COpaquePointer?): Any? {
        return null
    }

    override fun performSelector(aSelector: COpaquePointer?, withObject: Any?): Any? {
        return null
    }

    override fun performSelector(
        aSelector: COpaquePointer?,
        withObject: Any?,
        _withObject: Any?
    ): Any? {
        return null
    }

    override fun respondsToSelector(aSelector: COpaquePointer?): Boolean {
        return false
    }
}

@OptIn(ExperimentalForeignApi::class)
abstract class DefaultMAAdReviewDelegateProtocol(
    private val defaultNSObjectProtocol: DefaultNSObjectProtocol = DefaultNSObjectProtocol()
) : MAAdReviewDelegateProtocol, NSObjectProtocol by defaultNSObjectProtocol {
    override fun debugDescription(): String {
        return "DefaultMAAdReviewDelegateProtocol"
    }
}

@OptIn(ExperimentalForeignApi::class)
actual class RewardedAd actual constructor(
    adUnitId: String,
    appLovinSdk: AppLovinSdk?
) {
    private val iosRewardedAd: MARewardedAd by lazy {
        if (appLovinSdk != null) {
            MARewardedAd.sharedWithAdUnitIdentifier(adUnitId, appLovinSdk.iosApplovinSdk)
        } else {
            MARewardedAd.sharedWithAdUnitIdentifier(adUnitId)
        }
    }
    actual val isReady: Boolean
        get() = iosRewardedAd.ready
    actual val unitId: String
        get() = iosRewardedAd.adUnitIdentifier
    actual val reviewFlow: Flow<Ad>
        get() = callbackFlow {
            iosRewardedAd.adReviewDelegate =
                object : DefaultMAAdReviewDelegateProtocol() {
                    override fun didGenerateCreativeIdentifier(
                        creativeIdentifier: String,
                        forAd: MAAd
                    ) {
                        trySend(forAd)
                    }
                }
        }
    actual val expirationFlow: Flow<Pair<Ad, Ad>>
        get() = TODO("Not yet implemented")
    actual val revenueFlow: Flow<Ad>
        get() = TODO("Not yet implemented")
    actual val requestFlow: Flow<String>
        get() = TODO("Not yet implemented")
    actual val rewardedAd: Flow<MaxRewarded>
        get() = TODO("Not yet implemented")

    actual fun loadAd() {
    }

    actual fun setExtraParameter(key: String, value: String) {
    }

    internal actual fun setLocalExtraParameter(key: String, param: Any) {
    }

    actual fun showAd(rootView: RootView) {
    }

    actual fun showAd(placement: String, rootView: RootView) {
    }

    actual fun showAd(
        placement: String,
        customData: String,
        rootView: RootView
    ) {
    }

    internal actual fun showAd(
        viewGroup: ViewGroup,
        lifecycle: Lifecycle,
        rootView: RootView
    ) {
    }

    internal actual fun showAd(
        placement: String,
        viewGroup: ViewGroup,
        lifecycle: Lifecycle,
        rootView: RootView
    ) {
    }

    internal actual fun showAd(
        placement: String,
        customData: String,
        viewGroup: ViewGroup,
        lifecycle: Lifecycle,
        rootView: RootView
    ) {
    }

    actual fun destroy() {
    }
}

actual abstract class ViewGroup
