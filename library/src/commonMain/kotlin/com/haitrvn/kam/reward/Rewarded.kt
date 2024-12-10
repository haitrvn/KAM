package com.haitrvn.kam.reward

import com.haitrvn.kam.AdRequest
import com.haitrvn.kam.AdValue
import com.haitrvn.kam.FullScreenContent
import com.haitrvn.kam.ResponseInfo
import com.haitrvn.kam.RootView
import com.haitrvn.kam.ServerSideVerificationOptions
import kotlinx.coroutines.flow.Flow

expect class Rewarded {
    companion object {
        suspend fun load(
            unitId: String,
            request: AdRequest
        ): Rewarded?

        fun pollAd(unitId: String): Rewarded?
        fun isAvailable(unitId: String): Boolean
    }

    val unitId: String
    val responseInfo: ResponseInfo
    val rewardedItem: RewardItem
    val adMetaData: Any
    val fullScreenContentFlow: Flow<FullScreenContent>
    val paidEventFlow: Flow<AdValue>
    val dataChangedFlow: Flow<Unit>
    fun setServerSideVerificationOptions(option: ServerSideVerificationOptions)
    fun setImmersiveMode(immersive: Boolean)
    fun show(rootView: RootView, onUserEarnedReward: (RewardItem) -> Unit)
}