package com.judahben149.serenade.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun PlayPauseButtonComponent(isPlaying: Boolean, onCLick: () -> Unit) {

    Box {
        if (isPlaying) {
            PauseButton() { onCLick() }
        } else {
            PlayButton() { onCLick() }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PlayPauseButtonComponentPreview() {
    PlayPauseButtonComponent(true, {})
}

