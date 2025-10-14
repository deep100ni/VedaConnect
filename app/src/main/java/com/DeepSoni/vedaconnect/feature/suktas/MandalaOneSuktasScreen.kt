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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.DeepSoni.vedaconnect.Data.Mantra
import com.DeepSoni.vedaconnect.Data.Sukta
import com.DeepSoni.vedaconnect.Repository.MandalaOneSuktasRepository
import com.DeepSoni.vedaconnect.feature.audio.rememberMantraPlayer
import com.DeepSoni.vedaconnect.ui.theme.VedaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MandalaOneSuktasScreen(navController: NavHostController) {
    val suktas = MandalaOneSuktasRepository.suktas

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(VedaTheme.Cream)
    ) {
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

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(suktas) { sukta ->
                SuktaCard(sukta = sukta) {
                    // Navigate to Sukta detail screen if you create one
                }
            }
        }
    }
}
// 🪔 Mantra Card with Play Icon (UNCHANGED)
@Composable
fun SuktaCard(sukta: Sukta, onClick: () -> Unit) {
    // Only initialize player if audioUrl is not null
    val (isPlaying, togglePlayPause) = if (sukta.audioUrl != null) {
        rememberMantraPlayer(sukta.audioUrl)
    } else {
        Pair(false) {} // Return a default pair if no audio
    }

    // Rigveda inspired gradient colors
    val rigvedaCardGradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFF8B4513), Color(0xFFA0522D), Color(0xFFD2B48C)) // SaddleBrown, Sienna, Tan
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }, // The whole card is clickable for 'View'
        colors = CardDefaults.cardColors(containerColor = Color.Transparent), // Set to transparent to show gradient
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(rigvedaCardGradient, shape = RoundedCornerShape(16.dp))
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                // 📖 Mantra Info
                Text(
                    text = sukta.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = if (sukta.mandalaNumber != 0) "Mandala ${sukta.mandalaNumber}" else "",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )
                if (sukta.suktaNumber != 0) { // Only show sukta and mantra number if they exist
                    Text(
                       text = "Sukta ${sukta.suktaNumber}",
                        fontSize = 14.sp,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = sukta.preview,
                    fontSize = 12.sp,
                    color = Color.White.copy(alpha = 0.6f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Action Buttons Row
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // ▶️ Play/Pause Button - ONLY show if audioUrl is NOT null
                    if (sukta.audioUrl != null) {
                        Button(
                            onClick = { togglePlayPause() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isPlaying) Color(0xFFCD5C5C) else Color(0xFFFFA07A) // IndianRed, LightSalmon
                            ),
                            shape = CircleShape,
                            contentPadding = PaddingValues(10.dp),
                            modifier = Modifier.size(48.dp)
                        ) {
                            Icon(
                                imageVector = if (isPlaying) Icons.Default.Pause else Icons.Outlined.PlayArrow,
                                contentDescription = if (isPlaying) "Pause Mantra" else "Play Mantra",
                                tint = Color.White,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    }

                    // 👁️ View Button (always present)
                    Button(
                        onClick = { onClick() }, // Triggers the card's original onClick for details
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDAA520)), // Goldenrod
                        shape = CircleShape,
                        contentPadding = PaddingValues(10.dp),
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Visibility,
                            contentDescription = "View Details",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }

                    // ⭐ Star (Favorite) Button (always present)
                    Button(
                        onClick = { /* Handle favorite action */ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB0C4DE)), // LightSteelBlue
                        shape = CircleShape,
                        contentPadding = PaddingValues(10.dp),
                        modifier = Modifier.size(48.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.StarOutline,
                            contentDescription = "Favorite Mantra",
                            tint = Color.White,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                }
            }
        }
    }
}