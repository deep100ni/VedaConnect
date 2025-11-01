package com.DeepSoni.vedaconnect

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Article
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
import com.DeepSoni.vedaconnect.feature.home.HomeScreen
import com.DeepSoni.vedaconnect.feature.mandalas.MandalaListScreen
import com.DeepSoni.vedaconnect.feature.notification.NotificationScreen
import com.DeepSoni.vedaconnect.feature.quiz.QuizStartScreen
import com.DeepSoni.vedaconnect.feature.streak.StreakScreen
import com.DeepSoni.vedaconnect.feature.suktas.SuktaDetailScreen
import com.DeepSoni.vedaconnect.feature.suktas.SuktasScreen
import com.DeepSoni.vedaconnect.feature.weeklyquiz.QuizScreen
import com.DeepSoni.vedaconnect.feature.welcome.NameRepository
import com.DeepSoni.vedaconnect.feature.welcome.WelcomeScreen
import com.DeepSoni.vedaconnect.repository.RigvedaRepository


sealed class Screen(val route: String, val label: String? = null, val icon: ImageVector? = null) {
    object Welcome : Screen("welcome")
    object Home : Screen("home", "Home", Icons.Outlined.Home)
    object Streaks : Screen("streaks", "Streaks", Icons.Outlined.Whatshot)
    object Content : Screen("content", "Content", Icons.Outlined.AutoStories)
    object Quiz : Screen("quiz", "Quiz", Icons.Outlined.WorkspacePremium)
    object QuizStart : Screen("quizStart", "QuizStart")
    object QuizComplete : Screen("quizComplete", "QuizComplete") {
        fun createRoute(score: Int, totalQuestions: Int, totalPoints: Int): String {
            return "quizComplete/$score/$totalQuestions/$totalPoints"
        }
    }
    object Community : Screen("community", "Awareness", Icons.AutoMirrored.Outlined.Article)
    object Notification : Screen("notification")
    object MandalaList : Screen("mandala_list")
    object Suktas : Screen("suktas/{mandalaNumber}") {
        fun createRoute(mandalaNumber: Int) = "suktas/$mandalaNumber"
    }
    object SuktaDetail : Screen("sukta_detail/{suktaId}") {
        fun createRoute(suktaId: String) = "sukta_detail/$suktaId"
    }
}

@Composable
fun AppNavigation() {
    // 1. Set up repository and collect the saved name state.
    // `initial = null` means we are waiting for the value to be loaded.
    val context = LocalContext.current.applicationContext
    val nameRepository = remember { NameRepository(context) }
    val savedName by nameRepository.userName.collectAsState(initial = null)

    // 2. Display a loading screen while `savedName` is null (i.e., being loaded).
    if (savedName == null) {
        Surface(modifier = Modifier.fillMaxSize()) {
            Box(contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }
        return // Prevents the rest of the UI from composing until the name is loaded.
    }

    // 3. Once loaded, determine the starting screen.
    val navController = rememberNavController()
    val startDestination = if (savedName!!.isBlank()) Screen.Welcome.route else Screen.Home.route

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
        // 4. Set up the NavHost with the determined start destination.
        NavHost(
            navController = navController,
            startDestination = startDestination,
        ) {
            composable(Screen.Welcome.route) {
                WelcomeScreen(
                    navController = navController,
                    nameRepository = nameRepository
                )
            }

            composable(Screen.Home.route) {
                HomeScreen(
                    navController = navController,
                    paddingValues = innerPadding,
                    // Non-null assertion is safe here because of the loading check above.
                    userName = savedName!!.ifBlank { "User" }
                )
            }

            composable(Screen.Streaks.route) {
                StreakScreen(
                    navController = navController,
                    paddingValues = innerPadding) }
            composable(Screen.Content.route) { ContentScreen(navController = navController) }
            composable(Screen.Quiz.route) { QuizScreen(navController = navController) }
            composable(Screen.QuizStart.route) { QuizStartScreen(navController = navController) }

            composable(
                route = "${Screen.QuizComplete.route}/{correctAnswers}/{totalQuestions}/{totalPoints}",
                arguments = listOf(
                    navArgument("correctAnswers") { type = NavType.IntType },
                    navArgument("totalQuestions") { type = NavType.IntType; defaultValue = 0 },
                    navArgument("totalPoints") { type = NavType.IntType }
                )
            ) { backstackEntry ->
                val correctAnswers = backstackEntry.arguments?.getInt("correctAnswers") ?: 0
                val totalQuestions = backstackEntry.arguments?.getInt("totalQuestions") ?: 1
                val totalPoints = backstackEntry.arguments?.getInt("totalPoints") ?: 0
                QuizCompleteScreen(
                    correctAnswers = correctAnswers,
                    totalQuestions = totalQuestions,
                    totalPoints = totalPoints,
                    pointsEarned = totalPoints,
                    leaderboardEntries = emptyList(),
                    onViewFullLeaderboard = {
                        navController.navigate(Screen.Quiz.route) {
                            popUpTo(Screen.Quiz.route) { inclusive = true }
                        }
                    },
                    navController = navController
                )
            }
            composable(Screen.Community.route) { AwarenessScreen(navController = navController) }
            composable(Screen.Notification.route) { NotificationScreen(navController = navController) }

            composable(Screen.MandalaList.route) {
                MandalaListScreen(navController = navController)
            }

            composable(
                route = Screen.Suktas.route,
                arguments = listOf(navArgument("mandalaNumber") { type = NavType.IntType })
            ) { backStackEntry ->
                val mandalaNumber = backStackEntry.arguments?.getInt("mandalaNumber")
                requireNotNull(mandalaNumber) { "Mandala number is required as an argument." }
                SuktasScreen(navController = navController, mandalaNumber = mandalaNumber)
            }

            composable(
                route = Screen.SuktaDetail.route,
                arguments = listOf(navArgument("suktaId") { type = NavType.StringType })
            ) { backStackEntry ->
                val suktaId = backStackEntry.arguments?.getString("suktaId")
                val sukta = suktaId?.let { RigvedaRepository.getSuktaById(it) }

                if (sukta != null) {
                    SuktaDetailScreen(navController = navController, sukta = sukta)
                } else {
                    // Handle case where sukta is not found, e.g., show an error message.
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