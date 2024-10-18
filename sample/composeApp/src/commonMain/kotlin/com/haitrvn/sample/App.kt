package com.haitrvn.sample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.haitrvn.kam.core.getRootView
import com.haitrvn.kam.core.init.KamInitializer
import com.haitrvn.kam.rememberKamInterstitial
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    KamInitializer().initialize()
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            val kamInterstitialState = rememberKamInterstitial(Config.INTERSTITIAL_AD_UNIT_ID, reloadEnabled = true)
            val rootView = getRootView()
            Button(
                onClick = {
                    showContent = !showContent
                    kamInterstitialState.value?.show(rootView)
                },
                enabled = kamInterstitialState.value != null
            ) {
                Text("Show Interstitial")
            }
        }
    }
}