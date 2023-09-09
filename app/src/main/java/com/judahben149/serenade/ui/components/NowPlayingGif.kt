package com.judahben149.serenade.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import coil.request.ImageRequest
import com.judahben149.serenade.utils.appUtils.isApiLevelEqualToOrAbove
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun NowPlayingGif(data: Any?) {
    val context = LocalContext.current

    val imageLoader = ImageLoader.Builder(context)
        .components {
            if (isApiLevelEqualToOrAbove(9)) {
                add(ImageDecoderDecoder.Factory())
            } else {
                add(GifDecoder.Factory())
            }
        }.build()

    CoilImage(
        imageRequest = { ImageRequest.Builder(context).data(data).build() },
        imageLoader = { imageLoader },
        success = { _, painter ->
            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier.size(42.dp),
            )
        }
    )
}

@Preview(showBackground = true)
@Composable
fun NowPlayingGifPreview() {
    NowPlayingGif(null)
}
