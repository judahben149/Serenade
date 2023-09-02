package com.judahben149.serenade.ui.screens.trackDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.judahben149.serenade.domain.PlayerEvent
import com.judahben149.serenade.domain.SerenadePlayer
import com.judahben149.serenade.domain.models.Track
import com.judahben149.serenade.utils.MusicContentHelper
import com.judahben149.serenade.utils.logThis
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TrackDetailViewModel @Inject constructor(
    private val musicContentHelper: MusicContentHelper,
    private val serenadePlayer: SerenadePlayer,
) : ViewModel() {

    private var _state: MutableStateFlow<TrackDetailState> = MutableStateFlow(TrackDetailState())
    val state: StateFlow<TrackDetailState> = _state.asStateFlow()

    init {
        collectPlayerEvents()
    }

    fun updateTrackContentUri(trackContentUri: String) {
        _state.update { it.copy(trackContentUri = trackContentUri) }
        getTrackDetails()
        playTrack()
    }

    private fun getTrackDetails() {
        musicContentHelper
            .getTrackDetails(_state.value.trackContentUri)
            ?.let { trackContent ->
                _state.update { state ->
                    state.copy(
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

    private fun playTrack() {
        serenadePlayer.performPlayNewTrackAction(_state.value.track)
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
                when(playerEvent) {
                    is PlayerEvent.CurrentPosition -> {
                        _state.update { it.copy(playProgress = playerEvent.position) }
                        "Updated position from Player".logThis("TAGG")
                    }
                }
            }
        }
    }

    fun toggleIsPlaying() {
        serenadePlayer.performPlayPauseAction(_state.value.isPlaying)
        _state.update { it.copy(isPlaying = !_state.value.isPlaying) }
    }
}

