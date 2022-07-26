package com.example.compose_talkback

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var visible by remember { mutableStateOf(true) }

            LaunchedEffect(Unit) {
                delay(3000)
                visible = false
            }

            // Comment out this and it doesn't crash
            AndroidView(factory = { View(it) })

            AnimatedVisibility(
                visible = visible,
                // Use default transitions (comment out enter and exit) and it doesn't crash
                enter = fadeIn(tween(durationMillis = 500)),
                exit = fadeOut(tween(durationMillis = 500)),
                modifier = Modifier.fillMaxSize()
            ) {
                // Comment out either of these and it doesn't crash
                AndroidView(factory = { TextView(it).apply { text = "Hello" } })
                Text(text = "World")
            }
        }
    }
}
