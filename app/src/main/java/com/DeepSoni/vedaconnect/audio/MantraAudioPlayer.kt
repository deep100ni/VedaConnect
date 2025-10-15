package com.DeepSoni.vedaconnect.audio

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import java.io.IOException

@Composable
fun rememberMantraPlayer(audioUrl: String?): Pair<Boolean, () -> Unit> {
    var isPlaying by remember { mutableStateOf(false) }
    var mediaPlayer: MediaPlayer? by remember { mutableStateOf(null) }

    fun togglePlayPause() {
        if (audioUrl.isNullOrEmpty()) return

        if (isPlaying) {
            mediaPlayer?.pause()
            isPlaying = false
        } else {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer().apply {
                    setAudioAttributes(
                        AudioAttributes.Builder()
                            .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                            .setUsage(AudioAttributes.USAGE_MEDIA)
                            .build()
                    )
                    try {
                        setDataSource(audioUrl)
                        prepareAsync() // Use prepareAsync for network streams
                        setOnPreparedListener {
                            start()
                            isPlaying = true
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                        // Handle exceptions, perhaps by showing a Toast message
                    }
                }
            } else {
                mediaPlayer?.start()
                isPlaying = true
            }
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