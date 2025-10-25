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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.DeepSoni.vedaconnect.data.LeaderboardEntry
import com.DeepSoni.vedaconnect.data.Medal
import com.DeepSoni.vedaconnect.data.QuizResult
import com.DeepSoni.vedaconnect.repository.MantraRepository
import com.DeepSoni.vedaconnect.repository.MandalaOneSuktasRepository
import com.DeepSoni.vedaconnect.feature.quiz.QuizCompleteScreen
import com.DeepSoni.vedaconnect.feature.community.AwarenessScreen
import com.DeepSoni.vedaconnect.feature.home.HomeScreen
import com.DeepSoni.vedaconnect.feature.notification.NotificationScreen
import com.DeepSoni.vedaconnect.feature.streak.StreakScreen
import com.DeepSoni.vedaconnect.feature.quiz.QuizScreen
import com.DeepSoni.vedaconnect.feature.welcome.WelcomeScreen
import com.DeepSoni.vedaconnect.feature.content.ContentScreen
import com.DeepSoni.vedaconnect.feature.suktas.MandalaOneSuktasScreen
import com.DeepSoni.vedaconnect.feature.content.MantraDetailScreen
import com.DeepSoni.vedaconnect.feature.quiz.QuizStartScreen
import com.DeepSoni.vedaconnect.feature.suktas.SuktaDetailScreen


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

    object MantraDetail : Screen("detail/{mantraId}") {
        fun createRoute(mantraId: String) = "detail/$mantraId"
    }

    object SuktaDetail : Screen("sukta_detail/{suktaId}") {
        fun createRoute(suktaId: String) = "sukta_detail/$suktaId"
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    val bottomBarItems = listOf(
        Screen.Home,
        Screen.Streaks,
        Screen.Content,
        Screen.Quiz,
        Screen.Community
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

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
            // ... (Other composables like Welcome, Home, Streaks, etc., remain the same)
            composable(Screen.Welcome.route) { WelcomeScreen(navController = navController) }
            composable(Screen.Home.route) { HomeScreen(navController = navController) }
            composable(Screen.Streaks.route) { StreakScreen(navController = navController) }
            composable(Screen.Quiz.route) { QuizScreen(navController = navController) }
            composable(Screen.QuizStart.route) { QuizStartScreen(navController = navController) }
            composable(Screen.QuizComplete.route) {
                val dummyQuizResult = QuizResult(7, 10, 70, 850)
                val dummyLeaderboard = listOf(
                    LeaderboardEntry("Priya K.", 1, 950, medal = Medal.GOLD),
                    LeaderboardEntry("You", 4, 850, isCurrentUser = true)
                )
                QuizCompleteScreen(
                    quizResult = dummyQuizResult,
                    leaderboardEntries = dummyLeaderboard,
                    onViewFullLeaderboard = { navController.navigate(Screen.Quiz.route) { popUpTo(Screen.Quiz.route) { inclusive = true } } },
                    navController = navController
                )
            }
            composable(Screen.Content.route) { ContentScreen(navController = navController) }
            composable(Screen.MandalaOneSuktas.route) { MandalaOneSuktasScreen(navController = navController) }
            composable(Screen.Community.route) { AwarenessScreen(navController = navController) }
            composable(Screen.Notification.route) { NotificationScreen(navController = navController) }


            // --- Detail Screens ---

            composable(Screen.MantraDetail.route) { backStackEntry ->
                val mantraId = backStackEntry.arguments?.getString("mantraId")
                val mantra = MantraRepository.mantras.find { it.id == mantraId }
                mantra?.let {
                    MantraDetailScreen(navController = navController, mantra = it)
                }
            }

            // <<< NEW: Composable for the Sukta Detail Screen
            composable(
                route = Screen.SuktaDetail.route,
                arguments = listOf(navArgument("suktaId") { type = NavType.StringType })
            ) { backStackEntry ->
                // Retrieve the suktaId from the navigation arguments
                val suktaId = backStackEntry.arguments?.getString("suktaId")

                // Find the sukta using the helper function in your repository
                val sukta = suktaId?.let { MandalaOneSuktasRepository.getSuktaById(it) }

                // If the sukta is found, display the detail screen
                if (sukta != null) {
                    SuktaDetailScreen(navController = navController, sukta = sukta)
                } else {
                    // Optional: You can show a loading indicator or an error message here
                    // if the sukta could not be found.
                }
            }
        }
    }
}

@Composable
private fun AppBottomNavigationBar(
    navController: NavController,
    items: List<Screen>,
    currentRoute: String?
) {
    NavigationBar(
        containerColor = Color.White,
        contentColor = Color(0xFFF57C00)
    ) {
        items.forEach { screen ->
            require(screen.icon != null && screen.label != null) {
                "Screens in the bottom navigation bar must have an icon and a label."
            }

            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) { saveState = true }
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