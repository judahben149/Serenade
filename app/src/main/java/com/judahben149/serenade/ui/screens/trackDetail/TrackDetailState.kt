package com.judahben149.serenade.ui.screens.trackDetail

import com.judahben149.serenade.domain.models.Track

data class TrackDetailState(
    val trackContentUri: String = "",
    val track: Track = Track(),
    val isPlaying: Boolean = true,
    val playProgress: Int = 0
)
