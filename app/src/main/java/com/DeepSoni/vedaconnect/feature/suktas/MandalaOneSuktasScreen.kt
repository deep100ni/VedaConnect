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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.DeepSoni.vedaconnect.data.Sukta
import com.DeepSoni.vedaconnect.audio.rememberMantraPlayer
import com.DeepSoni.vedaconnect.ui.theme.VedaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MandalaOneSuktasScreen(
    navController: NavHostController,
    mandalaId: Int,
    // 1. Get an instance of our new ViewModel.
    viewModel: MandalaOneSuktasViewModel = viewModel()
) {
    LaunchedEffect(key1 = mandalaId) {
        viewModel.loadSukasForMandala(mandalaId)
    }
    // 2. Collect the data and loading state from the ViewModel.
    //    Compose will automatically redraw when these values change.
    val suktas by viewModel.suktas.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(VedaTheme.Cream)
    ) {
        TopAppBar(
            title = { Text("Mandala $mandalaId Suktas") },
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

        // 3. Show a loading indicator while data is loading, or the list when it's ready.
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = Color(0xFFF57C00))
            } else {
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
    }
}

// No changes are needed for SuktaCard
@Composable
fun SuktaCard(sukta: Sukta, onClick: () -> Unit) {
    val (isPlaying, togglePlayPause) = if (sukta.audioUrl != null) {
        rememberMantraPlayer(sukta.audioUrl)
    } else {
        Pair(false, { /* Do nothing */ })
    }

    val rigvedaCardGradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFF8B4513), Color(0xFFA0522D), Color(0xFFD2B48C)) // SaddleBrown, Sienna, Tan
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.Transparent),
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
                if (sukta.suktaNumber != 0) {
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

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    if (sukta.audioUrl != null) {
                        Button(
                            onClick = { togglePlayPause() },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = if (isPlaying) Color(0xFFCD5C5C) else Color(0xFFFFA07A)
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

                    Button(
                        onClick = { onClick() },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFDAA520)),
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

                    Button(
                        onClick = { /* Handle favorite action */ },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFB0C4DE)),
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