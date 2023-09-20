package com.judahben149.serenade.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MusicNote
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.palette.graphics.Palette
import com.judahben149.serenade.domain.models.Track
import com.judahben149.serenade.ui.theme.LightGrey
import com.skydoves.landscapist.animation.crossfade.CrossfadePlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.palette.PalettePlugin


@Composable
fun BottomCardImageComponent(
    track: Track,
    onPaletteExtracted:(palette: Palette) -> Unit
) {

    CoilImage(
        imageModel = { track.albumArtUri },
        modifier = Modifier.size(48.dp),
        component = rememberImageComponent {
            +CrossfadePlugin(600)
            +PalettePlugin(
                imageModel = track.albumArtUri,
                useCache = true,
                paletteLoadedListener = {
                    onPaletteExtracted(it)
                }
            )
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
