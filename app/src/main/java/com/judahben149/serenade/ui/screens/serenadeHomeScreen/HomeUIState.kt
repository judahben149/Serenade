package com.judahben149.serenade.ui.screens.serenadeHomeScreen

import androidx.palette.graphics.Palette
import com.judahben149.serenade.domain.models.Track

data class HomeUIState(
    val isCachingComplete: Boolean = false,
    val isPerformingCaching: Boolean = false,
    val trackList: List<Track> = emptyList(),
    val isTrackLoaded: Boolean = false,
    val currentTrack: Track? = null,
    val playerState: PlayerState = PlayerState(),
    val uiComponentsState: UIComponentsState = UIComponentsState(),
    val bottomBarState: BottomBarState = BottomBarState()
)

data class PlayerState(
    val isPlaying: Boolean = true,
    val playProgress: Int = 0,
)

data class UIComponentsState(
    val colorPalette: Palette? = null,
)

data class BottomBarState(
    val selected: SelectedBottomBarItem = SelectedBottomBarItem.HOME
)

enum class SelectedBottomBarItem {
    HOME,
    LIBRARY,
    LOCAL,
    SETTINGS
}