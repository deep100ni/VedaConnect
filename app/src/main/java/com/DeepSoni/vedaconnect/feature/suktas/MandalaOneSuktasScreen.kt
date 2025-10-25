package com.DeepSoni.vedaconnect.feature.suktas

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.StarOutline
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.DeepSoni.vedaconnect.Screen
import com.DeepSoni.vedaconnect.audio.rememberMantraPlayer
import com.DeepSoni.vedaconnect.data.Sukta
import com.DeepSoni.vedaconnect.repository.MandalaOneSuktasRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MandalaOneSuktasScreen(navController: NavHostController) {
    val context = LocalContext.current
    var suktas by remember { mutableStateOf<List<Sukta>>(emptyList()) }

    // Load the suktas when the screen is first composed
    LaunchedEffect(Unit) {
        MandalaOneSuktasRepository.initialize(context)
        suktas = MandalaOneSuktasRepository.suktas
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFFFF7F0) // A consistent background color
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                title = { Text("Mandala 1 Suktas") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFFF57C00),
                    titleContentColor = Color.White,
                    navigationIconContentColor = Color.White
                )
            )

            if (suktas.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    // You might want a CircularProgressIndicator here while loading
                    Text(
                        text = "Loading Suktas...",
                        fontSize = 18.sp,
                        color = Color.Gray
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(suktas) { sukta ->
                        SuktaCard(
                            sukta = sukta,
                            onClick = {
                                // Navigate to the detail screen using the type-safe route
                                navController.navigate(Screen.SuktaDetail.createRoute(sukta.id))
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun SuktaCard(sukta: Sukta, onClick: () -> Unit) {
    val (isPlaying, togglePlayPause) = if (sukta.audioUrl != null) {
        rememberMantraPlayer(sukta.audioUrl)
    } else {
        remember { false to {} }
    }

    val rigvedaCardGradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFF8B4513), Color(0xFFA0522D), Color(0xFFD2B48C))
    )

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(rigvedaCardGradient, shape = RoundedCornerShape(16.dp))
                .clickable(onClick = onClick) // The whole card is clickable
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(
                    text = sukta.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Mandala ${sukta.mandalaNumber} | Sukta ${sukta.suktaNumber}",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text(
                    text = sukta.preview,
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.9f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    lineHeight = 20.sp
                )
                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (sukta.audioUrl != null) {
                        Spacer(modifier = Modifier.height(16.dp))
                        IconButton(
                            onClick = { togglePlayPause() },
                            modifier = Modifier.size(48.dp).clip(CircleShape)
                                .background(if (isPlaying) Color(0xFFCD5C5C) else Color(0xFFFFA07A))
                        ) {
                            Icon(
                                imageVector = if (isPlaying) Icons.Default.Pause else Icons.Outlined.PlayArrow,
                                contentDescription = if (isPlaying) "Pause" else "Play",
                                tint = Color.White
                            )
                        }
                    }
                    IconButton(
                        onClick = onClick, // View button also navigates
                        modifier = Modifier.size(48.dp).clip(CircleShape).background(Color(0xFFDAA520))
                    ) {
                        Icon(
                            imageVector = Icons.Default.Visibility,
                            contentDescription = "View Details",
                            tint = Color.White
                        )
                    }
                    IconButton(
                        onClick = { /* TODO: Handle favorite action */ },
                        modifier = Modifier.size(48.dp).clip(CircleShape).background(Color(0xFFB0C4DE))
                    ) {
                        Icon(
                            imageVector = Icons.Default.StarOutline,
                            contentDescription = "Favorite",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}