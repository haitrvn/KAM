package com.haitrvn.kal.core

import cocoapods.AppLovinSDK.MAAd
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
actual class Ad(
    val ios: MAAd
)