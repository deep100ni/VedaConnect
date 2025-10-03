package com.DeepSoni.vedaconnect

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.DeepSoni.vedaconnect.feature.home.HomeScreen
import com.DeepSoni.vedaconnect.feature.welcome.WelcomeScreen
import com.DeepSoni.vedaconnect.ui.theme.VedaConnectTheme // Change this to your actual theme name

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            // It's good practice to wrap your app in your theme composable
            VedaConnectTheme {
                // Call your navigation controller here. This will be the root of your UI.
                AppNavigation()
            }
        }
    }
}

// =================================================================================
// Main Navigation Controller (You can place this here or in its own file)
// =================================================================================

@Composable
fun AppNavigation() {
    // State to decide which screen to show. Initially, we show the WelcomeScreen.
    var showHomeScreen by remember { mutableStateOf(false) }

    if (showHomeScreen) {
        HomeScreen()
    } else {
        // Pass a lambda to WelcomeScreen. When it's called, we update the state.
        WelcomeScreen(onNavigateToHome = { showHomeScreen = true })
    }
}

// NOTE: You would keep your WelcomeScreen() and HomeScreen() composables in their own
// separate files (e.g., WelcomeScreen.kt, HomeScreen.kt) as you had before.
// The code above just shows where to CALL the AppNavigation composable.