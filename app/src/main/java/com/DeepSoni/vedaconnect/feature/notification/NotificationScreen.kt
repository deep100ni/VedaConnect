// Ensure this package declaration is all lowercase and matches your directory structure
package com.DeepSoni.vedaconnect.feature.notification // Corrected to lowercase 'deepsoni'

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.outlined.Schedule
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController // Import NavController
import androidx.navigation.compose.rememberNavController // Import for Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationScreen(navController: NavController) { // Changed to accept NavController
    Scaffold(
        containerColor = Color(0xFFF0F4F8) // Light blue-gray background for the screen content
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues), // Apply padding from Scaffold
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val fullHeaderBlueGradient = Brush.verticalGradient(
                colors = listOf(Color(0xFFF57C00), Color(0xFFFB8C00), Color(0xFFFF9800))
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(130.dp) // Adjust height as needed to cover both texts
                    .background(fullHeaderBlueGradient, shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
                    .padding(horizontal = 16.dp, vertical = 8.dp) // Padding for content
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(onClick = { navController.popBackStack() }) { // Use navController to go back
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.White)
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Daily Reminder",
                            color = Color.White,
                            fontWeight = FontWeight.Bold,
                            style = MaterialTheme.typography.headlineSmall // Adjust text style
                        )
                    }
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Never miss your daily dharma",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color.White.copy(alpha = 0.9f),
                        modifier = Modifier.padding(start = 16.dp) // Align with "Daily Reminder" text
                    )
                }
            }
            Spacer(modifier = Modifier.height(16.dp)) // Space after the full header box

            // Rest of your cards (Set Your Time, It's Dharma Time!)
            Column(modifier = Modifier.padding(horizontal = 16.dp)) { // Add horizontal padding for cards
                // Set Your Time Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                imageVector = Icons.Outlined.Schedule, // A clock icon
                                contentDescription = "Set Time",
                                tint = Color(0xFFF57C00), // Blue
                                modifier = Modifier.size(28.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = "Set Your Time",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.Black
                                )
                                Text(
                                    text = "Choose when to receive your daily drop",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.Gray
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp)
                                .background(Color(0xFFFFF7E6), RoundedCornerShape(12.dp)), // Light blue background
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Text(
                                    text = "08:00",
                                    fontSize = 48.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Black
                                )
                                Text(
                                    text = "AM",
                                    style = MaterialTheme.typography.titleMedium,
                                    color = Color.Gray
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))

                        var enableNotification by remember { mutableStateOf(true) }
                        var enableVibration by remember { mutableStateOf(true) }

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Enable Notification",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.Black
                            )
                            Switch(
                                checked = enableNotification,
                                onCheckedChange = { enableNotification = it },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    checkedTrackColor = Color(0xFFFF9800), // Orange
                                    uncheckedThumbColor = Color.White,
                                    uncheckedTrackColor = Color.LightGray
                                )
                            )
                        }
                        Spacer(modifier = Modifier.height(8.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = "Enable Vibration",
                                style = MaterialTheme.typography.bodyLarge,
                                color = Color.Black
                            )
                            Switch(
                                checked = enableVibration,
                                onCheckedChange = { enableVibration = it },
                                colors = SwitchDefaults.colors(
                                    checkedThumbColor = Color.White,
                                    checkedTrackColor = Color(0xFFFF9800), // Orange
                                    uncheckedThumbColor = Color.White,
                                    uncheckedTrackColor = Color.LightGray
                                )
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(24.dp))

                // It's Dharma Time! Card
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    shape = RoundedCornerShape(16.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = "Dharma Time",
                                tint = Color(0xFFF57C00), // Orange
                                modifier = Modifier.size(28.dp)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Column {
                                Text(
                                    text = "It's Dharma Time!",
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.SemiBold,
                                    color = Color.Black
                                )
                                Text(
                                    text = "Your daily wisdom is ready",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = Color.Gray
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(12.dp),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF7F0)) // Light orange background
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = "Rigveda 10.191.2",
                                    style = MaterialTheme.typography.labelMedium,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.Gray
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "\"May your thoughts be united, may your hearts be united...\"",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = Color.Black.copy(alpha = 0.7f)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(16.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceAround
                        ) {
                            Button(
                                onClick = { /* TODO: Implement Snooze */ },
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFE0E0E0)),
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(text = "Snooze 5m", color = Color.Black)
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Button(
                                onClick = { /* TODO: Implement Dismiss */ },
                                shape = RoundedCornerShape(12.dp),
                                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF57C00)), // Orange
                                modifier = Modifier.weight(1f)
                            ) {
                                Text(text = "Dismiss", color = Color.White)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, device = "id:pixel_6")
@Composable
fun NotificationScreenPreview() {
    // For preview, provide a mock NavController
    val navController = rememberNavController()
    NotificationScreen(navController = navController)
}