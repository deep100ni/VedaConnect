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
import androidx.compose.material.icons.outlined.BookmarkBorder
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.DeepSoni.vedaconnect.Data.Mantra
import com.DeepSoni.vedaconnect.R
import com.DeepSoni.vedaconnect.Repository.MantraRepository
import com.DeepSoni.vedaconnect.ui.theme.VedaTheme
import androidx.compose.material.icons.filled.Search


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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentScreen(navController: NavHostController) {
    var searchQuery by remember { mutableStateOf("") }
    var selectedTab by remember { mutableIntStateOf(0) }

    val tabs = listOf("All", "Mandala 1", "Mandala 2", "Mandala 3", "Mandala 5", "Mandala 9", "Mandala 10")

    val filteredMantras = remember(searchQuery, selectedTab) {
        val searchResults = MantraRepository.searchMantras(searchQuery)
        when (selectedTab) {
            0 -> searchResults
            1 -> searchResults.filter { it.mandalaNumber == 1 }
            2 -> searchResults.filter { it.mandalaNumber == 2 }
            3 -> searchResults.filter { it.mandalaNumber == 3 }
            4 -> searchResults.filter { it.mandalaNumber == 5 }
            5 -> searchResults.filter { it.mandalaNumber == 9 }
            6 -> searchResults.filter { it.mandalaNumber == 10 }
            else -> searchResults
        }
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
                .background(headerOrangeGradient, shape = RoundedCornerShape(bottomStart = 24.dp, bottomEnd = 24.dp))
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

        // 📜 Mantra List
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(filteredMantras) { mantra ->
                MantraCard(mantra = mantra) {
                    navController.navigate("detail/${mantra.id}")
                }
            }
            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

// 🪔 Mantra Card with Play Icon
@Composable
fun MantraCard(mantra: Mantra, onClick: () -> Unit) {
    val (isPlaying, togglePlayPause) = rememberMantraPlayer(mantra.audioResId)

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // ▶️ Play Button
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape)
                    .clickable { togglePlayPause() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.PlayArrow,
                    contentDescription = "Play Mantra",
                    tint = if (isPlaying) VedaTheme.Orange else Color.Gray,
                    modifier = Modifier.size(35.dp)
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // 📖 Mantra Info
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = mantra.name,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
                Text(
                    text = "Mandala ${mantra.mandalaNumber} · Sukta ${mantra.suktaNumber}",
                    fontSize = 14.sp,
                    color = VedaTheme.TextGray
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = mantra.preview,
                    fontSize = 12.sp,
                    color = VedaTheme.TextGray.copy(alpha = 0.7f),
                    maxLines = 1
                )
            }

            // 🔖 Bookmark Placeholder
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.BookmarkBorder,
                    contentDescription = "Bookmark",
                    tint = Color.Gray,
                    modifier = Modifier.size(28.dp)
                )
            }
        }
    }
}

// 🧘‍♂️ Detail Screen with Play/Pause Button
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MantraDetailScreen(navController: NavHostController, mantra: Mantra) {
    val (isPlaying, togglePlayPause) = rememberMantraPlayer(mantra.audioResId)

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
