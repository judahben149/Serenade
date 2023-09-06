package com.judahben149.serenade.ui.screens.home

import android.graphics.Bitmap
import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.judahben149.serenade.domain.models.Track
import com.judahben149.serenade.domain.usecase.GetAlbumArtUseCase
import com.judahben149.serenade.utils.Constants
import com.judahben149.serenade.utils.MusicContentHelper
import com.judahben149.serenade.utils.PrefUtils
import com.judahben149.serenade.utils.serializeTrackListToJson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val musicContentHelper: MusicContentHelper,
    private val getAlbumArtUsecase: GetAlbumArtUseCase,
    private val prefs: PrefUtils
) : ViewModel() {

    private val _state = MutableStateFlow(HomeUIState())
    val state = _state.asStateFlow()

    init {
        getAllTracks()
    }

    fun updateTrackType(trackType: TrackType) {
        _state.update { it.copy(trackType = trackType) }
    }

    private fun getAllTracks() {
        viewModelScope.launch(Dispatchers.IO) {
            val trackList = musicContentHelper.retrieveAllTracks()

            if (trackList.size > 0) {
                val actualTrackList: ArrayList<Track> = arrayListOf()

                for (track in trackList) {
//                    val albumArt = getAlbumArt(track.contentUri)

                    val trackDetails = Track(
                        id = track.id,
                        trackName = track.name ?: "Empty name",
                        artistName = track.artist ?: "Empty Artist",
                        duration = track.duration,
                        contentUri = track.contentUri.toString(),
                        albumArt = null
                    )

                    actualTrackList.add(trackDetails)
                }

                withContext(Dispatchers.Main) {
                    _state.update {
                        it.copy(
                            isTrackDataFetched = true,
                            trackList = actualTrackList,
                            isLoading = false
                        )
                    }
                }

            } else {
                _state.update { it.copy(isLoading = false, isTrackDataFetched = true) }
            }
        }
    }

    fun saveNowPlayingQueue(currentTrackId: Long) {
        viewModelScope.launch(Dispatchers.IO) {

            val trackList = _state.value.trackList.toMutableList()
            val currentTrack = trackList.first { it.id == currentTrackId }
            val currentTrackIndex = trackList.indexOf(currentTrack)

            val nowPlayingList = arrayListOf<Track>()

            for (i in trackList.indices) {
                if (i > currentTrackIndex) {
                    nowPlayingList.add(trackList[i])
                }
            }

            val serializedList = nowPlayingList.toList().serializeTrackListToJson()
            prefs.saveString(Constants.NOW_PLAYING_QUEUE, serializedList)
        }
    }

    private suspend fun getAlbumArt(uri: Uri): Bitmap? {
        return getAlbumArtUsecase.getEmbeddedAlbumArt(uri)
    }

    fun toggleLoading(isLoading: Boolean) {
        _state.update { it.copy(isLoading = isLoading) }
    }
}

data class HomeUIState(
    val isLoading: Boolean = true,
    val isTrackDataFetched: Boolean = false,
    val trackList: List<Track> = emptyList(),
    val trackType: TrackType = TrackType.ALL,
)

enum class TrackType {
    ALL,
    MUSIC,
    PODCASTS
}