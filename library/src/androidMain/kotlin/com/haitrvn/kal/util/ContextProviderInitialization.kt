package com.haitrvn.kal.util

import android.content.Context
import androidx.startup.Initializer

class ContextProviderInitialization : Initializer<ContextProvider> {
    override fun create(context: Context): ContextProvider {
        return ContextProvider.apply {
            applicationContext = context.applicationContext
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}