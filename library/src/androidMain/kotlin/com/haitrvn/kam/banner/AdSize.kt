package com.haitrvn.kam.banner

import com.google.android.gms.ads.AdSize.BANNER
import com.google.android.gms.ads.AdSize.FLUID
import com.google.android.gms.ads.AdSize.FULL_BANNER
import com.google.android.gms.ads.AdSize.INVALID
import com.google.android.gms.ads.AdSize.LARGE_BANNER
import com.google.android.gms.ads.AdSize.LEADERBOARD
import com.google.android.gms.ads.AdSize.MEDIUM_RECTANGLE
import com.google.android.gms.ads.AdSize.SEARCH
import com.google.android.gms.ads.AdSize.WIDE_SKYSCRAPER
import com.google.android.gms.ads.AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize
import com.google.android.gms.ads.AdSize.getCurrentOrientationInlineAdaptiveBannerAdSize
import com.google.android.gms.ads.AdSize.getLandscapeAnchoredAdaptiveBannerAdSize
import com.google.android.gms.ads.AdSize.getLandscapeInlineAdaptiveBannerAdSize
import com.google.android.gms.ads.AdSize.getPortraitAnchoredAdaptiveBannerAdSize
import com.google.android.gms.ads.AdSize.getPortraitInlineAdaptiveBannerAdSize
import com.haitrvn.kam.util.ContextProvider
import com.google.android.gms.ads.AdSize.getInlineAdaptiveBannerAdSize as getAndroidInlineAdaptiveBannerAdSize

actual open class AdSize(
    val android: com.google.android.gms.ads.AdSize
) {
    actual object Banner : AdSize(BANNER)
    actual object FullBanner : AdSize(FULL_BANNER)
    actual object LargeBanner : AdSize(LARGE_BANNER)
    actual object LeaderBoard : AdSize(LEADERBOARD)
    actual object MediumRectangle : AdSize(MEDIUM_RECTANGLE)
    actual object WideSkyScraper : AdSize(WIDE_SKYSCRAPER)
    actual object Fluid : AdSize(FLUID)
    actual object Invalid : AdSize(INVALID)
    actual object Search : AdSize(SEARCH)
    actual class Custom(width: Int, height: Int) :
        AdSize(com.google.android.gms.ads.AdSize(width, height))

    actual companion object {
        actual fun getCurrentOrientationAnchoredAdaptiveBannerAdSize(width: Int): AdSize {
            return AdSize(
                getCurrentOrientationAnchoredAdaptiveBannerAdSize(
                    ContextProvider.context,
                    width
                )
            )
        }

        actual fun getCurrentOrientationInlineAdaptiveBannerAdSize(width: Int): AdSize {
            return AdSize(
                getCurrentOrientationInlineAdaptiveBannerAdSize(
                    ContextProvider.context,
                    width
                )
            )
        }

        actual fun getInlineAdaptiveBannerAdSize(width: Int, maxHeight: Int): AdSize {
            return AdSize(
                getAndroidInlineAdaptiveBannerAdSize(
                    width,
                    maxHeight
                )
            )
        }

        actual fun getLandscapeAnchoredAdaptiveBannerAdSize(width: Int): AdSize {
            return AdSize(
                getLandscapeAnchoredAdaptiveBannerAdSize(
                    ContextProvider.context,
                    width
                )
            )
        }

        actual fun getLandscapeInlineAdaptiveBannerAdSize(width: Int): AdSize {
            return AdSize(
                getLandscapeInlineAdaptiveBannerAdSize(
                    ContextProvider.context,
                    width
                )
            )
        }

        actual fun getPortraitAnchoredAdaptiveBannerAdSize(width: Int): AdSize {
            return AdSize(
                getPortraitAnchoredAdaptiveBannerAdSize(
                    ContextProvider.context,
                    width
                )
            )
        }

        actual fun getPortraitInlineAdaptiveBannerAdSize(width: Int): AdSize {
            return AdSize(
                getPortraitInlineAdaptiveBannerAdSize(
                    ContextProvider.context,
                    width
                )
            )
        }
    }

    actual val height: Int
        get() = android.height

    actual val width: Int
        get() = android.width

    actual val heightInPixel: Int
        get() = android.getHeightInPixels(ContextProvider.context)

    actual val widthInPixel: Int
        get() = android.getWidthInPixels(ContextProvider.context)

    actual val isAutoHeight: Boolean
        get() = android.isAutoHeight

    actual val isFullWidth: Boolean
        get() = android.isFullWidth

    actual val isFluid: Boolean
        get() = android.isFluid

    override fun equals(other: Any?): Boolean {
        return super.equals(other) && this.android == (other as? AdSize)?.android
    }

    override fun hashCode(): Int {
        return android.hashCode()
    }
}