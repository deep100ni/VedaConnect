package com.DeepSoni.vedaconnect.feature.home // Changed to lowercase 'deepsoni'

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Article // Use AutoMirrored version
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.Forum
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Whatshot
import androidx.compose.material.icons.outlined.WorkspacePremium
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController // Import for Preview
import com.DeepSoni.vedaconnect.R // Make sure to import your project's R file
// import com.DeepSoni.vedaconnect.feature.notifications.NotificationScreen // This import is not directly used in the current file, can be removed if not needed for navigation directly here.

// Main Composable for the Home Screen
@Composable
fun HomeScreen(navController: NavController) { // Accept NavController
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) },   // Modified: Pass navController
        containerColor = Color(0xFFFFF7F0) // Background color for the screen content
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState()) // Makes the column scrollable
        ) {
            HomeHeader(navController = navController) // Pass navController
            Spacer(modifier = Modifier.height(24.dp))
            Column(modifier = Modifier.padding(horizontal = 16.dp)) {
                DailyDharmaDropCard()
                Spacer(modifier = Modifier.height(24.dp))
                SadhanaStreakCard()
                Spacer(modifier = Modifier.height(24.dp))
                QuickActions()
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

// 1. Top Header Section
@Composable
fun HomeHeader(navController: NavController) { // Accept NavController
    val headerOrangeGradient = Brush.verticalGradient(
        colors = listOf(Color(0xFFF57C00), Color(0xFFFB8C00), Color(0xFFFF9800))
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp)
            .background(headerOrangeGradient, shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
            .padding(horizontal = 24.dp, vertical = 32.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            Text(
                text = "Namaste 🙏",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Welcome back to your spiritual journey",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.White.copy(alpha = 0.9f)
            )
        }
        Icon(
            imageVector = Icons.Default.Notifications,
            contentDescription = "Notifications",
            tint = Color.White,
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(28.dp)
                .clickable {
                    navController.navigate("notification_screen") // Navigate to the notification screen
                }
        )
    }
}

// 2. Daily Dharma Drop Card
@Composable
fun DailyDharmaDropCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Daily Dharma Drop",
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = "Today's wisdom awaits",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color.Gray
                    )
                }
                Image(
                    painter = painterResource(id = R.drawable.ic_om_symbol),
                    contentDescription = "Dharma Icon",
                    modifier = Modifier
                        .size(40.dp)
                        .background(Color(0xFFE8E0F8), CircleShape) // This is your purple background
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            // Inner quote card
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF7F0))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Rigveda 1.89.1",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "\"May we see a hundred autumns, may we live a hundred autumns...\"",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black.copy(alpha = 0.7f)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))

            // Play Audio Button
            Button(
                onClick = { /* TODO: Implement audio playback */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF57C00))
            ) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = "Play Audio",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Play Audio (1:30)", color = Color.White)
            }
        }
    }
}

// 3. Sadhana Streak Card
@Composable
fun SadhanaStreakCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF57C00)),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(horizontal = 24.dp, vertical = 20.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = "Sadhana Streak",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(4.dp))
                Row(verticalAlignment = Alignment.Bottom) {
                    Text(
                        text = "12",
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        lineHeight = 48.sp
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "days",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White.copy(alpha = 0.9f),
                        modifier = Modifier.padding(bottom = 6.dp)
                    )
                }
                Text(
                    text = "Keep going! You're doing great 🚀",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.9f)
                )
            }
            Icon(
                imageVector = Icons.Outlined.Whatshot, // Using Whatshot for the flame icon
                contentDescription = "Streak Flame",
                tint = Color.White,
                modifier = Modifier.size(70.dp)
            )
        }
    }
}

// 4. Quick Actions Section
@Composable
fun QuickActions() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Quick Actions",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            QuickActionItem(icon = Icons.Outlined.WorkspacePremium, text = "Weekly Quiz", modifier = Modifier.weight(1f))
            QuickActionItem(icon = Icons.Outlined.Forum, text = "Forum", modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            QuickActionItem(icon = Icons.AutoMirrored.Outlined.Article, text = "Articles", modifier = Modifier.weight(1f)) // Use AutoMirrored
            QuickActionItem(
                icon = Icons.Outlined.Whatshot, // Changed to Whatshot icon for My Streaks
                text = "My Streaks",
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun QuickActionItem(icon: ImageVector, text: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.clickable { /* TODO */ },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(vertical = 20.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = text,
                tint = Color(0xFFF57C00),
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        }
    }
}

// 5. Bottom Navigation Bar
@Composable
fun BottomNavigationBar(navController: NavController) {  // Modified to accept NavController
    NavigationBar(
        containerColor = Color.White,
        contentColor = Color(0xFFF57C00)
    ) {
        NavigationBarItem(
            selected = true,
            onClick = { /* TODO */ },
            icon = { Icon(Icons.Outlined.Home, contentDescription = "Home") },
            label = { Text("Home") },
            colors = NavigationBarItemDefaults.colors(
                selectedIconColor = Color(0xFFF57C00),
                selectedTextColor = Color(0xFFF57C00),
                indicatorColor = Color(0xFFFFE0B2)
            )
        )
        NavigationBarItem(
            selected = false,
            onClick = { /* TODO */ },
            icon = { Icon(Icons.Outlined.Whatshot, contentDescription = "Streaks", modifier = Modifier.size(24.dp)) }, // Changed to Whatshot icon for bottom nav Streaks
            label = { Text("Streaks") },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )
        NavigationBarItem(
            selected = false,
            onClick = { navController.navigate("mantra_list_screen") // Navigating to the DashBoard Screen
            },   // Navigating to the DashBoard Screen
            icon = { Icon(Icons.Outlined.Forum, contentDescription = "DashBoard") },
            label = { Text("DashBoard") },   // Text for the Forum to DashBoard
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )
        NavigationBarItem(
            selected = false,
            onClick = { /* TODO */ },
            icon = { Icon(Icons.Outlined.WorkspacePremium, contentDescription = "Quiz") },
            label = { Text("Quiz") },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )
        NavigationBarItem(
            selected = false,
            onClick = { /* TODO */ },
            icon = { Icon(Icons.Outlined.Book, contentDescription = "Awareness") },
            label = { Text("Awareness") },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = Color.Gray,
                unselectedTextColor = Color.Gray
            )
        )
    }
}

@Preview(showBackground = true, device = "id:pixel_6")
@Composable
fun HomeScreenPreview() {
    // For preview, provide a mock NavController
    val navController = rememberNavController()
    HomeScreen(navController = navController)
}