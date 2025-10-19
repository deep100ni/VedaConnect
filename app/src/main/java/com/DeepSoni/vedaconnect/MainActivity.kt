package com.DeepSoni.vedaconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.DeepSoni.vedaconnect.repository.MandalaOneSuktasRepository
import com.DeepSoni.vedaconnect.ui.theme.VedaConnectTheme // Use your theme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // *** FIX: Initialize the repository on a background thread ***
        // This is the crucial step. It loads the JSON data into memory
        // before any screen that needs it is displayed.
        lifecycleScope.launch(Dispatchers.IO) {
            MandalaOneSuktasRepository.initialize(applicationContext)
        }

        setContent {
            VedaConnectTheme {
                // AppNavigation is now the root of your UI.
                AppNavigation()
            }
        }
    }
}