package com.haitrvn.kam

import androidx.compose.runtime.Composable

expect class RootView

@Composable
expect fun getRootView(): RootView