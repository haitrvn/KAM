package com.haitrvn.kamsample

import android.app.Application
import com.haitrvn.kam.Initializer

class SampleApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        Initializer.initialize {
            println("Initialize status: $it")
        }
    }
}