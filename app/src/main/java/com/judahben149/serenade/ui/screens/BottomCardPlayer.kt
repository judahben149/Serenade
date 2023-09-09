package com.judahben149.serenade.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.judahben149.serenade.R
import com.judahben149.serenade.ui.components.TrackImageComponent
import com.judahben149.serenade.ui.screens.serenadeHomeScreen.SerenadeHomeScreenState
import com.judahben149.serenade.ui.screens.serenadeHomeScreen.SerenadeHomeScreenViewModel
import com.judahben149.serenade.ui.theme.AssistButtonDefaultColor
import com.judahben149.serenade.ui.theme.Body_Dark
import com.judahben149.serenade.ui.theme.Body_Light
import com.judahben149.serenade.ui.theme.MainButtonDefaultColor
import com.judahben149.serenade.ui.theme.PurpleGrey40
import com.judahben149.serenade.utils.resourceUtils.themeColorSwitcher
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomCardPlayer(
    state: SerenadeHomeScreenState,
    viewModel: SerenadeHomeScreenViewModel,
    sheetState: SheetState,
) {
    val scope = rememberCoroutineScope()

    Box(modifier = Modifier
        .clickable {
            scope.launch {
                sheetState.expand()
            }
        }
    ) {
        CardPlayerSongItem(
            state = state,
            viewModel = viewModel,
            onDismissPlayer = { viewModel.dismissPlayer() },
            onCardClick = { scope.launch { sheetState.expand() } },
            onPreviousClick = { viewModel.playPreviousTrack() },
            onPlayPauseClick = { viewModel.toggleIsPlaying() },
            onNextClick = { viewModel.playNextTrack() },
        )
    }
}

@Composable
fun CardPlayerSongItem(
    state: SerenadeHomeScreenState,
    viewModel: SerenadeHomeScreenViewModel,
    onDismissPlayer: () -> Unit,
    onCardClick: () -> Unit,
    onPreviousClick: () -> Unit,
    onPlayPauseClick: () -> Unit,
    onNextClick: () -> Unit,
) {
    val xAxis = remember { mutableStateOf(0f) }
    val yAxis = remember { mutableStateOf(0f) }

    val cardBackgroundColor = animateColorAsState(
        targetValue = state.uiComponentsState
            .colorPalette
            ?.darkMutedSwatch
            ?.rgb?.let { Color(it) }
            ?: MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.2F),
        animationSpec = tween(durationMillis = 300, easing = LinearEasing),
        label = "Bottom Card player background color"
    )


    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onCardClick() }
            .draggable(
                orientation = Orientation.Vertical,
                state = rememberDraggableState(onDelta = { delta ->
                    yAxis.value = delta
                }),
                onDragStopped = {
                    if (yAxis.value > 0) { // when dragged below
                        onDismissPlayer()
                    } else {
                        onCardClick()
                    }
                }
            )
            .draggable(
                orientation = Orientation.Horizontal,
                state = rememberDraggableState(onDelta = { delta ->
                    xAxis.value = delta
                }),
                onDragStopped = {
                    if (xAxis.value > 0) {
                        viewModel.playPreviousTrack()
                    } else {
                        viewModel.playNextTrack()
                    }
                }
            ),
        colors = CardDefaults.cardColors(
            containerColor = cardBackgroundColor.value
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 8.dp,
                    horizontal = 10.dp
                )
        ) {

            Spacer(modifier = Modifier.width(12.dp))

            TrackImageComponent(state.currentTrack!!)

            Column(
                modifier = Modifier
                    .padding(start = 12.dp)
                    .align(Alignment.CenterVertically)
                    .weight(0.5F, fill = true)
            ) {

                Text(
                    text = state.currentTrack.artistName,
                    fontSize = 13.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = themeColorSwitcher(
                        lightThemeColor = Body_Light,
                        darkThemeColor = Body_Dark
                    ),
                )

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = state.currentTrack.trackName,
                    fontSize = 11.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = themeColorSwitcher(
                        lightThemeColor = Body_Light,
                        darkThemeColor = Body_Dark
                    ),
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            val assistButtonColor = animateColorAsState(
                targetValue = state.uiComponentsState
                    .colorPalette
                    ?.darkMutedSwatch
                    ?.rgb?.let { Color(it) }
                    ?: AssistButtonDefaultColor,
                animationSpec = tween(durationMillis = 300, easing = LinearEasing),
                label = "Bottom Card player background color"
            )

            val mainButtonColor = animateColorAsState(
                targetValue = state.uiComponentsState
                    .colorPalette
                    ?.mutedSwatch
                    ?.rgb?.let { Color(it) }
                    ?: MainButtonDefaultColor,
                animationSpec = tween(durationMillis = 300, easing = LinearEasing),
                label = "Bottom Card player background color"
            )


            // Previous Button
            Button(
                modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.CenterVertically),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = assistButtonColor.value
                ),
                onClick = { onPreviousClick() },
                contentPadding = PaddingValues(1.dp)
            ) {
                Icon(
                    modifier = Modifier.size(22.dp),
                    imageVector = Icons.Filled.SkipPrevious,
                    contentDescription = stringResource(R.string.previous),
                    tint = Color.LightGray
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Play/Pause Button
            Button(
                modifier = Modifier
                    .size(36.dp)
                    .align(Alignment.CenterVertically),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = mainButtonColor.value
                ),
                onClick = { onPlayPauseClick() },
                contentPadding = PaddingValues(1.dp)
            ) {
                Icon(
                    modifier = Modifier.size(24.dp),
                    imageVector = if (state.playerState.isPlaying) {
                        Icons.Filled.Pause
                    } else {
                        Icons.Filled.PlayArrow
                    },
                    contentDescription = stringResource(R.string.play),
                    tint = Color.LightGray
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            // Next Button
            Button(
                modifier = Modifier
                    .size(28.dp)
                    .align(Alignment.CenterVertically),
                shape = CircleShape,
                colors = ButtonDefaults.buttonColors(
                    containerColor = assistButtonColor.value
                ),
                onClick = { onNextClick() },
                contentPadding = PaddingValues(1.dp)
            ) {
                Icon(
                    modifier = Modifier.size(22.dp),
                    imageVector = Icons.Filled.SkipNext,
                    contentDescription = stringResource(R.string.next),
                    tint = Color.LightGray
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun BottomCardPlayerPreview() {
//    BottomCardPlayer()
}
