package com.DeepSoni.vedaconnect // Changed to lowercase 'deepsoni'

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.DeepSoni.vedaconnect.feature.home.HomeScreen
import com.DeepSoni.vedaconnect.feature.welcome.WelcomeScreen
import com.DeepSoni.vedaconnect.ui.theme.VedaConnectTheme
import com.DeepSoni.vedaconnect.feature.notification.NotificationScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            VedaConnectTheme {
                AppNavigation()
            }
        }
    }
}

// =================================================================================
// Main Navigation Controller using Jetpack Compose Navigation
// =================================================================================

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "welcome_screen") {
        composable("welcome_screen") {
            WelcomeScreen(onNavigateToHome = {
                // Navigate to the home screen, and pop the welcome screen off the back stack
                // so the user can't go back to it with the back button.
                navController.navigate("home_screen") {
                    popUpTo("welcome_screen") {
                        inclusive = true
                    }
                }
            })
        }
        composable("home_screen") {
            HomeScreen(navController = navController)
        }
        composable("notification_screen") {

             NotificationScreen(navController = navController)

        }

    }
}

