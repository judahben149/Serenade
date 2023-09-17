package com.judahben149.serenade.ui.screens

import androidx.compose.animation.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.judahben149.serenade.ui.components.TrackListItemComponent
import com.judahben149.serenade.ui.screens.bottomPlayerCard.BottomCardPlayer
import com.judahben149.serenade.ui.screens.serenadeHomeScreen.SerenadeHomeScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LocalScreen(viewModel: SerenadeHomeScreenViewModel) {

    val state by viewModel.state.collectAsState()

    val backgroundOverlayColor =
        state.uiComponentsState.colorPalette?.darkVibrantSwatch?.rgb?.let { Color(it) }
            ?: MaterialTheme.colorScheme.background
    val animatedColor = remember { Animatable(backgroundOverlayColor) }

    val tracks = state.trackList
    val bottomSheetState = rememberModalBottomSheetState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = animatedColor.value
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {

            if (state.isPerformingCaching) {
                LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
            }

            LazyColumn(
                modifier = Modifier
                    .weight(1F),
                state = rememberLazyListState(),
                verticalArrangement = Arrangement.spacedBy(2.dp),
                contentPadding = PaddingValues(horizontal = 12.dp)
            ) {

                items(tracks.size) {
                    TrackListItemComponent(
                        state = state,
                        track = tracks[it],
                        isCurrent = tracks[it].id == state.currentTrack?.id
                    ) { trackId ->
                        viewModel.playTrack(trackId)
                    }
                }
            }

            if (state.isTrackLoaded) {
                BottomCardPlayer(
                    state = state,
                    viewModel = viewModel,
                    sheetState = bottomSheetState
                )
            }
        }
    }
}

    @Preview(showBackground = true)
    @Composable
    fun LocalScreenPreview() {
//    LocalScreen()
    }
