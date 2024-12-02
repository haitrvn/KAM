package com.haitrvn.sample

import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController(
    viewModel: SampleViewModel
) = ComposeUIViewController { App(viewModel.state) }