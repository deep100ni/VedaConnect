package com.DeepSoni.vedaconnect.feature.suktas

import android.media.MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.DeepSoni.vedaconnect.data.Sukta

private object VedaThemeColors {
    val DarkOrange = Color(0xFFE65100)
    val Orange = Color(0xFFF57C00)
    val TextGray = Color.Gray
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SuktaDetailScreen(navController: NavHostController, sukta: Sukta) {
    val (isPlaying, togglePlayPause) = if (sukta.audioUrl != null) {
        rememberMantraPlayer(sukta.audioUrl)
    } else {
        remember { false to {} }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFFFF7F0))
    ) {
        TopAppBar(
            title = { Text("Sukta Details") },
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

            item {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = Color(0xFFE8E0D5)),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Column(
                        modifier = Modifier.fillMaxWidth().padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = sukta.name,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = VedaThemeColors.DarkOrange,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Mandala ${sukta.mandalaNumber} · Sukta ${sukta.suktaNumber}",
                            fontSize = 14.sp,
                            color = VedaThemeColors.TextGray,
                            textAlign = TextAlign.Center
                        )

                        if (sukta.audioUrl != null) {
                            Spacer(modifier = Modifier.height(16.dp))
                            OutlinedButton(
                                onClick = { togglePlayPause() },
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = VedaThemeColors.Orange),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text(if (isPlaying) "⏸ Pause" else "▶️ Play", fontSize = 16.sp)
                            }
                        }
                    }
                }
            }

            item { SectionCard("Sanskrit", sukta.sanskrit, Color.Black) }
            item { SectionCard("Transliteration", sukta.transliteration, Color.Black.copy(alpha = 0.8f)) }
            item { SectionCard("Translation", sukta.translation, VedaThemeColors.TextGray) }

            item {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    OutlinedButton(
                        onClick = { /* TODO: Save action */ },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = VedaThemeColors.Orange),
                        shape = RoundedCornerShape(12.dp)
                    ) { Text("🔖 Save", fontSize = 16.sp) }

                    OutlinedButton(
                        onClick = { /* TODO: Share action */ },
                        modifier = Modifier.weight(1f),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = VedaThemeColors.Orange),
                        shape = RoundedCornerShape(12.dp)
                    ) { Text("🔗 Share", fontSize = 16.sp) }
                }
            }

            item { Spacer(modifier = Modifier.height(16.dp)) }
        }
    }
}

@Composable
private fun SectionCard(title: String, content: String, contentColor: Color = Color.Black) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = VedaThemeColors.Orange.copy(alpha = 0.8f),
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

// Re-usable audio player logic
@Composable
private fun rememberMantraPlayer(audioUrl: String): Pair<Boolean, () -> Unit> {
    var isPlaying by remember { mutableStateOf(false) }

    val mediaPlayer = remember(audioUrl) {
        MediaPlayer().apply {
            setDataSource(audioUrl)
            prepareAsync()
            setOnCompletionListener { isPlaying = false }
        }
    }

    DisposableEffect(Unit) {
        onDispose {
            mediaPlayer.release()
        }
    }

    val togglePlayPause: () -> Unit = {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
        } else {
            mediaPlayer.start()
        }
        isPlaying = mediaPlayer.isPlaying
    }

    return isPlaying to togglePlayPause
}