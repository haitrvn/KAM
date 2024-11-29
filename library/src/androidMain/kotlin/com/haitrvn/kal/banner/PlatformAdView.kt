package com.haitrvn.kal.banner

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView

@Composable
actual fun PlatformAdView(
    modifier: Modifier,
    adView: AdView
) {
    AndroidView(
        factory = { adView.android },
        modifier = modifier
    )
}