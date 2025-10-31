package com.DeepSoni.vedaconnect.audio

import android.media.AudioAttributes
import android.media.MediaPlayer
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.IOException

enum class PlayerState {
    IDLE,
    LOADING,
    PLAYING,
    PAUSED
}

// Data class to hold the state
data class PlayerStatus(
    val state: PlayerState = PlayerState.IDLE,
    val url: String? = null
)

object MantraPlayerManager {
    private var mediaPlayer: MediaPlayer? = null
    private val _playerStatus = MutableStateFlow(PlayerStatus())
    val playerStatus = _playerStatus.asStateFlow()

    fun togglePlayPause(audioUrl: String) {
        if (_playerStatus.value.url == audioUrl && _playerStatus.value.state == PlayerState.PLAYING) {
            pause()
        } else if (_playerStatus.value.url == audioUrl && _playerStatus.value.state == PlayerState.PAUSED) {
            resume()
        } else {
            play(audioUrl)
        }
    }

    private fun play(audioUrl: String) {
        stop()
        _playerStatus.value = PlayerStatus(state = PlayerState.LOADING, url = audioUrl)
        mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            try {
                setDataSource(audioUrl)
                prepareAsync()
                setOnPreparedListener {
                    start()
                    _playerStatus.value = PlayerStatus(state = PlayerState.PLAYING, url = audioUrl)
                }
                setOnCompletionListener {
                    stop()
                }
                setOnErrorListener { _, _, _ ->
                    stop()
                    true
                }
            } catch (e: IOException) {
                e.printStackTrace()
                stop()
            }
        }
    }

    private fun pause() {
        mediaPlayer?.pause()
        _playerStatus.value = _playerStatus.value.copy(state = PlayerState.PAUSED)
    }

    private fun resume() {
        mediaPlayer?.start()
        _playerStatus.value = _playerStatus.value.copy(state = PlayerState.PLAYING)
    }

    fun stop() {
        mediaPlayer?.release()
        mediaPlayer = null
        _playerStatus.value = PlayerStatus()
    }
}

@Composable
fun rememberMantraPlayer(audioUrl: String): Pair<PlayerState, () -> Unit> {
    val playerStatus by MantraPlayerManager.playerStatus.collectAsState()

    val isThisAudio = playerStatus.url == audioUrl
    val currentState = if (isThisAudio) playerStatus.state else PlayerState.IDLE

    val onToggle: () -> Unit = { MantraPlayerManager.togglePlayPause(audioUrl) }

    return Pair(currentState, onToggle)
}