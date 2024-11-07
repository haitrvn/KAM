package com.haitrvn.kal.initialization

import kotlinx.atomicfu.locks.SynchronizedObject
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized
import kotlin.concurrent.Volatile

class ALSdk {
    companion object {
        fun shared(): ALSdk {
            TODO()
        }
    }

    fun initializeSdk(
        configuration: AppLovinSdkInitializationConfiguration,
        onComplete: () -> Unit
    ) {

    }
}

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
        configuration: AppLovinSdkInitializationConfiguration,
        initializationListener: SdkInitializationListener
    ) {
        iosApplovinSdk.initializeSdk(configuration) {
            initializationListener.onSdkInitialized()
        }
    }
}