package com.haitrvn.kam.core.init

import android.content.Context
import android.util.Log
import androidx.startup.Initializer

class KamContextProviderInitialization : Initializer<ContextProvider> {
    companion object {
        const val TAG = "haitrvn"
    }

    override fun create(context: Context): ContextProvider {
        Log.d(TAG, "create KamContextProviderInitialization $context")
        return ContextProvider.apply {
            applicationContext = context.applicationContext
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> {
        return emptyList()
    }
}