package com.DeepSoni.vedaconnect.feature.streak

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun StreakScreen(navController: NavController, paddingValues: PaddingValues) {
    // The Scaffold is now in AppNavigation.kt
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color(0xFFFFF7F0)) // Set background here
    ) {
        StreakHeader()
        Spacer(modifier = Modifier.height(paddingValues.calculateTopPadding()))
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            CurrentStreakCard()
            Spacer(modifier = Modifier.height(24.dp))
            WeeklyProgressCard()
            Spacer(modifier = Modifier.height(24.dp))
            StatsOverview()
            Spacer(modifier = Modifier.height(24.dp))
            BadgesSection()
            Spacer(modifier = Modifier.height(24.dp))
            ShareProgressButton()
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun StreakHeader() {
    val headerOrangeGradient = Brush.verticalGradient(
        colors = listOf(Color(0xFFF57C00), Color(0xFFFB8C00), Color(0xFFFF9800))
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(headerOrangeGradient, shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
            .padding(top = 50.dp, bottom = 15.dp, start = 16.dp, end = 16.dp),
    ) {
        Column {
            Text(
                text = "Your Progress",
                style = MaterialTheme.typography.headlineMedium,
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Track your spiritual journey",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 14.sp,
                color = Color.White.copy(alpha = 0.9f)
            )
        }
    }
}

@Composable
fun CurrentStreakCard() {
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
                    text = "Current Streak",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
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
                    text = "Longest: 18 days",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White.copy(alpha = 0.9f)
                )
            }
            Icon(
                imageVector = Icons.Outlined.Whatshot,
                contentDescription = "Streak Flame",
                tint = Color.White,
                modifier = Modifier.size(70.dp)
            )
        }
    }
}

@Composable
fun WeeklyProgressCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = "This Week",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                DayProgress("M", completed = true)
                DayProgress("T", completed = true)
                DayProgress("W", completed = true)
                DayProgress("T", completed = false)
                DayProgress("F", completed = false)
                DayProgress("S", completed = false)
                DayProgress("S", completed = false)
            }
        }
    }
}

@Composable
fun DayProgress(day: String, completed: Boolean) {
    val circleColor = if (completed) Color(0xFFF57C00) else Color.LightGray.copy(alpha = 0.5f)
    val checkColor = if (completed) Color.White else Color.Transparent

    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape)
                .background(circleColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = if (completed) "Completed" else "Incomplete",
                tint = checkColor,
                modifier = Modifier.size(24.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = day, fontWeight = FontWeight.SemiBold, color = Color.Gray)
    }
}

@Composable
fun StatsOverview() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        StatItem(label = "Total Days", value = "156", modifier = Modifier.weight(1f))
        StatItem(label = "Dharma Drops", value = "45", modifier = Modifier.weight(1f))
        StatItem(label = "Quizzes", value = "8", modifier = Modifier.weight(1f))
    }
}

// =====================================================================
// MODIFIED SECTION
// =====================================================================
@Composable
fun StatItem(label: String, value: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth() // <-- THIS IS THE FIX
                .padding(vertical = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = value,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Black // <-- Changed color to match image
            )
        }
    }
}
// =====================================================================
// END OF MODIFIED SECTION
// =====================================================================


@Composable
fun BadgesSection() {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Jnana Badges",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
            Text(
                text = "6/12",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                BadgeItem(icon = Icons.Outlined.Spa, title = "First Step", tier = "Bronze", earned = true, modifier = Modifier.weight(1f))
                BadgeItem(icon = Icons.Outlined.StarOutline, title = "Dedicated", tier = "Silver", earned = true, modifier = Modifier.weight(1f))
                BadgeItem(icon = Icons.Outlined.EmojiEvents, title = "Enlightened", tier = "Gold", earned = false, modifier = Modifier.weight(1f))
            }
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                BadgeItem(icon = Icons.Outlined.MenuBook, title = "Wisdom Seeker", tier = "Bronze", earned = true, modifier = Modifier.weight(1f))
                BadgeItem(icon = Icons.Outlined.Forum, title = "Community Hero", tier = "Silver", earned = false, modifier = Modifier.weight(1f))
                BadgeItem(icon = Icons.Outlined.WorkspacePremium, title = "Quiz Master", tier = "Gold", earned = true, modifier = Modifier.weight(1f))
            }
        }
    }
}

@Composable
fun BadgeItem(icon: ImageVector, title: String, tier: String, earned: Boolean, modifier: Modifier = Modifier) {
    val contentColor = if (earned) Color.Black else Color.LightGray
    val cardBackgroundColor = if (earned) Color.White else Color.White.copy(alpha = 0.7f)
    val tierColor = when (tier) {
        "Bronze" -> Color(0xFFCD7F32)
        "Silver" -> Color(0xFFC0C0C0)
        "Gold" -> Color(0xFFFFD700)
        else -> Color.Gray
    }

    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = cardBackgroundColor),
        elevation = CardDefaults.cardElevation(defaultElevation = if(earned) 2.dp else 0.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = if (earned) Color(0xFFF57C00) else contentColor,
                modifier = Modifier.size(32.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = contentColor,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))
            Surface(
                shape = RoundedCornerShape(8.dp),
                color = if (earned) tierColor.copy(alpha = 0.2f) else Color.Transparent,
                border = if (!earned) BorderStroke(1.dp, contentColor) else null
            ) {
                Text(
                    text = tier,
                    style = MaterialTheme.typography.labelSmall,
                    color = if (earned) tierColor.copy(alpha = 0.9f) else contentColor,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                )
            }
        }
    }
}

@Composable
fun ShareProgressButton() {
    Button(
        onClick = { /* TODO: Implement share functionality */ },
        modifier = Modifier
            .fillMaxWidth()
            .height(52.dp),
        shape = RoundedCornerShape(12.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF57C00))
    ) {
        Icon(
            imageVector = Icons.Default.Share,
            contentDescription = "Share Progress",
            tint = Color.White
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "Share Your Progress", color = Color.White, fontWeight = FontWeight.Bold)
    }
}

@Preview(showBackground = true, device = "id:pixel_6")
@Composable
fun StreakScreenPreview() {
    StreakScreen(navController = rememberNavController(), paddingValues = PaddingValues())
}