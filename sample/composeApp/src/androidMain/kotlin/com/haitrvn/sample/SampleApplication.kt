package com.haitrvn.sample

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