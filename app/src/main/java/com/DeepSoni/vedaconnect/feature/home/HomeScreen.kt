package com.DeepSoni.vedaconnect.feature.home

import android.media.MediaPlayer
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.DeepSoni.vedaconnect.R

// ✅ Audio player helper
@Composable
fun rememberMantraPlayer(audioResId: Int?): Pair<Boolean, () -> Unit> {
    val context = LocalContext.current
    var isPlaying by remember { mutableStateOf(false) }
    var mediaPlayer: MediaPlayer? by remember { mutableStateOf(null) }

    fun togglePlayPause() {
        if (audioResId == null) return
        if (isPlaying) {
            mediaPlayer?.pause()
            isPlaying = false
        } else {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(context, audioResId)
            }
            mediaPlayer?.start()
            isPlaying = true
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer?.release()
            mediaPlayer = null
        }
    }

    return Pair(isPlaying, ::togglePlayPause)
}

@Composable
fun HomeScreen(navController: NavController) {
    // The Scaffold is now in AppNavigation.kt, so we just provide the content.
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(Color(0xFFFFF7F0)) // Set background here
    ) {
        HomeHeader(navController = navController) // Pass NavController
        Spacer(modifier = Modifier.height(24.dp))
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            DailyDharmaDropCard()
            Spacer(modifier = Modifier.height(24.dp))
            SadhanaStreakCard()
            Spacer(modifier = Modifier.height(24.dp))
            QuickActions(navController = navController) // Pass NavController
            Spacer(modifier = Modifier.height(24.dp))
        }
    }
}

@Composable
fun HomeHeader(navController: NavController) { // Accept NavController
    val headerOrangeGradient = Brush.verticalGradient(
        colors = listOf(Color(0xFFF57C00), Color(0xFFFB8C00), Color(0xFFFF9800))
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(headerOrangeGradient, shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
            .padding(top = 50.dp, bottom = 15.dp, start = 16.dp, end = 16.dp),
    ) {
        Column(
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            Text(
                text = "Namaste 🙏",
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Welcome back to your spiritual journey",
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 14.sp,
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
                .clickable { navController.navigate("notification") } // Navigate on click
        )
    }
}

@Composable
fun DailyDharmaDropCard() {
    val (isPlaying, togglePlayPause) = rememberMantraPlayer(audioResId = R.raw.gayatri)

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
                        .background(Color(0xFFE8E0F8), CircleShape)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF7F0))
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Gayatri Mantra",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = Color.Gray
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "\"Aum Bhur Bhuvah Swah, Tat Savitur Varenyam...\"",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Black.copy(alpha = 0.7f)
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = { togglePlayPause() },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF57C00))
            ) {
                Icon(
                    imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                    contentDescription = if (isPlaying) "Pause Audio" else "Play Audio",
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = if (isPlaying) "Pause Audio" else "Play Gayatri Mantra",
                    color = Color.White
                )
            }
        }
    }
}

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
                imageVector = Icons.Outlined.Whatshot,
                contentDescription = "Streak Flame",
                tint = Color.White,
                modifier = Modifier.size(70.dp)
            )
        }
    }
}

@Composable
fun QuickActions(navController: NavController) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Quick Actions",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp),
            color = Color.Black
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            QuickActionItem(icon = Icons.Outlined.WorkspacePremium, text = "Weekly Quiz", onClick = { navController.navigate("quiz") }, modifier = Modifier.weight(1f))

            QuickActionItem(icon = Icons.Outlined.AutoStories, text = "Content", onClick = { navController.navigate(route = "content") }, modifier = Modifier.weight(1f))
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            QuickActionItem(icon = Icons.Outlined.Article, text = "Awareness", onClick = { navController.navigate("community") }, modifier = Modifier.weight(1f))
            QuickActionItem(
                icon = Icons.Outlined.Whatshot,
                text = "My Streaks",
                onClick = { navController.navigate("streaks") },
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
fun QuickActionItem(icon: ImageVector, text: String, onClick: () -> Unit, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier.clickable(onClick = onClick),
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
                textAlign = TextAlign.Center,
                color = Color.Black
            )
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_6")
@Composable
fun HomeScreenPreview() {
    HomeScreen(navController = rememberNavController())
}