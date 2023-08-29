package com.judahben149.serenade.ui.screens.home

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(): ViewModel() {

    private val _state = MutableStateFlow(HomeUIState())
    val state = _state.asStateFlow()

    fun updateTrackType(trackType: TrackType) {
        _state.update { it.copy(trackType = trackType) }
    }
}

data class HomeUIState(
    val trackType: TrackType = TrackType.ALL
)

enum class TrackType {
    ALL,
    MUSIC,
    PODCASTS
}