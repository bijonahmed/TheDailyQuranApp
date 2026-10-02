package com.example.player

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.example.data.model.Ayah
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch

data class AudioPlayerState(
    val isPlaying: Boolean = false,
    val isBuffering: Boolean = false,
    val currentSurahNumber: Int = 1,
    val currentAyahNumber: Int = 1,
    val currentAyahTextArabic: String = "",
    val currentAyahTranslation: String = "",
    val currentPositionMs: Long = 0,
    val durationMs: Long = 0,
    val isRepeatEnabled: Boolean = false,
    val errorMessage: String? = null
)

class QuranAudioPlayer(context: Context) {

    private val appContext = context.applicationContext
    private var exoPlayer: ExoPlayer? = null
    private val scope = CoroutineScope(Dispatchers.Main + Job())
    private var progressJob: Job? = null

    private val _state = MutableStateFlow(AudioPlayerState())
    val state: StateFlow<AudioPlayerState> = _state.asStateFlow()

    private var currentPlaylist: List<Ayah> = emptyList()
    private var currentIndex: Int = 0

    init {
        initPlayer()
    }

    private fun initPlayer() {
        if (exoPlayer == null) {
            exoPlayer = ExoPlayer.Builder(appContext).build().apply {
                addListener(object : Player.Listener {
                    override fun onIsPlayingChanged(isPlaying: Boolean) {
                        _state.value = _state.value.copy(isPlaying = isPlaying)
                        if (isPlaying) {
                            startProgressUpdates()
                        } else {
                            progressJob?.cancel()
                        }
                    }

                    override fun onPlaybackStateChanged(playbackState: Int) {
                        when (playbackState) {
                            Player.STATE_BUFFERING -> {
                                _state.value = _state.value.copy(isBuffering = true, errorMessage = null)
                            }
                            Player.STATE_READY -> {
                                _state.value = _state.value.copy(
                                    isBuffering = false,
                                    durationMs = exoPlayer?.duration?.coerceAtLeast(0) ?: 0,
                                    errorMessage = null
                                )
                            }
                            Player.STATE_ENDED -> {
                                handleTrackEnded()
                            }
                            Player.STATE_IDLE -> {
                                _state.value = _state.value.copy(isBuffering = false)
                            }
                        }
                    }

                    override fun onPlayerError(error: PlaybackException) {
                        _state.value = _state.value.copy(
                            isPlaying = false,
                            isBuffering = false,
                            errorMessage = "Audio unavailable or offline. Please check connection."
                        )
                    }
                })
            }
        }
    }

    private fun startProgressUpdates() {
        progressJob?.cancel()
        progressJob = scope.launch {
            while (isActive) {
                exoPlayer?.let { player ->
                    _state.value = _state.value.copy(
                        currentPositionMs = player.currentPosition.coerceAtLeast(0),
                        durationMs = player.duration.coerceAtLeast(0)
                    )
                }
                delay(300)
            }
        }
    }

    fun playAyah(
        surahNumber: Int,
        ayah: Ayah,
        playlist: List<Ayah> = listOf(ayah),
        reciterBaseUrl: String = "https://everyayah.com/data/Alafasy_128kbps/"
    ) {
        initPlayer()
        currentPlaylist = playlist
        currentIndex = playlist.indexOfFirst { it.numberInSurah == ayah.numberInSurah }.coerceAtLeast(0)

        val formattedSurah = String.format("%03d", surahNumber)
        val formattedAyah = String.format("%03d", ayah.numberInSurah)
        val audioUrl = if (ayah.audioUrl.isNotEmpty() && !ayah.audioUrl.contains("everyayah")) {
            ayah.audioUrl
        } else {
            "$reciterBaseUrl$formattedSurah$formattedAyah.mp3"
        }

        _state.value = _state.value.copy(
            currentSurahNumber = surahNumber,
            currentAyahNumber = ayah.numberInSurah,
            currentAyahTextArabic = ayah.textArabic,
            currentAyahTranslation = ayah.translationEnglish,
            isBuffering = true,
            errorMessage = null
        )

        try {
            val mediaItem = MediaItem.fromUri(audioUrl)
            exoPlayer?.apply {
                stop()
                clearMediaItems()
                setMediaItem(mediaItem)
                prepare()
                play()
            }
        } catch (e: Exception) {
            _state.value = _state.value.copy(
                isBuffering = false,
                errorMessage = "Failed to load audio: ${e.localizedMessage}"
            )
        }
    }

    fun playPause() {
        exoPlayer?.let { player ->
            if (player.isPlaying) {
                player.pause()
            } else {
                player.play()
            }
        }
    }

    fun seekTo(positionMs: Long) {
        exoPlayer?.seekTo(positionMs)
    }

    fun toggleRepeat() {
        val newRepeat = !_state.value.isRepeatEnabled
        _state.value = _state.value.copy(isRepeatEnabled = newRepeat)
    }

    fun nextAyah(surahNumber: Int, reciterBaseUrl: String = "https://everyayah.com/data/Alafasy_128kbps/") {
        if (currentPlaylist.isNotEmpty() && currentIndex + 1 < currentPlaylist.size) {
            currentIndex++
            playAyah(surahNumber, currentPlaylist[currentIndex], currentPlaylist, reciterBaseUrl)
        }
    }

    fun previousAyah(surahNumber: Int, reciterBaseUrl: String = "https://everyayah.com/data/Alafasy_128kbps/") {
        if (currentPlaylist.isNotEmpty() && currentIndex - 1 >= 0) {
            currentIndex--
            playAyah(surahNumber, currentPlaylist[currentIndex], currentPlaylist, reciterBaseUrl)
        }
    }

    private fun handleTrackEnded() {
        if (_state.value.isRepeatEnabled) {
            exoPlayer?.seekTo(0)
            exoPlayer?.play()
        } else if (currentPlaylist.isNotEmpty() && currentIndex + 1 < currentPlaylist.size) {
            currentIndex++
            val next = currentPlaylist[currentIndex]
            playAyah(_state.value.currentSurahNumber, next, currentPlaylist)
        } else {
            _state.value = _state.value.copy(isPlaying = false, currentPositionMs = 0)
        }
    }

    fun stop() {
        progressJob?.cancel()
        exoPlayer?.stop()
        _state.value = _state.value.copy(isPlaying = false, currentPositionMs = 0)
    }

    fun release() {
        progressJob?.cancel()
        exoPlayer?.release()
        exoPlayer = null
    }
}
