package com.haitrvn.kam.appopen

import androidx.compose.runtime.Composable
import com.haitrvn.kam.AdRequest
import com.haitrvn.kam.AdValue
import com.haitrvn.kam.FullScreenContent
import com.haitrvn.kam.ResponseInfo
import com.haitrvn.kam.RootView
import kotlinx.coroutines.flow.Flow

expect class AppOpen {
    companion object {
        suspend fun load(unitId: String, request: AdRequest): AppOpen?
        fun pollAd(unitId: String): AppOpen?
        fun isAvailable(unitId: String): Boolean
    }

    val unitId: String
    val responseInfo: ResponseInfo
    val fullScreenContentFlow: Flow<FullScreenContent>
    val paidEventFlow: Flow<AdValue>
    fun setImmersiveMode(immersive: Boolean)

    @Composable
    fun show()
    fun show(rootView: RootView)
}