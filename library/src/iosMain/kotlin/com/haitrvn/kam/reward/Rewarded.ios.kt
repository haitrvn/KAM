package com.haitrvn.kam.reward

import com.haitrvn.kam.AdRequest
import com.haitrvn.kam.AdValue
import com.haitrvn.kam.FullScreenContent
import com.haitrvn.kam.ResponseInfo
import com.haitrvn.kam.RootView
import com.haitrvn.kam.ServerSideVerificationOptions
import kotlinx.coroutines.flow.Flow

actual class Rewarded {
    actual companion object {
        actual suspend fun load(
            unitId: String,
            request: AdRequest
        ): Rewarded? {
            TODO("Not yet implemented")
        }

        actual fun pollAd(unitId: String): Rewarded? {
            return null
        }

        actual fun isAvailable(unitId: String): Boolean {
            return false
        }
    }

    actual val unitId: String
        get() = TODO("Not yet implemented")

    actual val responseInfo: ResponseInfo
        get() = TODO("Not yet implemented")

    actual val rewardedItem: RewardItem
        get() = TODO("Not yet implemented")

    actual val adMetaData: Any
        get() = TODO("Not yet implemented")

    actual val fullScreenContentFlow: Flow<FullScreenContent>
        get() = TODO("Not yet implemented")

    actual val paidEventFlow: Flow<AdValue>
        get() = TODO("Not yet implemented")

    actual val dataChangedFlow: Flow<Unit>
        get() = TODO("Not yet implemented")

    actual fun setServerSideVerificationOptions(option: ServerSideVerificationOptions) {
    }

    actual fun setImmersiveMode(immersive: Boolean) {
    }

    actual fun show(
        rootView: RootView,
        onUserEarnedReward: (RewardItem) -> Unit
    ) {
    }
}

actual class RewardItem