package com.haitrvn.kam.util

import android.content.Context
import androidx.startup.Initializer

class ContextInitialization : Initializer<ContextProvider> {
    override fun create(context: Context): ContextProvider {
        return ContextProvider.apply {
            ContextProvider.context = context.applicationContext
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}