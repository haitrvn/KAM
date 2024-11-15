package com.haitrvn.kal.initialization

import cocoapods.AppLovinSDK.ALSdk
import cocoapods.AppLovinSDK.initializeSdkWithCompletionHandler
import kotlinx.atomicfu.locks.SynchronizedObject
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
import kotlin.concurrent.Volatile

@OptIn(ExperimentalForeignApi::class)
actual class AppLovinSdk private constructor(
    var iosApplovinSdk: ALSdk
) {
    actual companion object {
        @Volatile
        private var instance: AppLovinSdk? = null
        val myLock = SynchronizedObject()

        @OptIn(InternalCoroutinesApi::class)
        actual fun getInstance(): AppLovinSdk {
            return instance ?: synchronized(myLock) {
                instance ?: AppLovinSdk(
                    ALSdk.shared()
                ).also { instance = it }
            }
        }
    }

    actual fun initializeSdk(
        configuration: InitConfiguration,
        completedInformation: (SdkInformation) -> Unit
    ) {
    }
}