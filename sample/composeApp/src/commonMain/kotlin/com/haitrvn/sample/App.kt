package com.haitrvn.sample

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.haitrvn.kam.getRootView
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App(
    uiState: State<UiState>
) {
    val rootView = getRootView()
    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            enabled = !uiState.value.isInterstitialLoading,
            onClick = {
                uiState.value.interstitial?.show(rootView)
            }
        ) {
            Text("Load and Show Interstitial Ad")
        }
        Button(
            enabled = !uiState.value.isAppOpenLoading,
            onClick = {
                uiState.value.appOpen?.show(rootView)
            }
        ) {
            Text("Load and Show AppOpen Ad")
        }
        Button(
            enabled = !uiState.value.isRewardedLoading,
            onClick = {
                uiState.value.rewarded?.show(rootView) {}
            }
        ) {
            Text("Load and Show Rewarded Ad")
        }
    }
}