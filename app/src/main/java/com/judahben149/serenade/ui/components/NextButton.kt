package com.judahben149.serenade.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.SkipNext
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
import com.judahben149.serenade.ui.theme.AssistButtonDefaultColor

@Composable
fun NextButton(state: SerenadeHomeScreenState, onClick: () -> Unit) {
    Button(
        modifier = Modifier.size(48.dp),
        shape = CircleShape,
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = (
                    state.uiComponentsState
                        .colorPalette
                        ?.darkMutedSwatch
                        ?.rgb?.let { Color(it) } ?: AssistButtonDefaultColor
                    ).copy(alpha = 0.6f)
        ),
        contentPadding = PaddingValues(1.dp)
    ) {
        Icon(
            modifier = Modifier.size(32.dp),
            imageVector = Icons.Rounded.SkipNext,
            contentDescription = stringResource(R.string.previous),
            tint = Color.LightGray
        )
    }
}

@Preview(showBackground = true)
@Composable
fun NextButtonPreview() {
//    NextButton {}
}
