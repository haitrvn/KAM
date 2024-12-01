package com.haitrvn.kam

import android.content.Context
import androidx.startup.Initializer

class ContextInitialization : Initializer<ContextProvider> {
    override fun create(context: Context): ContextProvider {
        return ContextProvider.apply {
            this.context = context.applicationContext
        }
    }

    override fun dependencies(): List<Class<out Initializer<*>>> = emptyList()
}