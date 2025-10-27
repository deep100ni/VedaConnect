package com.DeepSoni.vedaconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.DeepSoni.vedaconnect.ui.theme.VedaConnectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            VedaConnectTheme {
                // AppNavigation is now the root of your UI.
                AppNavigation()
            }
        }
    }
}