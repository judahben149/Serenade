package com.judahben149.serenade.domain.usecase

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import androidx.core.net.toUri
import com.judahben149.serenade.utils.resourceUtils.TrackContent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import javax.inject.Inject

class SaveAlbumArtUseCase @Inject constructor(
    private val context: Context,
    private val getAlbumArtUseCase: GetAlbumArtUseCase,
) {

    suspend fun saveBitmapToExternalCache(track: TrackContent): Result<Uri> {
        return withContext(Dispatchers.IO) {
            kotlin.runCatching {

                val directory = File(context.externalCacheDir, "album_art")
                val file = File(directory, "${track.id}.jpg")
                if (!directory.exists()) {
                    directory.mkdirs()
                }

                // if file exists, return the uri
                if (file.exists()) {
                    return@runCatching file.toUri()
                }

                val out = FileOutputStream(file)
                getAlbumArtUseCase
                    .getEmbeddedAlbumArt(track.contentUri)
                    ?.compress(Bitmap.CompressFormat.JPEG, 10, out)

                out.flush()
                out.close()
                file.toUri()
            }
        }
    }
}