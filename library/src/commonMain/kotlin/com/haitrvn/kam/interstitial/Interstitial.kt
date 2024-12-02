package com.haitrvn.kam.interstitial

import androidx.compose.runtime.Composable
import com.haitrvn.kam.AdRequest
import com.haitrvn.kam.RootView
import com.haitrvn.kam.AdValue
import com.haitrvn.kam.FullScreenContent
import com.haitrvn.kam.ResponseInfo
import kotlinx.coroutines.flow.Flow

expect class Interstitial {
    companion object {
        fun isAvailable(unitId: String): Boolean
        fun pollAd(unitId: String): Interstitial?
        suspend fun load(unitId: String, adRequest: AdRequest): Interstitial?
    }

    val unitId: String
    val paidEventFlow: Flow<AdValue>
    val responseInfo: ResponseInfo
    val fullScreenContentFlow: Flow<FullScreenContent>
    fun setImmersiveMode(immersive: Boolean)
    @Composable
    fun show()
    fun show(rootView: RootView)
}