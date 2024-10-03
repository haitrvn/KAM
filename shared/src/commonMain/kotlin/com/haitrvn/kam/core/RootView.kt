package com.haitrvn.kam.core

import androidx.compose.runtime.Composable

expect class RootView

@Composable
expect fun getRootView(): RootView