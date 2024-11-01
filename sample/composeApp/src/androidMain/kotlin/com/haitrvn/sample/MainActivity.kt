package com.haitrvn.sample

import android.os.Bundle
import android.provider.MediaStore
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.ui.graphics.asImageBitmap

class MainActivity : ComponentActivity() {
    private val imagePickerLauncher = registerForActivityResult(
        ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, it)
            viewModel.startGame(bitmap.asImageBitmap())
        }
    }

    private val viewModel = PuzzleViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NumberPuzzleGame(viewModel) {
                imagePickerLauncher.launch("image/*")
            }
            viewModel.startGame()
        }
    }
}