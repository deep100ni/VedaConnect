package com.DeepSoni.vedaconnect

// This file is located at: app/src/main/java/com/DeepSoni/vedaconnect/MainActivity.kt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.DeepSoni.vedaconnect.feature.welcome.WelcomeScreen
import com.DeepSoni.vedaconnect.ui.theme.VedaConnectTheme // This is your app's theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // VedaConnectTheme is the main theme for your app.
            VedaConnectTheme {
                // We are calling your WelcomeScreen composable here, making it the first thing the user sees.
                WelcomeScreen()
            }
        }
    }
}