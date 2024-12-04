package com.haitrvn.kam.banner

import cocoapods.Google_Mobile_Ads_SDK.GADAdSize

actual open class AdSize(
    private val ios: GADAdSize
) {
    actual object Banner : AdSize(GADAdSize.GADAdSizeBanner)
    actual object FullBanner : AdSize(GADAdSize.GADAdSizeFullBanner)
    actual object LargeBanner : AdSize(GADAdSize.GADAdSizeLargeBanner)
    actual object LeaderBoard : AdSize(GADAdSize.GADAdSizeLeaderboard)
    actual object MediumRectangle : AdSize(GADAdSize.GADAdSizeMediumRectangle)
    actual object WideSkyScraper : AdSize(GADAdSize.GADAdSizeSkyscraper)
    actual object Fluid : AdSize(GADAdSize.GADAdSizeFluid)
    actual object Invalid : AdSize(GADAdSize.GADAdSizeInvalid)
    actual object Search : AdSize(GADAdSize(-3, 0))
    actual class Custom(width: Int, height: Int) : AdSize(GADAdSize(width, height))
    actual companion object {
        actual fun getCurrentOrientationAnchoredAdaptiveBannerAdSize(width: Int): AdSize {
            return ios.GADCurrentOrientationAnchoredAdaptiveBannerAdSizeWithWidth(width)
        }

        actual fun getCurrentOrientationInlineAdaptiveBannerAdSize(width: Int): AdSize {
            return ios.GADCurrentOrientationInlineAdaptiveBannerAdSizeWithWidth(width)
        }

        actual fun getInlineAdaptiveBannerAdSize(
            width: Int,
            maxHeight: Int
        ): AdSize {
            return ios.GADInlineAdaptiveBannerAdSizeWithWidthAndMaxHeight(width, maxHeight)
        }

        actual fun getLandscapeAnchoredAdaptiveBannerAdSize(width: Int): AdSize {
            return ios.GADLandscapeAnchoredAdaptiveBannerAdSizeWithWidth(width)
        }

        actual fun getLandscapeInlineAdaptiveBannerAdSize(width: Int): AdSize {
            return ios.GADLandscapeInlineAdaptiveBannerAdSizeWithWidth(width)
        }

        actual fun getPortraitAnchoredAdaptiveBannerAdSize(width: Int): AdSize {
            return ios.GADPortraitAnchoredAdaptiveBannerAdSizeWithWidth(width)
        }

        actual fun getPortraitInlineAdaptiveBannerAdSize(width: Int): AdSize {
            return ios.GADPortraitInlineAdaptiveBannerAdSizeWithWidth(width)
        }
    }

    actual val height: Int
        get() = TODO("Not yet implemented")
    actual val width: Int
        get() = TODO("Not yet implemented")
    actual val heightInPixel: Int
        get() = TODO("Not yet implemented")
    actual val widthInPixel: Int
        get() = TODO("Not yet implemented")
    actual val isAutoHeight: Boolean
        get() = TODO("Not yet implemented")
    actual val isFullWidth: Boolean
        get() = TODO("Not yet implemented")
    actual val isFluid: Boolean
        get() = TODO("Not yet implemented")

}