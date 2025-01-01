package com.haitrvn.kamsample

import com.haitrvn.kam.native.NativeAdViewBinding
import com.haitrvn.kam.util.ContextProvider

actual fun createNativeBinding(): NativeAdViewBinding {
    return NativeView(ContextProvider.context)
}