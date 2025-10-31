package com.DeepSoni.vedaconnect.feature.content

import android.media.MediaPlayer
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.DeepSoni.vedaconnect.data.Mantra
import com.DeepSoni.vedaconnect.data.Mandala
import com.DeepSoni.vedaconnect.repository.MandalaRepository
import com.DeepSoni.vedaconnect.repository.MantraRepository
import com.DeepSoni.vedaconnect.Screen
import com.DeepSoni.vedaconnect.audio.rememberMantraPlayer
import com.DeepSoni.vedaconnect.ui.theme.VedaTheme

// ✅ Audio player helper - Kept for potential future use or if other parts of the app use it, though not directly used by the Mandala-only content screen
@Composable
fun rememberMantraPlayer(audioUrl: String?): Pair<Boolean, () -> Unit> {
    val context = LocalContext.current
    var isPlaying by remember { mutableStateOf(false) }
    var mediaPlayer: MediaPlayer? by remember { mutableStateOf(null) }

    fun togglePlayPause() {
        if (audioUrl == null) return
        if (isPlaying) {
            mediaPlayer?.pause()
            isPlaying = false
        } else {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer()
                mediaPlayer?.apply {
                    setDataSource(audioUrl)
                    prepareAsync()
                    setOnPreparedListener { player ->
                        player.start()
                        isPlaying = true
                    }
                    setOnCompletionListener {
                        isPlaying = false
                    }
                }
            } else {
                mediaPlayer?.start()
                isPlaying = true
            }
        }
    }

    DisposableEffect(audioUrl) {
        onDispose {
            mediaPlayer?.release()
            mediaPlayer = null
            isPlaying = false
        }
    }

    return Pair(isPlaying, ::togglePlayPause)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentScreen(navController: NavHostController) {
    var searchQuery by remember { mutableStateOf("") }
    // Only Mandalas tab is needed
    val selectedTab by remember { mutableIntStateOf(0) } // Always 0 for Mandalas

    val tabs = listOf("Mandalas") // Only Mandalas tab

    // Filtered lists based on search query
    val filteredMandalas = remember(searchQuery) {
        MandalaRepository.searchMandalas(searchQuery)
    }

    val headerOrangeGradient = Brush.verticalGradient(
        colors = listOf(Color(0xFFF57C00), Color(0xFFFB8C00), Color(0xFFFF9800))
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(VedaTheme.Cream)
    ) {
        // 🟠 Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(headerOrangeGradient, shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 22.dp))
                .padding(top = 50.dp, bottom = 15.dp, start = 16.dp, end = 16.dp),
        ) {
            Column(modifier = Modifier.align(Alignment.TopStart)) {
                Text(
                    text = "Explore Mandalas 🙏",
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "Welcome back to your spiritual journey",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.9f)
                )
            }
        }

        // 🔍 Search Bar
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            placeholder = { Text("Search Mandala") }, // Updated placeholder
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = VedaTheme.Orange
                )
            },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Color(0xFFF57C00),
                unfocusedBorderColor = Color.Gray.copy(alpha = 0.3f),
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                // Add this line to set the text color to black
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {})
        )





        // 📜 Content List (Only Mandalas)
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(filteredMandalas) { mandala ->
                MandalaCard(mandala = mandala) {
                    navController.navigate(Screen.Suktas.createRoute(mandala.mandalaNumber))
                }
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

// 🏞️ Mandala Card (UPDATED with elevation)
@Composable
fun MandalaCard(mandala: Mandala, onClick: () -> Unit) {
    val rigvedaCardGradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFF8B4513), Color(0xFFA0522D), Color(0xFF8B4513))
    )

    // For detecting press state
    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    // Animate elevation depending on press state
    val animatedElevation by animateDpAsState(
        targetValue = if (isPressed) 5.dp else 15.dp,
        label = "cardElevationAnimation"
    )

    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) { onClick() },
        shape = RoundedCornerShape(16.dp),
        color = Color.White, // Required for visible shadow
        shadowElevation = animatedElevation // 👈 Animated elevation
    ) {
        Box(
            modifier = Modifier
                .background(rigvedaCardGradient, shape = RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Column {
                Text(
                    text = mandala.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "Mandala ${mandala.mandalaNumber}",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = mandala.preview,
                    fontSize = 12.sp,
                    color = Color.White.copy(alpha = 0.6f),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}



// 📚 Section Card (UNCHANGED)
@Composable
fun SectionCard(
    title: String,
    content: String,
    contentColor: Color = Color.Black
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = VedaTheme.Orange.copy(alpha = 0.8f),
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Color(0xFFFFF5E6)),
            shape = RoundedCornerShape(16.dp)
        ) {
            Text(
                text = content,
                fontSize = 15.sp,
                color = contentColor,
                lineHeight = 24.sp,
                modifier = Modifier.padding(20.dp)
            )
        }
    }
}