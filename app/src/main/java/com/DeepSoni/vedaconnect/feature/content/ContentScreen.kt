package com.DeepSoni.vedaconnect.feature.content

import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import com.DeepSoni.vedaconnect.Data.Mantra
import com.DeepSoni.vedaconnect.Data.Mandala // Import the new Mandala data class
import com.DeepSoni.vedaconnect.R
import com.DeepSoni.vedaconnect.Repository.MandalaRepository
import com.DeepSoni.vedaconnect.Repository.MantraRepository
import com.DeepSoni.vedaconnect.feature.audio.rememberMantraPlayer
import com.DeepSoni.vedaconnect.ui.theme.VedaTheme

// ✅ Audio player helper
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
    var selectedTab by remember { mutableIntStateOf(0) } // 0 for Mandalas, 1 for Mantra

    val tabs = listOf("Mandalas", "Mantra")

    // Filtered lists based on search query and selected tab
    val filteredMandalas = remember(searchQuery) {
        MandalaRepository.searchMandalas(searchQuery)
    }

    val filteredMantras = remember(searchQuery) {
        MantraRepository.searchMantras(searchQuery)
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
                .padding(horizontal = 24.dp, vertical = 32.dp)
        ) {
            Column(modifier = Modifier.align(Alignment.TopStart)) {
                Text(
                    text = "Explore Mandalas and Suktas 🙏",
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
            placeholder = { Text("Search Mandala, Sukta or Mantra") },
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
                unfocusedContainerColor = Color.White
            ),
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {})
        )

        // 🔸 Tabs
        ScrollableTabRow(
            selectedTabIndex = selectedTab,
            modifier = Modifier.fillMaxWidth(),
            containerColor = VedaTheme.Cream,
            edgePadding = 16.dp,
            indicator = {},
            divider = {}
        ) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTab == index,
                    onClick = { selectedTab = index },
                    modifier = Modifier
                        .padding(horizontal = 4.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(
                            if (selectedTab == index) Color(0xFFF57C00)
                            else Color.White
                        )
                ) {
                    Text(
                        text = title,
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        color = if (selectedTab == index) Color.White else VedaTheme.TextGray,
                        fontSize = 14.sp
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        // 📜 Content List (Mandalas or Mantras)
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            when (selectedTab) {
                0 -> { // Mandalas Tab
                    items(filteredMandalas) { mandala ->
                        MandalaCard(mandala = mandala) {
                            // Navigate to a Mandala detail screen if you create one
                            // For now, it just shows the card
                            // navController.navigate("mandala_detail/${mandala.id}")
                        }
                    }
                }
                1 -> { // Mantra Tab
                    items(filteredMantras) { mantra ->
                        MantraCard(mantra = mantra) {
                            navController.navigate("detail/${mantra.id}")
                        }
                    }
                }
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

// 🪔 Mantra Card with Play Icon (UNCHANGED)
@Composable
fun MantraCard(mantra: Mantra, onClick: () -> Unit) {
    // Only initialize player if audioUrl is not null
    val (isPlaying, togglePlayPause) = if (mantra.audioUrl != null) {
        rememberMantraPlayer(mantra.audioUrl)
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
                    text = mantra.name,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = if (mantra.mandalaNumber != 0) "Mandala ${mantra.mandalaNumber}" else "",
                    fontSize = 14.sp,
                    color = Color.White.copy(alpha = 0.8f)
                )
                if (mantra.suktaNumber != 0) { // Only show sukta and mantra number if they exist
                    Text(
                        text = "Sukta ${mantra.suktaNumber} · Mantra ${mantra.mantraNumber}",
                        fontSize = 14.sp,
                        color = Color.White.copy(alpha = 0.8f)
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = mantra.preview,
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
                    if (mantra.audioUrl != null) {
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

// 🏞️ NEW Mandala Card (No Buttons)
@Composable
fun MandalaCard(mandala: Mandala, onClick: () -> Unit) {
    // Rigveda inspired gradient colors
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
                // 📖 Mandala Info
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
                // No Spacer or Row for buttons here, as requested.
            }
        }
    }
}


// 🧘‍♂️ Detail Screen with Play/Pause Button
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MantraDetailScreen(navController: NavHostController, mantra: Mantra) {
    val (isPlaying, togglePlayPause) = rememberMantraPlayer(mantra.audioUrl)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF7F0))
    ) {
        // 🔙 Top Bar
        TopAppBar(
            title = { Text("Mantra Details") },
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back", tint = Color.Black)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFFFFF7F0),
                titleContentColor = Color.Black
            )
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item { Spacer(modifier = Modifier.height(8.dp)) }

            // 🪔 Header
            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8E0D5)),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = mantra.name,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = VedaTheme.DarkOrange,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Mandala ${mantra.mandalaNumber} · Sukta ${mantra.suktaNumber} · Mantra ${mantra.mantraNumber}",
                            fontSize = 14.sp,
                            color = VedaTheme.TextGray,
                            textAlign = TextAlign.Center
                        )

                        Spacer(modifier = Modifier.height(16.dp))

                        // ▶️ Play Button
                        OutlinedButton(
                            onClick = { togglePlayPause() },
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = VedaTheme.Orange),
                            shape = RoundedCornerShape(12.dp)
                        ) {
                            Text(if (isPlaying) "⏸ Pause" else "▶️ Play", fontSize = 16.sp)
                        }
                    }
                }
            }

            // 🕉 Sanskrit
            item { SectionCard("Sanskrit", mantra.sanskrit, Color.Black) }

            // 📜 Transliteration
            item { SectionCard("Transliteration", mantra.transliteration, Color.Black.copy(alpha = 0.8f)) }

            // 🌼 Translation
            item { SectionCard("Translation", mantra.translation, VedaTheme.TextGray) }

            // Action Buttons
            item {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = { /* Save */ },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = VedaTheme.Orange),
                        shape = RoundedCornerShape(12.dp)
                    ) { Text("🔖 Save", fontSize = 16.sp) }

                    OutlinedButton(
                        onClick = { /* Share */ },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = VedaTheme.Orange),
                        shape = RoundedCornerShape(12.dp)
                    ) { Text("🔗 Share", fontSize = 16.sp) }
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

// 📚 Section Card Reusable Component
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