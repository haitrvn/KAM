package com.haitrvn.sample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
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
    interstitialState: State<InterstitialContract>
) {
    val rootView = getRootView()
    Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            enabled = interstitialState.value is InterstitialContract.Loaded && (interstitialState.value as InterstitialContract.Loaded).interstitial != null,
            onClick = {
                (interstitialState.value as? InterstitialContract.Loaded)?.interstitial?.show(rootView)
            }
        ) {
            Text("Load and Show Interstitial Ad")
        }
        Button(
            enabled = interstitialState.value is InterstitialContract.Loaded && (interstitialState.value as InterstitialContract.Loaded).appOpen != null,
            onClick = {
                (interstitialState.value as? InterstitialContract.Loaded)?.appOpen?.show(rootView)
            }
        ) {
            Text("Load and Show AppOpen Ad")
        }
    }
}