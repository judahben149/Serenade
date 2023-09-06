package com.judahben149.serenade.ui.screens.trackDetail

import androidx.core.net.toUri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.judahben149.serenade.domain.PlayerEvent
import com.judahben149.serenade.domain.SerenadePlayer
import com.judahben149.serenade.domain.models.Track
import com.judahben149.serenade.domain.usecase.GetAlbumArtUseCase
import com.judahben149.serenade.utils.Constants
import com.judahben149.serenade.utils.MusicContentHelper
import com.judahben149.serenade.utils.PrefUtils
import com.judahben149.serenade.utils.deserializeTrackListFromJson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class TrackDetailViewModel @Inject constructor(
    private val musicContentHelper: MusicContentHelper,
    private val serenadePlayer: SerenadePlayer,
    private val prefs: PrefUtils,
    private val getAlbumArtUseCase: GetAlbumArtUseCase
) : ViewModel() {

    private var _state: MutableStateFlow<TrackDetailState> = MutableStateFlow(TrackDetailState())
    val state: StateFlow<TrackDetailState> = _state.asStateFlow()

    init {
        collectPlayerEvents()
    }

    fun updateTrackContentUri(trackContentUri: String) {
        getTrackDetails(trackContentUri)
        playTrack()
        getEmbeddedAlbumArt()
        getNowPlayingQueue()
    }

    private fun playTrack() = serenadePlayer.performPlayNewTrackAction(_state.value.track)

    fun playPreviousTrack() = serenadePlayer.playPreviousTrack()

    fun playNextTrack() = serenadePlayer.playNextTrack()


    private fun getTrackDetails(trackContentUri: String) {
        musicContentHelper
            .getTrackDetails(trackContentUri)
            ?.let { trackContent ->
                _state.update { state ->
                    state.copy(
                        trackContentUri = trackContentUri,
                        track = Track(
                            id = trackContent.id,
                            trackName = trackContent.name ?: "No Name",
                            artistName = trackContent.artist ?: "No Artist Name",
                            duration = trackContent.duration,
                            contentUri = trackContent.contentUri.toString(),
                        )
                    )
                }
            }
    }

    private fun getNowPlayingQueue() {
        viewModelScope.launch(Dispatchers.IO) {
            val nowPlayingQueue = prefs.fetchString(Constants.NOW_PLAYING_QUEUE)

            if (nowPlayingQueue.isEmpty()) return@launch

            val queue = nowPlayingQueue.deserializeTrackListFromJson()

            withContext(Dispatchers.Main) {
                serenadePlayer.prepareQueue(queue)
            }
        }
    }

    fun updatePlayProgress(value: Float) {
        _state.update { it.copy(playProgress = value.toInt()) }
    }

    fun seekToNewProgress() {
        serenadePlayer.seekTo(_state.value.playProgress)
    }

    private fun collectPlayerEvents() {
        viewModelScope.launch {
            serenadePlayer.playerEvent.collectLatest { playerEvent ->
                when (playerEvent) {
                    is PlayerEvent.CurrentPosition -> {
                        _state.update { it.copy(playProgress = playerEvent.position) }
                    }

                    is PlayerEvent.TrackChanged -> {
                        if (playerEvent.currentTrackUri != "null") {
                            getTrackDetails(playerEvent.currentTrackUri)
                        }
                    }
                }
            }
        }
    }

    fun getEmbeddedAlbumArt() {
        viewModelScope.launch {
            val albumArt = getAlbumArtUseCase.getEmbeddedAlbumArt(_state.value.trackContentUri.toUri())

            albumArt?.let { artBitmap ->
                _state.update { it.copy(track = _state.value.track.copy(albumArt = artBitmap)) }
            }
        }
    }

    fun toggleIsPlaying() {
        serenadePlayer.performPlayPauseAction(_state.value.isPlaying)
        _state.update { it.copy(isPlaying = !_state.value.isPlaying) }
    }
}

