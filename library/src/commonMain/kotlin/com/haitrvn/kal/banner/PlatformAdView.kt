package com.haitrvn.kal.banner

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun PlatformAdView(
    modifier: Modifier,
    adView: AdView
)