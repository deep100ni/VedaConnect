package com.DeepSoni.vedaconnect.feature.audio

import android.media.MediaPlayer
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext

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
