package com.DeepSoni.vedaconnect.feature.suktas

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import com.DeepSoni.vedaconnect.audio.PlayerState
import com.DeepSoni.vedaconnect.audio.rememberMantraPlayer
import com.DeepSoni.vedaconnect.data.Sukta
import com.DeepSoni.vedaconnect.repository.RigvedaRepository

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuktasScreen(navController: NavHostController, mandalaNumber: Int) {
    val context = LocalContext.current
    var suktas by remember { mutableStateOf<List<Sukta>>(emptyList()) }
    var searchQuery by remember { mutableStateOf("") } // State for the search query

    // Load the suktas for the specific mandala number
    LaunchedEffect(mandalaNumber) {
        RigvedaRepository.initialize(context) // Ensure it's initialized
        suktas = RigvedaRepository.getSuktasForMandala(mandalaNumber)
    }

    // Filtered list of suktas based on search query
    val filteredSuktas = remember(suktas, searchQuery) {
        if (searchQuery.isBlank()) {
            suktas
        } else {
            suktas.filter {
                it.name.contains(searchQuery, ignoreCase = true) ||
                        it.preview.contains(searchQuery, ignoreCase = true) ||
                        it.suktaNumber.toString().contains(searchQuery, ignoreCase = true)
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFFFF7F0)
    ) {
        Column(modifier = Modifier.fillMaxSize()) {
            TopAppBar(
                title = { Text("Mandala $mandalaNumber Suktas") },
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

            // Search Bar
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                placeholder = { Text("Search suktas...") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search icon"
                    )
                },
                singleLine = true,
                shape = RoundedCornerShape(24.dp), // Rounded corners for the search bar
                colors = OutlinedTextFieldDefaults.colors( // Use OutlinedTextFieldDefaults.colors
                    focusedBorderColor = Color(0xFFF57C00),
                    unfocusedBorderColor = Color.Gray.copy(alpha = 0.5f),
                    focusedLabelColor = Color(0xFFF57C00),
                    cursorColor = Color(0xFFF57C00),
                    focusedTextColor = Color.Black,   // Set text color to Black when focused
                    unfocusedTextColor = Color.Black, // Set text color to Black when unfocused
                    focusedContainerColor = Color.White,
                    unfocusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                )
            )

            if (filteredSuktas.isEmpty() && searchQuery.isNotBlank()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "No suktas found for \"$searchQuery\"",
                        fontSize = 18.sp,
                        color = Color.Gray
                    )
                }
            } else if (suktas.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Loading Suktas...",
                        fontSize = 18.sp,
                        color = Color.Gray
                    )
                }
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(filteredSuktas) { sukta ->
                        SuktaCard(
                            sukta = sukta,
                            onClick = {
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
    val rigvedaCardGradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFF8B4513), Color(0xFFA0522D), Color(0xFF8B4513))
    )

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 85.dp), // Set a minimum height for the card
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize() // Fill the whole card area
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
            }

            // Play/Pause Button positioned at top-right
            if (sukta.audioUrl != null) {
                val (playerState, togglePlayPause) = rememberMantraPlayer(sukta.audioUrl)

                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .align(Alignment.TopEnd) // Align to the top-right corner of the Box
                        .padding(8.dp) // Add some padding from the edges
                        .size(48.dp)
                        .clip(CircleShape)
                        .background(
                            if (playerState == PlayerState.PLAYING) Color(0xFFCD5C5C) else Color(
                                0xFFFFA07A
                            )
                        )
                        .clickable(onClick = { togglePlayPause() })
                ) {
                    when (playerState) {
                        PlayerState.LOADING -> {
                            CircularProgressIndicator(
                                modifier = Modifier.size(30.dp),
                                color = Color.White,
                                strokeWidth = 3.dp
                            )
                        }
                        PlayerState.PLAYING -> {
                            Icon(
                                imageVector = Icons.Default.Pause,
                                contentDescription = "Pause",
                                tint = Color.White
                            )
                        }
                        else -> { // IDLE, PAUSED
                            Icon(
                                imageVector = Icons.Outlined.PlayArrow,
                                contentDescription = "Play",
                                tint = Color.White
                            )
                        }
                    }
                }
            }
        }
    }
}