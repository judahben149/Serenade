package com.judahben149.serenade.ui.screens.trackDetail

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MusicNote
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.judahben149.serenade.ui.components.NextButton
import com.judahben149.serenade.ui.components.PlayPauseButtonComponent
import com.judahben149.serenade.ui.components.PreviousButton
import com.judahben149.serenade.ui.screens.serenadeHomeScreen.SerenadeHomeScreenState
import com.judahben149.serenade.ui.screens.serenadeHomeScreen.SerenadeHomeScreenViewModel
import com.judahben149.serenade.ui.theme.AssistButtonDefaultColor
import com.judahben149.serenade.ui.theme.Body_Dark
import com.judahben149.serenade.ui.theme.Body_Light
import com.judahben149.serenade.ui.theme.DarkGrey
import com.judahben149.serenade.ui.theme.LightGrey
import com.judahben149.serenade.ui.theme.MainButtonDefaultColor
import com.judahben149.serenade.utils.resourceUtils.themeColorSwitcher
import com.judahben149.serenade.utils.resourceUtils.toDurationHHMMSS
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.palette.PalettePlugin

@Composable
fun TrackDetailPlayerSheet(
    state: SerenadeHomeScreenState,
    viewModel: SerenadeHomeScreenViewModel,
) {

    val assistButtonColor = animateColorAsState(
        targetValue = state.uiComponentsState
            .colorPalette
            ?.darkMutedSwatch
            ?.rgb?.let { Color(it) }
            ?: AssistButtonDefaultColor,
        animationSpec = tween(durationMillis = 300, easing = LinearEasing),
        label = "Assist button background color"
    )

    val mainButtonColor = animateColorAsState(
        targetValue = state.uiComponentsState
            .colorPalette
            ?.mutedSwatch
            ?.rgb?.let { Color(it) }
            ?: MainButtonDefaultColor,
        animationSpec = tween(durationMillis = 300, easing = LinearEasing),
        label = "Main button background color"
    )

    val cardBackgroundColor = animateColorAsState(
        targetValue = state.uiComponentsState
            .colorPalette
            ?.darkMutedSwatch
            ?.rgb?.let { Color(it) }
            ?: MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.2F),
        animationSpec = tween(durationMillis = 300, easing = LinearEasing),
        label = "Bottom Card player background color"
    )


    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = cardBackgroundColor.value)
            .padding(horizontal = 16.dp)
    ) {

        Spacer(modifier = Modifier.height(48.dp))

        Box(
            modifier = Modifier
                .size(280.dp)
                .background(
                    color = themeColorSwitcher(
                        lightThemeColor = LightGrey,
                        darkThemeColor = DarkGrey
                    ),
                    shape = RoundedCornerShape(8.dp)
                )
        ) {

            CoilImage(
                imageModel = { state.currentTrack?.albumArtUri },
                modifier = Modifier
                    .align(Alignment.Center),
                component = rememberImageComponent {
                    +CrossfadePlugin(600)
                    +PalettePlugin(
                        imageModel = state.currentTrack?.albumArtUri,
                        useCache = true,
                        paletteLoadedListener = {
                            viewModel.updateColorPalette(it)
                        }
                    )
                },
                success = { _, painter ->
                    Image(
                        painter = painter,
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxSize()
                            .clip(RoundedCornerShape(5.dp)),
                        contentScale = ContentScale.Crop
                    )
                },
                loading = {
                    Icon(
                        imageVector = Icons.Rounded.MusicNote,
                        contentDescription = null,
                        modifier = Modifier
                            .size(56.dp)
                            .alpha(0.4F)
                            .align(Alignment.Center)
                    )
                },
                failure = {
                    Icon(
                        imageVector = Icons.Rounded.MusicNote,
                        contentDescription = null,
                        modifier = Modifier
                            .size(56.dp)
                            .alpha(0.4F)
                            .align(Alignment.Center)
                    )
                }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = state.currentTrack?.trackName ?: "No track loaded",
            fontSize = 15.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(horizontal = 24.dp),
            color = themeColorSwitcher(
                lightThemeColor = Body_Light,
                darkThemeColor = Body_Dark
            ),
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = state.currentTrack?.artistName ?: "No track loaded",
            fontSize = 13.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(horizontal = 24.dp),
            color = themeColorSwitcher(
                lightThemeColor = Body_Light,
                darkThemeColor = Body_Dark
            ),
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {

            Text(
                text = state.playerState.playProgress.toDurationHHMMSS(),
                fontSize = 12.sp,
                modifier = Modifier.weight(1F, true),
                textAlign = TextAlign.Center,
                color = themeColorSwitcher(
                    lightThemeColor = Body_Light,
                    darkThemeColor = Body_Dark
                ),
            )


            Slider(
                value = state.playerState.playProgress.toFloat(),
                valueRange = 0F..(state.currentTrack?.duration?.toFloat() ?: 0f),
                onValueChange = { newValue ->
                    viewModel.updatePlayProgress(newValue)
                },
                onValueChangeFinished = { viewModel.seekToNewProgress() },
                modifier = Modifier
                    .weight(8F, true)
                    .padding(horizontal = 8.dp),
                colors = SliderDefaults.colors(
                    thumbColor = mainButtonColor.value,
                    activeTrackColor = mainButtonColor.value
                )
            )

            Text(
                text = state.currentTrack?.duration?.toDurationHHMMSS() ?: "0:00",
                fontSize = 12.sp,
                modifier = Modifier.weight(1F, true),
                textAlign = TextAlign.Center,
                color = themeColorSwitcher(
                    lightThemeColor = Body_Light,
                    darkThemeColor = Body_Dark
                ),
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            PreviousButton(state) { viewModel.playPreviousTrack() }

            Spacer(modifier = Modifier.width(16.dp))

            PlayPauseButtonComponent(
                state = state,
                isPlaying = state.playerState.isPlaying
            ) {
                viewModel.toggleIsPlaying()
            }

            Spacer(modifier = Modifier.width(16.dp))

            NextButton(state) { viewModel.playNextTrack() }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TrackDetailPlayerSheetPreview() {
//    TrackDetailPlayerSheet()
}
