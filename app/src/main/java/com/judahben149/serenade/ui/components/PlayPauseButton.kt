package com.judahben149.serenade.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.judahben149.serenade.R
import com.judahben149.serenade.ui.screens.serenadeHomeScreen.SerenadeHomeScreenState
import com.judahben149.serenade.ui.theme.MainButtonDefaultColor

@Composable
fun PlayButton(
    state: SerenadeHomeScreenState,
    onClick: () -> Unit,
) {
    Button(
        modifier = Modifier.size(72.dp),
        shape = CircleShape,
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = (
                    state.uiComponentsState
                        .colorPalette
                        ?.mutedSwatch
                        ?.rgb?.let { Color(it) } ?: MainButtonDefaultColor
                    ).copy(alpha = 0.6f)
        ),
        contentPadding = PaddingValues(1.dp)
    ) {
        Icon(
            modifier = Modifier.size(36.dp),
            imageVector = Icons.Filled.PlayArrow,
            contentDescription = stringResource(R.string.play),
            tint = Color.LightGray
        )
    }
}

@Composable
fun PauseButton(
    state: SerenadeHomeScreenState,
    onClick: () -> Unit,
) {
    Button(
        modifier = Modifier.size(72.dp),
        shape = CircleShape,
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = (
                    state.uiComponentsState
                        .colorPalette
                        ?.mutedSwatch
                        ?.rgb?.let { Color(it) } ?: MainButtonDefaultColor
                    ).copy(alpha = 0.6f)
        ),
        contentPadding = PaddingValues(1.dp)
    ) {
        Icon(
            modifier = Modifier.size(36.dp),
            imageVector = Icons.Filled.Pause,
            contentDescription = stringResource(R.string.pause),
            tint = Color.LightGray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PlayButtonPreview() {
//    PlayButton {}
}

@Preview(showBackground = true)
@Composable
fun PauseButtonPreview() {
//    PauseButton {}
}
