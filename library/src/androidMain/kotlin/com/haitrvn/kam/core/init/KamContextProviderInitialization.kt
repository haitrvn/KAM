package com.haitrvn.kam.core.init

import android.content.Context
import androidx.startup.Initializer

class KamContextProviderInitialization : Initializer<ContextProvider> {
    override fun create(context: Context): ContextProvider {
        return ContextProvider.apply {
            applicationContext = context.applicationContext
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}