package com.DeepSoni.vedaconnect

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.DeepSoni.vedaconnect.feature.community.CommunityScreen
import com.DeepSoni.vedaconnect.feature.content.ContentScreen
import com.DeepSoni.vedaconnect.feature.home.HomeScreen
import com.DeepSoni.vedaconnect.feature.notification.NotificationScreen
import com.DeepSoni.vedaconnect.feature.streaks.StreakScreen
import com.DeepSoni.vedaconnect.feature.weeklyquiz.QuizScreen
import com.DeepSoni.vedaconnect.feature.welcome.WelcomeScreen

// A simple data class to make the bottom bar code cleaner
private data class Screen(val route: String, val label: String, val icon: ImageVector)

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // Determine if the bottom bar should be shown based on the current screen
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val shouldShowBottomBar = currentRoute in listOf("home", "streaks", "community", "quiz", "content")

    Scaffold(
        bottomBar = {
            if (shouldShowBottomBar) {
                AppBottomNavigationBar(navController = navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "welcome", // Start on the Welcome screen
            modifier = Modifier.padding(innerPadding)
        ) {
            // Welcome Screen (no bottom bar)
            composable("welcome") {
                WelcomeScreen(navController = navController)
            }

            // Home Screen (with bottom bar)
            composable("home") {
                HomeScreen(navController = navController)
            }

            // Streak Screen (with bottom bar)
            composable("streaks") {
                StreakScreen(navController = navController)
            }

            // Quiz Screen (with bottom bar)
            composable("quiz") {
                QuizScreen(navController = navController)
            }

            // Content Screen (with bottom bar)
            composable("content") {
                ContentScreen(navController = navController)
            }

            // Community Screen (with bottom bar)
            composable("community") {
                CommunityScreen(navController = navController)
            }

            // Notification Screen (no bottom bar)
            composable("notification") {
                NotificationScreen(navController = navController)
            }
        }
    }
}

/**
 * This is the single, shared Bottom Navigation Bar for the entire app.
 */
@Composable
private fun AppBottomNavigationBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val items = listOf(
        Screen("home", "Home", Icons.Outlined.Home),
        Screen("streaks", "Streaks", Icons.Outlined.Whatshot),
        Screen("content", "Content", Icons.Outlined.AutoStories),
        Screen("quiz", "Quiz", Icons.Outlined.WorkspacePremium),
        Screen("community", "Community", Icons.Outlined.Article)
    )

    NavigationBar(
        containerColor = Color.White,
        contentColor = Color(0xFFF57C00)
    ) {
        items.forEach { screen ->
            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(screen.icon, contentDescription = screen.label) },
                label = { Text(screen.label) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFFF57C00),
                    selectedTextColor = Color(0xFFF57C00),
                    indicatorColor = Color(0xFFFFE0B2),
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray
                )
            )
        }
    }
}