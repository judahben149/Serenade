package com.judahben149.serenade.ui.screens.trackDetail

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
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.judahben149.serenade.ui.components.NextButton
import com.judahben149.serenade.ui.components.PlayPauseButtonComponent
import com.judahben149.serenade.ui.components.PreviousButton
import com.judahben149.serenade.ui.theme.LightGrey
import com.judahben149.serenade.utils.decodeUri
import com.judahben149.serenade.utils.logThis
import com.judahben149.serenade.utils.toDurationHHMMSS
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent

@Composable
fun TrackDetailScreen(
    trackId: Long,
    encodedTrackContentUri: String,
    viewModel: TrackDetailViewModel = hiltViewModel(),
    onNavigate: () -> Unit,
) {
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(key1 = encodedTrackContentUri) {
        viewModel.updateTrackContentUri(encodedTrackContentUri.decodeUri())
    }

    SideEffect {
        state.trackContentUri.logThis("jhh")
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White)
            .padding(horizontal = 16.dp)
    ) {
        Spacer(modifier = Modifier.height(64.dp))

        Box(
            modifier = Modifier
                .size(280.dp)
                .background(color = LightGrey, shape = RoundedCornerShape(8.dp))
        ) {

            CoilImage(
                imageModel = { state.track.albumArt },
                modifier = Modifier
                    .align(Alignment.Center),
                component = rememberImageComponent { +CrossfadePlugin(300) },
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
            text = state.track.trackName,
            fontSize = 16.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = state.track.artistName,
            fontSize = 13.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {

            Text(
                text = state.playProgress.toDurationHHMMSS(),
                fontSize = 12.sp,
                modifier = Modifier.weight(1F, true),
                textAlign = TextAlign.Center
            )

            Slider(
                value = state.playProgress.toFloat(),
                valueRange = 0F..state.track.duration.toFloat(),
                onValueChange = { newValue ->
                    viewModel.updatePlayProgress(newValue)
                },
                onValueChangeFinished = { viewModel.seekToNewProgress() },
                modifier = Modifier
                    .weight(8F, true)
                    .padding(horizontal = 12.dp)
            )

            Text(
                text = state.track.duration.toDurationHHMMSS(),
                fontSize = 12.sp,
                modifier = Modifier.weight(1F, true),
                textAlign = TextAlign.Center
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            PreviousButton { viewModel.playPreviousTrack() }

            Spacer(modifier = Modifier.width(16.dp))

            PlayPauseButtonComponent(isPlaying = state.isPlaying) {
                "Button Clicked Is Playing - ${state.isPlaying}".logThis("TAG")
                viewModel.toggleIsPlaying()
            }

            Spacer(modifier = Modifier.width(16.dp))

            NextButton { viewModel.playNextTrack() }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun TrackDetailScreenPreview() {
    TrackDetailScreen(0L, "") {}
}
