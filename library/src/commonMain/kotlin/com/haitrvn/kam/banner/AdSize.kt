package com.haitrvn.kam.banner

expect open class AdSize {
    object Banner : AdSize
    object FullBanner : AdSize
    object LargeBanner : AdSize
    object LeaderBoard : AdSize
    object MediumRectangle : AdSize
    object WideSkyScraper : AdSize
    object Fluid : AdSize
    object Invalid : AdSize
    internal object Search : AdSize
    class Custom : AdSize

    companion object {
        fun getCurrentOrientationAnchoredAdaptiveBannerAdSize(width: Int): AdSize
        fun getCurrentOrientationInlineAdaptiveBannerAdSize(width: Int): AdSize
        fun getInlineAdaptiveBannerAdSize(
            width: Int,
            maxHeight: Int
        ): AdSize

        fun getLandscapeAnchoredAdaptiveBannerAdSize(width: Int): AdSize
        fun getLandscapeInlineAdaptiveBannerAdSize(width: Int): AdSize
        fun getPortraitAnchoredAdaptiveBannerAdSize(width: Int): AdSize
        fun getPortraitInlineAdaptiveBannerAdSize(width: Int): AdSize
    }

    val height: Int
    val width: Int
    val heightInPixel: Int
    val widthInPixel: Int
    val isAutoHeight: Boolean
    val isFullWidth: Boolean
    val isFluid: Boolean
}