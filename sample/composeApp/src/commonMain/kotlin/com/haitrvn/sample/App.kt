package com.haitrvn.sample

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.haitrvn.kam.AdRequest
import com.haitrvn.kam.Initializer
import com.haitrvn.kam.getRootView
import com.haitrvn.kam.interstitial.Interstitial
import kotlinx.coroutines.launch
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    Initializer().initialize {
        println("haitrvn initialize $it")
    }
    MaterialTheme {
        val coroutineScope = rememberCoroutineScope()
        val interstitialState = remember { mutableStateOf<Interstitial?>(null) }
        val rootView = getRootView()
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(
                onClick = {
                    coroutineScope.launch {
                        val interstitial = Interstitial.load("ca-app-pub-3940256099942544/1033173712", AdRequest.createInstance {  })
                        interstitialState.value = interstitial
                        interstitial?.show(rootView)
                    }
                }
            ) {
                Text("Load and Show Interstitial Ad")
            }
        }
    }
}