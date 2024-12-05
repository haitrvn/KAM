package com.haitrvn.kam.banner

import cocoapods.Google_Mobile_Ads_SDK.GADAdSize
import cocoapods.Google_Mobile_Ads_SDK.GADAdSizeBanner
import cocoapods.Google_Mobile_Ads_SDK.GADAdSizeFluid
import cocoapods.Google_Mobile_Ads_SDK.GADAdSizeFromCGSize
import cocoapods.Google_Mobile_Ads_SDK.GADAdSizeFullBanner
import cocoapods.Google_Mobile_Ads_SDK.GADAdSizeInvalid
import cocoapods.Google_Mobile_Ads_SDK.GADAdSizeLargeBanner
import cocoapods.Google_Mobile_Ads_SDK.GADAdSizeLeaderboard
import cocoapods.Google_Mobile_Ads_SDK.GADAdSizeMediumRectangle
import cocoapods.Google_Mobile_Ads_SDK.GADAdSizeSkyscraper
import cocoapods.Google_Mobile_Ads_SDK.GADCurrentOrientationAnchoredAdaptiveBannerAdSizeWithWidth
import cocoapods.Google_Mobile_Ads_SDK.GADCurrentOrientationInlineAdaptiveBannerAdSizeWithWidth
import cocoapods.Google_Mobile_Ads_SDK.GADInlineAdaptiveBannerAdSizeWithWidthAndMaxHeight
import cocoapods.Google_Mobile_Ads_SDK.GADLandscapeAnchoredAdaptiveBannerAdSizeWithWidth
import cocoapods.Google_Mobile_Ads_SDK.GADLandscapeInlineAdaptiveBannerAdSizeWithWidth
import cocoapods.Google_Mobile_Ads_SDK.GADPortraitAnchoredAdaptiveBannerAdSizeWithWidth
import cocoapods.Google_Mobile_Ads_SDK.GADPortraitInlineAdaptiveBannerAdSizeWithWidth
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.useContents
import platform.CoreGraphics.CGSizeMake

@OptIn(ExperimentalForeignApi::class)
actual open class AdSize(
    val ios: GADAdSize
) {
    actual object Banner : AdSize(GADAdSizeBanner)
    actual object FullBanner : AdSize(GADAdSizeFullBanner)
    actual object LargeBanner : AdSize(GADAdSizeLargeBanner)
    actual object LeaderBoard : AdSize(GADAdSizeLeaderboard)
    actual object MediumRectangle : AdSize(GADAdSizeMediumRectangle)
    actual object WideSkyScraper : AdSize(GADAdSizeSkyscraper)
    actual object Fluid : AdSize(GADAdSizeFluid)
    actual object Invalid : AdSize(GADAdSizeInvalid)
    internal actual object Search : AdSize(GADAdSizeFromCGSize(CGSizeMake(250.0, 250.0)).useContents { this })
    actual class Custom(width: Int, height: Int) :
        AdSize(GADAdSizeFromCGSize(CGSizeMake(width.toDouble(), height.toDouble())).useContents { this })

    actual companion object {
        actual fun getCurrentOrientationAnchoredAdaptiveBannerAdSize(width: Int): AdSize {
            return AdSize(GADCurrentOrientationAnchoredAdaptiveBannerAdSizeWithWidth(width.toDouble()).useContents { this })
        }

        actual fun getCurrentOrientationInlineAdaptiveBannerAdSize(width: Int): AdSize {
            return AdSize(GADCurrentOrientationInlineAdaptiveBannerAdSizeWithWidth(width.toDouble()).useContents { this })
        }

        actual fun getInlineAdaptiveBannerAdSize(
            width: Int,
            maxHeight: Int
        ): AdSize {
            return AdSize(
                GADInlineAdaptiveBannerAdSizeWithWidthAndMaxHeight(
                    width.toDouble(),
                    maxHeight.toDouble()
                ).useContents { this })
        }

        actual fun getLandscapeAnchoredAdaptiveBannerAdSize(width: Int): AdSize {
            return AdSize(GADLandscapeAnchoredAdaptiveBannerAdSizeWithWidth(width.toDouble()).useContents { this })
        }

        actual fun getLandscapeInlineAdaptiveBannerAdSize(width: Int): AdSize {
            return AdSize(GADLandscapeInlineAdaptiveBannerAdSizeWithWidth(width.toDouble()).useContents { this })
        }

        actual fun getPortraitAnchoredAdaptiveBannerAdSize(width: Int): AdSize {
            return AdSize(GADPortraitAnchoredAdaptiveBannerAdSizeWithWidth(width.toDouble()).useContents { this })
        }

        actual fun getPortraitInlineAdaptiveBannerAdSize(width: Int): AdSize {
            return AdSize(GADPortraitInlineAdaptiveBannerAdSizeWithWidth(width.toDouble()).useContents { this })
        }
    }

    actual val height: Int
        get() = ios.size.height.toInt()

    actual val width: Int
        get() = ios.size.width.toInt()

    actual val heightInPixel: Int
        get() = ios.size.height.toInt()

    actual val widthInPixel: Int
        get() = ios.size.width.toInt()

    actual val isAutoHeight: Boolean
        get() = height == -2

    actual val isFullWidth: Boolean
        get() = width == -1

    actual val isFluid: Boolean
        get() = width == -3 && height == -4

}