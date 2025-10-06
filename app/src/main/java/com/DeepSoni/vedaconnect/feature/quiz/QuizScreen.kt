package com.DeepSoni.vedaconnect.feature.weeklyquiz

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.WorkspacePremium
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.DeepSoni.vedaconnect.Screen

// Color definitions used within this screen
val OrangePrimary = Color(0xFFF77F00)
val LightOrangeBg: Color = Color(0xFFFFEEE0)

/**
 * The main entry point for the Quiz feature screen.
 * It displays the header, the current weekly challenge, and a list of previous quizzes.
 */
@Composable
fun QuizScreen(navController: NavHostController) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header section with the orange background
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    OrangePrimary,
                    shape = RoundedCornerShape(bottomStart = 16.dp, bottomEnd = 16.dp)
                )
                .padding(16.dp)
                .height(100.dp)
        ) {
            Column {
                Text(
                    text = "Weekly Quiz",
                    color = Color.White,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Test your Vedic Knowledge",
                    color = Color.White.copy(alpha = 0.8f),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }

        // Scrollable body content
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 16.dp)
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            WeeklyChallengeCard(
                modifier = Modifier.fillMaxWidth(),
                navController = navController
            )

            Spacer(modifier = Modifier.height(16.dp))

            // The list of previous weeks now requires the NavController
            PreviousWeekSection(
                modifier = Modifier.fillMaxWidth(),
                navController = navController
            )

            Spacer(modifier = Modifier.height(60.dp)) // Padding at the bottom
        }
    }
}

/**
 * Displays a list of previous weekly quizzes. Each item in the list is clickable
 * and navigates to the QuizComplete screen.
 */
@Composable
fun PreviousWeekSection(modifier: Modifier = Modifier, navController: NavController) {
    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {
        Text(
            text = "Previous Week",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
        )
        Spacer(modifier = Modifier.height(8.dp))

        // Hardcoded list for demonstration purposes
        val previousWeeks = listOf(
            "Week 1" to 0.8f,
            "Week 2" to 0.6f,
            "Week 3" to 0.7f,
            "Week 4" to 0.5f,
            "Week 5" to 0.9f
        )

        previousWeeks.forEach { (week, progress) ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    // Apply clipping before clickable to ensure the ripple effect respects the rounded corners
                    .clip(RoundedCornerShape(10.dp))
                    .clickable {
                        // Navigate to the completion screen when a previous quiz is clicked.
                        // In a real app, you would pass a specific quiz ID here.
                        navController.navigate(Screen.QuizComplete.route)
                    }
                    .background(Color.LightGray) // The shape is now handled by the clip modifier
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = week,
                    fontSize = 16.sp,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.width(8.dp))
                Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                    LinearProgressIndicator(
                        progress = { progress }, // Updated syntax for progress
                        modifier = Modifier
                            .weight(1f)
                            .height(10.dp),
                        color = OrangePrimary,
                        trackColor = Color.White
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "${(progress * 100).toInt()}/100",
                        fontSize = 16.sp,
                        color = Color.Gray
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Icon(
                    imageVector = Icons.Outlined.WorkspacePremium,
                    contentDescription = "Trophy Icon",
                    tint = Color.Gray
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}

/**
 * A generic card for displaying statistics. (Not used in the final layout but kept for completeness).
 */
@Composable
fun StateCard(icon: ImageVector, label: String, value: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = LightOrangeBg)
    ) {
        Row(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = Color(0xFFF57C00),
                modifier = Modifier.size(35.dp)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = value,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = label,
                    fontSize = 12.sp,
                    color = Color.Gray
                )
            }
        }
    }
}

/**
 * A card that presents the current weekly challenge and a button to start it.
 * The "Start Quiz" button's navigation has been removed as requested.
 */
@Composable
fun WeeklyChallengeCard(modifier: Modifier = Modifier, navController: NavHostController) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = LightOrangeBg)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Outlined.WorkspacePremium,
                contentDescription = "Weekly Challenge Icon",
                modifier = Modifier.size(60.dp),
                tint = OrangePrimary
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Weekly Challenge",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Answer 10 questions to get points and climb the leaderboard",
                fontSize = 15.sp,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 16.dp)
            )
            Spacer(modifier = Modifier.height(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                QuizInfoItem(value = "10", label = "Questions")
                QuizInfoItem(value = "5:00", label = "Minutes")
                QuizInfoItem(value = "+100", label = "Max Points")
            }
            Spacer(modifier = Modifier.height(24.dp))

            // The button to start the current week's quiz
            Button(
                onClick = {
                    // TODO: Implement navigation to the actual quiz-taking screen.
                    // For now, this button does nothing as per the request.
                    // Example: navController.navigate("quiz_questions_route")
                },
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = OrangePrimary),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Text(text = "Start Quiz", color = Color.White, fontSize = 18.sp)
            }
        }
    }
}

/**
 * A small composable to display a piece of quiz information (e.g., "10 Questions").
 */
@Composable
fun QuizInfoItem(value: String, label: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = value, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text(text = label, fontSize = 15.sp, color = Color.Gray)
    }
}