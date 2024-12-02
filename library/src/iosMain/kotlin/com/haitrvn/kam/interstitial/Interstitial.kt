package com.haitrvn.kam.interstitial

import androidx.compose.runtime.Composable
import com.haitrvn.kam.AdRequest
import com.haitrvn.kam.AdValue
import com.haitrvn.kam.FullScreenContent
import com.haitrvn.kam.ResponseInfo
import com.haitrvn.kam.RootView
import kotlinx.coroutines.flow.Flow

actual class Interstitial {
    actual companion object {
        actual fun isAvailable(unitId: String): Boolean {
            TODO("Not yet implemented")
        }

        actual fun pollAd(unitId: String): Interstitial? {
            TODO("Not yet implemented")
        }

        actual suspend fun load(
            unitId: String,
            adRequest: AdRequest
        ): Interstitial? {
            TODO("Not yet implemented")
        }
    }

    actual val unitId: String
        get() = TODO("Not yet implemented")
    actual val paidEventFlow: Flow<AdValue>
        get() = TODO("Not yet implemented")
    actual val responseInfo: ResponseInfo
        get() = TODO("Not yet implemented")
    actual val fullScreenContentFlow: Flow<FullScreenContent>
        get() = TODO("Not yet implemented")

    actual fun setImmersiveMode(immersive: Boolean) {
    }

    @Composable
    actual fun show() {
    }

    actual fun show(rootView: RootView) {}
}