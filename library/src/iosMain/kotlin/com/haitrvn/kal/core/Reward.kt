package com.haitrvn.kal.core

import cocoapods.AppLovinSDK.MAReward
import kotlinx.cinterop.ExperimentalForeignApi

@OptIn(ExperimentalForeignApi::class)
actual class Reward constructor(
    val reward: MAReward
)