package com.DeepSoni.vedaconnect

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Article
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.DeepSoni.vedaconnect.data.LeaderboardEntry
import com.DeepSoni.vedaconnect.data.Medal
import com.DeepSoni.vedaconnect.data.QuizResult
import com.DeepSoni.vedaconnect.repository.MantraRepository
import com.DeepSoni.vedaconnect.feature.quiz.QuizCompleteScreen
import com.DeepSoni.vedaconnect.feature.community.AwarenessScreen
import com.DeepSoni.vedaconnect.feature.home.HomeScreen
import com.DeepSoni.vedaconnect.feature.notification.NotificationScreen
import com.DeepSoni.vedaconnect.feature.streak.StreakScreen
import com.DeepSoni.vedaconnect.feature.quiz.QuizScreen
import com.DeepSoni.vedaconnect.feature.welcome.WelcomeScreen
import com.DeepSoni.vedaconnect.feature.content.ContentScreen
// ✅ FIX: Added the missing import for MandalaOneSuktasScreen
import com.DeepSoni.vedaconnect.feature.suktas.MandalaOneSuktasScreen
import com.DeepSoni.vedaconnect.feature.content.MantraDetailScreen
import com.DeepSoni.vedaconnect.feature.quiz.QuizStartScreen


/**
 * A sealed class to define the navigation routes in a type-safe way.
 * This prevents typos and centralizes screen information.
 */
sealed class Screen(val route: String, val label: String? = null, val icon: ImageVector? = null) {
    object Welcome : Screen("welcome")
    object Home : Screen("home", "Home", Icons.Outlined.Home)
    object Streaks : Screen("streaks", "Streaks", Icons.Outlined.Whatshot)
    object Content : Screen("content", "Content", Icons.Outlined.AutoStories)
    object MandalaOneSuktas : Screen("mandalaOneSuktas")

    object Quiz : Screen("quiz", "Quiz", Icons.Outlined.WorkspacePremium)
    object QuizStart : Screen("quizStart", "QuizStart")
    object QuizComplete : Screen("quizComplete", "QuizComplete")

    object Community : Screen("community", "Awareness", Icons.AutoMirrored.Outlined.Article)

    object Notification : Screen("notification")
    //object MantraDetail : Screen("detail/{mantraId}") {
      //  fun createRoute(mantraId: String) = "detail/$mantraId"
    //}
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // List of screens that will be displayed in the bottom navigation bar.
    val bottomBarItems = listOf(
        Screen.Home,
        Screen.Streaks,
        Screen.Content,
        Screen.Quiz,
        Screen.Community
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Determine if the bottom bar should be shown based on the current screen's route.
    val shouldShowBottomBar = bottomBarItems.any { it.route == currentRoute }

    Scaffold(
        bottomBar = {
            if (shouldShowBottomBar) {
                AppBottomNavigationBar(
                    navController = navController,
                    items = bottomBarItems,
                    currentRoute = currentRoute
                )
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Welcome.route, // Start on the Welcome screen
            modifier = Modifier.padding(innerPadding)
        ) {
            // Welcome Screen (no bottom bar)
            composable(Screen.Welcome.route) {
                WelcomeScreen(navController = navController)
            }

            // Home Screen (with bottom bar)
            composable(Screen.Home.route) {
                HomeScreen(navController = navController)
            }

            // Streak Screen (with bottom bar)
            composable(Screen.Streaks.route) {
                StreakScreen(navController = navController)
            }

            // Quiz Screen (with bottom bar)
            composable(Screen.Quiz.route) {
                QuizScreen(navController = navController)
            }
            composable(Screen.QuizStart.route) {
                QuizStartScreen(navController = navController)
            }

            composable(Screen.QuizComplete.route) {
                // Dummy data for demonstration
                val dummyQuizResult = QuizResult(
                    correctAnswers = 7,
                    totalQuestions = 10,
                    pointsEarned = 70,
                    totalScore = 850
                )
                val dummyLeaderboard = listOf(
                    LeaderboardEntry("Priya K.", 1, 950, medal = Medal.GOLD),
                    LeaderboardEntry("Rahul M.", 2, 920, medal = Medal.SILVER),
                    LeaderboardEntry("Ananya S.", 3, 890, medal = Medal.BRONZE),
                    LeaderboardEntry("You", 4, 850, isCurrentUser = true),
                    LeaderboardEntry("Dr. Sharma", 5, 820)
                )

                QuizCompleteScreen(
                    quizResult = dummyQuizResult,
                    leaderboardEntries = dummyLeaderboard,
                    onViewFullLeaderboard = {
                        // Navigate back to the main quiz screen
                        navController.navigate(Screen.Quiz.route) {
                            popUpTo(Screen.Quiz.route) { inclusive = true }
                        }
                    },
                    navController = navController
                )
            }

            // Content Screen (with bottom bar)
            composable(Screen.Content.route) {
                ContentScreen(navController = navController)
            }

            // Mandala One Suktas Screen (no bottom bar)
            composable(Screen.MandalaOneSuktas.route) {
                MandalaOneSuktasScreen(navController = navController)
            }

            // Community Screen (with bottom bar)
            composable(Screen.Community.route) {
                AwarenessScreen(navController = navController)
            }

            // Notification Screen (no bottom bar)
            composable(Screen.Notification.route) {
                NotificationScreen(navController = navController)
            }

            composable("detail/{mantraId}") { backStackEntry ->
                val mantraId = backStackEntry.arguments?.getString("mantraId")
                val mantra = MantraRepository.mantras.find { it.id == mantraId }
                mantra?.let {
                    MantraDetailScreen(navController = navController, mantra = it)
                }
            }

        }
    }
}

/**
 * This is the single, shared Bottom Navigation Bar for the entire app.
 * It is stateless and receives all its data as parameters.
 */
@Composable
private fun AppBottomNavigationBar(
    navController: NavController,
    items: List<Screen>,
    currentRoute: String?
) {
    NavigationBar(
        containerColor = Color.White,
        contentColor = Color(0xFFF57C00) // Orange color for selected items
    ) {
        items.forEach { screen ->
            require(screen.icon != null && screen.label != null) {
                "Screens in the bottom navigation bar must have an icon and a label."
            }

            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
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
                    indicatorColor = Color(0xFFFFE0B2), // Light orange for the selection indicator
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray
                )
            )
        }
    }
}