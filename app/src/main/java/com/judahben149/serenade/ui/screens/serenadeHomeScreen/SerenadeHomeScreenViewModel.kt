package com.judahben149.serenade.ui.screens.serenadeHomeScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.palette.graphics.Palette
import com.judahben149.serenade.domain.PlayerEvent
import com.judahben149.serenade.domain.SerenadePlayer
import com.judahben149.serenade.domain.SessionManager
import com.judahben149.serenade.domain.mappers.toTrack
import com.judahben149.serenade.domain.models.Track
import com.judahben149.serenade.domain.usecase.GetAlbumArtUseCase
import com.judahben149.serenade.domain.usecase.GetAllTracksUseCase
import com.judahben149.serenade.domain.usecase.QueueManagementUseCase
import com.judahben149.serenade.domain.usecase.SaveAlbumArtUseCase
import com.judahben149.serenade.domain.usecase.SaveTracksToDatabaseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SerenadeHomeScreenViewModel @Inject constructor(
    private val getTracksUseCase: GetAllTracksUseCase,
    private val saveTracksUseCase: SaveTracksToDatabaseUseCase,
    private val saveAlbumArtUseCase: SaveAlbumArtUseCase,
    private val getAlbumArtUseCase: GetAlbumArtUseCase,
    private val queueUseCase: QueueManagementUseCase,
    private val serenadePlayer: SerenadePlayer,
    private val sessionManager: SessionManager,
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUIState())
    val state = _state.asStateFlow()

    init {
        if (!sessionManager.getIsCachingComplete()) {
            cacheAllTracks()
        }

        getAllTracksFromDatabase()
    }

    private fun cacheAllTracks() {
        _state.update { it.copy(isPerformingCaching = true) }

        viewModelScope.launch(Dispatchers.IO) {
            val trackContentList = getTracksUseCase.getAllTracks()

            if (trackContentList.size > 0) {
                val tracks = arrayListOf<Track>()

                for (trackContent in trackContentList) {
                    val albumArtUri = saveAlbumArtUseCase
                        .saveBitmapToExternalCache(trackContent)
                        .getOrNull()?.toString() ?: "null"

                    val track = trackContent.toTrack(albumArtUri = albumArtUri)
                    tracks.add(track)
                    saveTracksUseCase.saveTracks(track)
                }

                _state.update {
                    it.copy(
                        isPerformingCaching = false,
                        isCachingComplete = true
                    )
                }

                sessionManager.saveCachingComplete()
            } else {
                _state.update {
                    it.copy(
                        isPerformingCaching = false,
                        isCachingComplete = true
                    )
                }
            }
        }
    }


    private fun getAllTracksFromDatabase() {
        viewModelScope.launch(Dispatchers.IO) {
            getTracksUseCase.getAllTracksFromDatabase().collect { trackList ->
                _state.update {
                    it.copy(
                        trackList = trackList
                    )
                }
            }
        }
    }

    private fun collectPlayerEvents() {
        viewModelScope.launch {
            serenadePlayer.playerEvent.collectLatest { playerEvent ->
                when (playerEvent) {
                    is PlayerEvent.CurrentPosition -> {
                        _state.update {
                            it.copy(
                                playerState = _state.value.playerState.copy(
                                    playProgress = playerEvent.position
                                )
                            )
                        }
                    }

                    is PlayerEvent.TrackChanged -> {
                        if (playerEvent.currentTrackUri != "null") {
                            val trackList = _state.value.trackList

                            val newTrack = trackList.firstOrNull { track ->
                                track.contentUri == playerEvent.currentTrackUri
                            }

                            newTrack?.let { track ->
                                _state.update { it.copy(currentTrack = track) }
                            }
                        }
                    }
                }
            }
        }
    }

    fun playTrack(id: Long) {
        serenadePlayer.clearQueue()
        val track = _state.value.trackList.firstOrNull { track -> track.id == id }

        track?.let { currentTrack ->
            _state.update {
                it.copy(
                    isTrackLoaded = true,
                    currentTrack = currentTrack,
                    playerState = _state.value.playerState.copy(isPlaying = true)
                )
            }
            serenadePlayer.performPlayNewTrackAction(currentTrack)
        }

        collectPlayerEvents()
        prepareQueue()
    }

    fun toggleIsPlaying() {
        serenadePlayer.performPlayPauseAction(_state.value.playerState.isPlaying)
        _state.update {
            it.copy(
                playerState = _state.value.playerState.copy(
                    isPlaying = !_state.value.playerState.isPlaying
                )
            )
        }
    }

    fun playPreviousTrack() = serenadePlayer.playPreviousTrack()

    fun playNextTrack() = serenadePlayer.playNextTrack()

    fun updatePlayProgress(value: Float) {
        _state.update {
            it.copy(
                playerState = _state.value.playerState.copy(
                    playProgress = value.toInt()
                )
            )
        }
    }

    fun seekToNewProgress() {
        serenadePlayer.seekTo(_state.value.playerState.playProgress)
    }

    private fun prepareQueue() {
        viewModelScope.launch {
            val nowPlayingQueue = queueUseCase.prepareNowPlayingQueue(
                _state.value.currentTrack?.trackMediaId!!,
                _state.value.trackList
            )

            serenadePlayer.prepareQueue(nowPlayingQueue)
        }
    }

    fun updateColorPalette(colorPalette: Palette) {
        _state.update {
            it.copy(
                uiComponentsState = _state.value.uiComponentsState.copy(
                    colorPalette = colorPalette
                )
            )
        }
    }

    fun dismissPlayer() {
        serenadePlayer.dismissPlayer()
        _state.update { it.copy(currentTrack = null, isTrackLoaded = false) }
    }

    fun updateSelectedBottomBarItem(selectedBottomBarItem: SelectedBottomBarItem) {
        _state.update {
            it.copy(
                bottomBarState = _state.value.bottomBarState.copy(
                    selected = selectedBottomBarItem
                )
            )
        }
    }
}