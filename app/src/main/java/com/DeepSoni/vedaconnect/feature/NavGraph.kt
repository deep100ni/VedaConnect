package com.DeepSoni.vedaconnect.feature

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.DeepSoni.vedaconnect.feature.home.HomeScreen
import com.DeepSoni.vedaconnect.feature.weeklyquiz.QuizScreen
import com.DeepSoni.vedaconnect.feature.weeklyquiz.QuizStartScreen
import com.DeepSoni.vedaconnect.feature.welcome.WelcomeScreen

@Composable
fun NavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        startDestination = "welcome_screen"
    ) {

        composable("welcome_screen") {
            WelcomeScreen {
                navController.navigate("home_screen")
            }
        }

        composable("home_screen") {
            HomeScreen(navController = navController)
        }

        composable("weekly_quiz_screen") {
            QuizScreen(navController = navController)
        }
        composable("quiz_start_screen") {
            QuizStartScreen(navController = navController)
        }

    }
}

// test2