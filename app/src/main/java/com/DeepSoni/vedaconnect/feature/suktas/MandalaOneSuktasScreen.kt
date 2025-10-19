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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.DeepSoni.vedaconnect.audio.rememberMantraPlayer
import com.DeepSoni.vedaconnect.data.Sukta
import com.DeepSoni.vedaconnect.repository.MandalaOneSuktasRepository
import com.DeepSoni.vedaconnect.ui.theme.VedaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MandalaOneSuktasScreen(navController: NavHostController) {
    // Fetch the list of suktas from the repository.
    // 'remember' ensures this is done only once during the initial composition.
    val suktas = remember { MandalaOneSuktasRepository.suktas }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = VedaTheme.Cream
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

            // If the suktas list is empty, you can show a message.
            if (suktas.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No Suktas found.",
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
                        SuktaCard(sukta = sukta) {
                            // TODO: Navigate to Sukta detail screen
                            // navController.navigate("sukta_detail/${sukta.id}")
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SuktaCard(sukta: Sukta, onClick: () -> Unit) {
    // Conditionally initialize the audio player only if an audio URL exists.
    val (isPlaying, togglePlayPause) = if (sukta.audioUrl != null) {
        rememberMantraPlayer(sukta.audioUrl)
    } else {
        // Provide default values if there's no audio to avoid runtime errors.
        remember { Pair(false, {}) }
    }

    val rigvedaCardGradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFF8B4513), Color(0xFFA0522D), Color(0xFFD2B48C))
    )

    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        // Box with background gradient
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(rigvedaCardGradient)
                .clickable { onClick() } // Make the whole area clickable for navigation
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // --- Sukta Info ---
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

                // --- Action Buttons ---
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp, Alignment.Start),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Show Play/Pause button only if there is an audio URL
                    if (sukta.audioUrl != null) {
                        IconButton(
                            onClick = { togglePlayPause() },
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(if (isPlaying) Color(0xFFCD5C5C) else Color(0xFFFFA07A))
                        ) {
                            Icon(
                                imageVector = if (isPlaying) Icons.Default.Pause else Icons.Outlined.PlayArrow,
                                contentDescription = if (isPlaying) "Pause Mantra" else "Play Mantra",
                                tint = Color.White
                            )
                        }
                    }

                    // View Button
                    IconButton(
                        onClick = { onClick() },
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFDAA520))
                    ) {
                        Icon(
                            imageVector = Icons.Default.Visibility,
                            contentDescription = "View Details",
                            tint = Color.White
                        )
                    }

                    // Favorite Button
                    IconButton(
                        onClick = { /* TODO: Handle favorite action */ },
                        modifier = Modifier
                            .size(48.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFB0C4DE))
                    ) {
                        Icon(
                            imageVector = Icons.Default.StarOutline,
                            contentDescription = "Favorite Mantra",
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}