package com.DeepSoni.vedaconnect

// ✅ FIX: Added the missing import for MandalaOneSuktasScreen
import android.content.Context
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Article
import androidx.compose.material.icons.outlined.AutoStories
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Whatshot
import androidx.compose.material.icons.outlined.WorkspacePremium
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.DeepSoni.vedaconnect.feature.QuizCompleteScreen
import com.DeepSoni.vedaconnect.feature.community.AwarenessScreen
import com.DeepSoni.vedaconnect.feature.content.ContentScreen
import com.DeepSoni.vedaconnect.feature.content.MantraDetailScreen
import com.DeepSoni.vedaconnect.feature.home.HomeScreen
import com.DeepSoni.vedaconnect.feature.notification.NotificationScreen
import com.DeepSoni.vedaconnect.feature.quiz.QuizStartScreen
import com.DeepSoni.vedaconnect.feature.streak.StreakScreen
import com.DeepSoni.vedaconnect.feature.suktas.MandalaOneSuktasScreen
import com.DeepSoni.vedaconnect.feature.weeklyquiz.QuizScreen
import com.DeepSoni.vedaconnect.feature.welcome.NameRepository
import com.DeepSoni.vedaconnect.feature.welcome.WelcomeScreen
import com.DeepSoni.vedaconnect.repository.MantraRepository
import kotlinx.coroutines.flow.first


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
    object QuizComplete : Screen("quizComplete", "QuizComplete") {
        fun createRoute(score: Int, totalQuestions: Int, totalPoints: Int): String {
            return "quizComplete/$score/$totalQuestions/$totalPoints"
        }
    }

    object Community : Screen("community", "Awareness", Icons.AutoMirrored.Outlined.Article)

    object Notification : Screen("notification")
    //object MantraDetail : Screen("detail/{mantraId}") {
    //  fun createRoute(mantraId: String) = "detail/$mantraId"
    //}
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val nameRepository = remember { NameRepository(context) }
    val savedName: String? by nameRepository.userName.collectAsState(initial = null)
    var isDataLoaded by remember { mutableStateOf(false) }

    LaunchedEffect(savedName) {
        if (savedName != null) {
            isDataLoaded = true
        }
    }
    if (!isDataLoaded) {
        return
    }
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
    val shouldShowBottomBar = bottomBarItems.any { it.route == currentRoute }


    val startDestination =
        if (!savedName.isNullOrBlank()) Screen.Home.route else Screen.Welcome.route
    var isReady by remember { mutableStateOf(false) }



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
            startDestination = startDestination, // Start on the Welcome screen

        ) {
            // Welcome Screen (no bottom bar)
            composable(Screen.Welcome.route) {
                WelcomeScreen(
                    navController = navController,
                    nameRepository = nameRepository
                )
            }

            // Home Screen (with bottom bar)
            composable(Screen.Home.route) {
                HomeScreen(
                    navController = navController,
                    paddingValues = innerPadding,
                    userName = savedName ?: "User"
                )
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

            composable(
                "${Screen.QuizComplete.route}/{correctAnswers}/{totalQuestions}/{totalPoints}",
                arguments = listOf(
                    navArgument("correctAnswers") { type = NavType.IntType },
                    navArgument("totalQuestions") {
                        type = NavType.IntType
                        defaultValue = 0
                    },
                    navArgument("totalPoints") { type = NavType.IntType },

                    )
            ) { backStackEntry ->
                val correctAnswers = backStackEntry.arguments?.getInt("correctAnswers") ?: 0
                val totalPoints = backStackEntry.arguments?.getInt("totalPoints") ?: 0
                val totalQuestions = backStackEntry.arguments?.getInt("totalQuestions") ?: 1

                QuizCompleteScreen(
                    correctAnswers = correctAnswers,
                    totalQuestions = totalQuestions,
                    pointsEarned = totalPoints,
                    totalPoints = totalPoints,
                    navController = navController,
                    leaderboardEntries = emptyList(),
                    onViewFullLeaderboard = {
                        // Navigate back to the main quiz screen
                        navController.navigate(Screen.Quiz.route) {
                            popUpTo(Screen.Quiz.route) { inclusive = true }
                        }
                    }
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