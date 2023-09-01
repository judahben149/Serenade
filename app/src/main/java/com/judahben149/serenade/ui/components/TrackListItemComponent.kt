package com.judahben149.serenade.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.MusicNote
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.judahben149.serenade.domain.models.Track
import com.judahben149.serenade.ui.theme.LightGrey
import com.judahben149.serenade.utils.toDurationHHMMSS

@Composable
fun TrackListItemComponent(track: Track, onClick:(trackContentUri: String) -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .background(color = Color.White)
            .clickable { onClick(track.contentUri) }
            .padding(vertical = 10.dp)
    ) {

        Spacer(modifier = Modifier.width(16.dp))
        TrackImageComponent(track)

        Column(modifier = Modifier
            .padding(start = 12.dp)
            .align(Alignment.CenterVertically)
            .weight(0.5F, fill = true)
        ) {

            Text(text = track.trackName, fontSize = 13.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = track.artistName, fontSize = 11.sp, maxLines = 1, overflow = TextOverflow.Ellipsis)

        }

        Text(
            text = track.duration.toDurationHHMMSS(),
            fontSize = 11.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .weight(0.1F, fill = true)
        )

        Icon(
            imageVector = Icons.Rounded.MoreVert,
            contentDescription = null,
            modifier = Modifier
                .padding(end = 6.dp)
                .size(20.dp)
                .alpha(0.4F)
                .align(Alignment.CenterVertically),
            tint = Color.Black
        )
    }
}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun TrackImageComponent(track: Track) {
    val imagePainter = rememberAsyncImagePainter(model = track.contentUri)

    if (track.albumArt == null) { //this is a check that will never be false - intentionally put here - will resolve later
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
    }
    else {

        GlideImage(
            model = imagePainter,
            modifier = Modifier.size(48.dp),
            contentDescription = "Track Thumbnail"
        )
    }
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
    TrackListItemComponent(
        Track().copy(
            trackName = "Yahweh",
            artistName = "Chinedu Ndubueze",
            duration = 707
        )
    ) {}
}
