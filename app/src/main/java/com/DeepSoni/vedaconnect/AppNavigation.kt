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
import com.DeepSoni.vedaconnect.feature.home.HomeScreen
import com.DeepSoni.vedaconnect.feature.streaks.StreakScreen
import com.DeepSoni.vedaconnect.feature.welcome.WelcomeScreen

// A simple data class to make the bottom bar code cleaner
private data class Screen(val route: String, val label: String, val icon: ImageVector)

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // Determine if the bottom bar should be shown based on the current screen
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val shouldShowBottomBar = currentRoute in listOf("home", "streaks", "forum", "quiz", "awareness")

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

            // TODO: Add composables for your other screens here
            // composable("forum") { ForumScreen(navController = navController) }
            // composable("quiz") { QuizScreen(navController = navController) }
            // composable("awareness") { AwarenessScreen(navController = navController) }
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
        Screen("forum", "Forum", Icons.Outlined.Forum),
        Screen("quiz", "Quiz", Icons.Outlined.WorkspacePremium),
        Screen("awareness", "Awareness", Icons.Outlined.Book)
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
                        // Pop up to the start destination of the graph to avoid building up a large
                        // back stack as users select items
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when re-selecting the same item
                        launchSingleTop = true
                        // Restore state when re-selecting a previously selected item
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
