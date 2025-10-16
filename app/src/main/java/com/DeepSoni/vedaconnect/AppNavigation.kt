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
import com.DeepSoni.vedaconnect.feature.community.AwarenessScreen
import com.DeepSoni.vedaconnect.feature.content.ContentScreen
import com.DeepSoni.vedaconnect.feature.content.MantraDetailScreen
import com.DeepSoni.vedaconnect.feature.home.HomeScreen
import com.DeepSoni.vedaconnect.feature.notification.NotificationScreen
import com.DeepSoni.vedaconnect.feature.quiz.QuizCompleteScreen
import com.DeepSoni.vedaconnect.feature.quiz.QuizScreen
import com.DeepSoni.vedaconnect.feature.quiz.QuizStartScreen
import com.DeepSoni.vedaconnect.feature.streak.StreakScreen
import com.DeepSoni.vedaconnect.feature.suktas.MandalaOneSuktasScreen
import com.DeepSoni.vedaconnect.feature.welcome.WelcomeScreen
import com.DeepSoni.vedaconnect.repository.MantraRepository

/**
 * Kelas bersegel untuk mendefinisikan rute navigasi dengan cara yang aman dari tipe.
 * Ini mencegah kesalahan ketik dan memusatkan informasi layar.
 */
sealed class Screen(val route: String, val label: String? = null, val icon: ImageVector? = null) {
    object Welcome : Screen("welcome")
    object Home : Screen("home", "Home", Icons.Outlined.Home)
    object Streaks : Screen("streaks", "Streaks", Icons.Outlined.Whatshot)
    object Content : Screen("content", "Content", Icons.Outlined.AutoStories)
    object MandalaOneSuktas : Screen("mandalaOneSuktas")
    object Suktas : Screen("suktas/{mandalaId}") {
        fun createRoute(mandalaId: Int) = "suktas/$mandalaId"
    }
    object Quiz : Screen("quiz", "Quiz", Icons.Outlined.WorkspacePremium)
    object QuizStart : Screen("quizStart", "QuizStart")
    object QuizComplete : Screen("quizComplete", "QuizComplete")
    object Community : Screen("community", "Awareness", Icons.AutoMirrored.Outlined.Article)
    object Notification : Screen("notification")
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    // Daftar layar yang akan ditampilkan di bilah navigasi bawah.
    val bottomBarItems = listOf(
        Screen.Home,
        Screen.Streaks,
        Screen.Content,
        Screen.Quiz,
        Screen.Community
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    // Tentukan apakah bilah bawah harus ditampilkan berdasarkan rute layar saat ini.
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
            startDestination = Screen.Welcome.route, // Mulai dari layar Selamat Datang
            modifier = Modifier.padding(innerPadding)
        ) {
            // Layar Selamat Datang (tanpa bilah bawah)
            composable(Screen.Welcome.route) {
                WelcomeScreen(navController = navController)
            }

            // Layar Beranda (dengan bilah bawah)
            composable(Screen.Home.route) {
                HomeScreen(navController = navController)
            }

            // Layar Streak (dengan bilah bawah)
            composable(Screen.Streaks.route) {
                StreakScreen(navController = navController)
            }

            // Layar Kuis (dengan bilah bawah)
            composable(Screen.Quiz.route) {
                QuizScreen(navController = navController)
            }
            composable(Screen.QuizStart.route) {
                QuizStartScreen(navController = navController)
            }

            composable(Screen.QuizComplete.route) {
                // Data dummy untuk demonstrasi
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
                        // Arahkan kembali ke layar kuis utama
                        navController.navigate(Screen.Quiz.route) {
                            popUpTo(Screen.Quiz.route) { inclusive = true }
                        }
                    },
                    navController = navController
                )
            }

            // Layar Konten (dengan bilah bawah)
            composable(Screen.Content.route) {
                ContentScreen(navController = navController)
            }

            // Layar Mandala One Suktas (tanpa bilah bawah)
            composable(Screen.Suktas.route) { backStackEntry ->
                // Extract the mandalaId from the route. Default to 1 if it's missing.
                val mandalaId = backStackEntry.arguments?.getString("mandalaId")?.toIntOrNull() ?: 1
                MandalaOneSuktasScreen(
                    navController = navController,
                    mandalaId = mandalaId // Pass the ID to the screen
                )
            }


            // Layar Komunitas (dengan bilah bawah)
            composable(Screen.Community.route) {
                AwarenessScreen(navController = navController)
            }

            // Layar Notifikasi (tanpa bilah bawah)
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
 * Ini adalah satu-satunya Bilah Navigasi Bawah bersama untuk seluruh aplikasi.
 * Ini tanpa status dan menerima semua datanya sebagai parameter.
 */
@Composable
private fun AppBottomNavigationBar(
    navController: NavController,
    items: List<Screen>,
    currentRoute: String?
) {
    NavigationBar(
        containerColor = Color.White,
        contentColor = Color(0xFFF57C00) // Warna oranye untuk item yang dipilih
    ) {
        items.forEach { screen ->
            require(screen.icon != null && screen.label != null) {
                "Layar di bilah navigasi bawah harus memiliki ikon dan label."
            }

            NavigationBarItem(
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Hindari beberapa salinan tujuan yang sama saat memilih kembali item yang sama
                        launchSingleTop = true
                        // Pulihkan status saat memilih kembali item yang dipilih sebelumnya
                        restoreState = true
                    }
                },
                icon = { Icon(screen.icon, contentDescription = screen.label) },
                label = { Text(screen.label) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFFF57C00),
                    selectedTextColor = Color(0xFFF57C00),
                    indicatorColor = Color(0xFFFFE0B2), // Oranye muda untuk indikator pilihan
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray
                )
            )
        }
    }
}