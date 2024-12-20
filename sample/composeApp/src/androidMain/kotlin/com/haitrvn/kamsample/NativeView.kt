package com.haitrvn.kamsample

import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import com.haitrvn.kam.native.AdChoicesView
import com.haitrvn.kam.native.MediaView
import com.haitrvn.kam.native.NativeAdView
import com.haitrvn.kam.native.NativeAdViewBinding
import com.haitrvn.kam.native.View
import com.haitrvn.kamsample.databinding.GntMediumTemplateViewBinding

class NativeView(context: Context): NativeAdViewBinding {
    private val binding: GntMediumTemplateViewBinding by lazy {
        val layoutInflater = LayoutInflater.from(context)
        GntMediumTemplateViewBinding.inflate(layoutInflater)
    }
    override val rootView: NativeAdView
        get() = NativeAdView(binding.root)
    override val adChoicesView: AdChoicesView?
        get() = null
    override val advertiserView: View?
        get() = null
    override val bodyView: TextView
        get() = binding.body
    override val callToActionView: View?
        get() = null
    override val clickConfirmingView: Button
        get() = binding.cta
    override val headlineView: LinearLayout
        get() = binding.headline
    override val iconView: ImageView
        get() = binding.icon
    override val imageView: View?
        get() = null
    override val mediaView: MediaView
        get() = binding.mediaView
    override val priceView: View?
        get() = null
    override val starRatingView: RatingBar
        get() = binding.ratingBar
    override val storeView: View?
        get() = null
}