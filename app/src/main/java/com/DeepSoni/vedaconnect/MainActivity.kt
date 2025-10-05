package com.DeepSoni.vedaconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.DeepSoni.vedaconnect.ui.theme.VedaConnectTheme // Use your theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VedaConnectTheme {
                // AppNavigation is now the root of your UI.
                AppNavigation()
            }
        }
    }
}