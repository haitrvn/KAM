package com.haitrvn.kam

import androidx.compose.runtime.Composable
import androidx.compose.ui.interop.LocalUIViewController
import platform.UIKit.UIViewController

actual typealias RootView = UIViewController

@Composable
actual fun getRootView(): RootView = LocalUIViewController.current