package com.judahben149.serenade.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.judahben149.serenade.ui.screens.serenadeHomeScreen.HomeUIState

@Composable
fun PlayPauseButtonComponent(
    state: HomeUIState,
    isPlaying: Boolean,
    onCLick: () -> Unit
) {

    Box {
        if (isPlaying) {
            PauseButton(state) { onCLick() }
        } else {
            PlayButton(state) { onCLick() }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlayPauseButtonComponentPreview() {
//    PlayPauseButtonComponent(true) {}
}

