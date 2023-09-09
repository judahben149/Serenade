package com.judahben149.serenade.ui.components

import androidx.compose.animation.Animatable
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun BackgroundOverlay(color: Color) {
    val colorAnimated = remember{ Animatable(color) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = colorAnimated.value
            )
    )
}

@Preview(showBackground = true)
@Composable
fun BackgroundOverlayPreview() {
    BackgroundOverlay(Color(0xFFFFFFFF))
}
