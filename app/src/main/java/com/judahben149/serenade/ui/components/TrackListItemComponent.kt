package com.judahben149.serenade.ui.components

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.MusicNote
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import com.judahben149.serenade.domain.models.Track
import com.judahben149.serenade.ui.screens.serenadeHomeScreen.HomeUIState
import com.judahben149.serenade.ui.theme.Body_Dark
import com.judahben149.serenade.ui.theme.Body_Light
import com.judahben149.serenade.ui.theme.LightGrey
import com.judahben149.serenade.utils.resourceUtils.themeColorSwitcher
import com.judahben149.serenade.utils.resourceUtils.toDurationHHMMSS
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent

@Composable
fun TrackListItemComponent(
    state: HomeUIState,
    track: Track,
    isCurrent: Boolean = false,
    onClick: (trackId: Long) -> Unit,
) {

    val backgroundColor =
        animateColorAsState(
            targetValue = if (isCurrent) {
                state.uiComponentsState
                    .colorPalette
                    ?.darkMutedSwatch
                    ?.rgb?.let { Color(it) }
                    ?.copy(alpha = 0.3f)
                    ?: MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = 0.2F)
            } else {
                MaterialTheme.colorScheme.background
            },
            animationSpec = tween(durationMillis = 300, easing = LinearEasing),
            label = "Item playing background color"
        )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .background(
                color = backgroundColor.value
            )
            .clickable { onClick(track.id) }
            .padding(vertical = 10.dp)
    ) {

        Spacer(modifier = Modifier.width(16.dp))

        TrackImageComponent(track)

        Column(
            modifier = Modifier
                .padding(start = 12.dp)
                .align(Alignment.CenterVertically)
                .weight(0.5F, fill = true)
        ) {

            Text(
                text = track.trackName,
                fontSize = 12.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = themeColorSwitcher(
                    lightThemeColor = Body_Light,
                    darkThemeColor = Body_Dark
                ),
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = track.artistName,
                fontSize = 11.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = themeColorSwitcher(
                    lightThemeColor = Body_Light,
                    darkThemeColor = Body_Dark
                ),
            )

        }

        Text(
            text = track.duration.toDurationHHMMSS(),
            fontSize = 11.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(0.1F, fill = true),
            color = themeColorSwitcher(
                lightThemeColor = Body_Light,
                darkThemeColor = Body_Dark
            ),
        )

        Icon(
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = null,
            modifier = Modifier
                .padding(end = 6.dp)
                .size(20.dp)
                .alpha(0.4F)
                .align(Alignment.CenterVertically),
            tint = themeColorSwitcher(
                lightThemeColor = Color.Black,
                darkThemeColor = Color.LightGray
            )
        )
    }
}

@Composable
fun TrackImageComponent(track: Track) {

    CoilImage(
        imageModel = { track.albumArtUri },
        modifier = Modifier.size(48.dp),
        component = rememberImageComponent {
            +CrossfadePlugin(600)
        },
        loading = {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(color = LightGrey, shape = RoundedCornerShape(5.dp))
            ) {
                Icon(
                    imageVector = Icons.Rounded.MusicNote,
                    contentDescription = null,
                    modifier = Modifier
                        .alpha(0.4F)
                        .align(Alignment.Center)
                )
            }
        },
        failure = {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(color = LightGrey, shape = RoundedCornerShape(5.dp))
            ) {
                Icon(
                    imageVector = Icons.Rounded.MusicNote,
                    contentDescription = null,
                    modifier = Modifier
                        .alpha(0.4F)
                        .align(Alignment.Center)
                )
            }
        },
        success = { _, painter ->
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(shape = RoundedCornerShape(5.dp))
            ) {
                Image(
                    painter = painter,
                    contentDescription = "Track Album Art",
                    contentScale = ContentScale.Crop,
                    alignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    )
}

@Preview
@Composable
fun TrackImageComponentPreview() {
    TrackImageComponent(
        Track().copy(
            trackName = "Yahweh",
            artistName = "Chinedu Ndubueze",
            duration = 707
        )
    )
}

@Preview(showBackground = true)
@Composable
fun TrackListItemComponentPreview() {
//    TrackListItemComponent(
//        Track().copy(
//            trackName = "Yahweh",
//            artistName = "Chinedu Ndubueze",
//            duration = 707
//        )
//    ) {}
}
