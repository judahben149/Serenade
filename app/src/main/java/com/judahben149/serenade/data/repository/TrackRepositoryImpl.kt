package com.judahben149.serenade.data.repository

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import com.judahben149.serenade.domain.TrackRepository
import com.judahben149.serenade.utils.MusicContentHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class TrackRepositoryImpl @Inject constructor(
    private val context: Context,
    private val musicContentHelper: MusicContentHelper
): TrackRepository {

    override suspend fun loadCoverBitmap(uri: Uri): Bitmap? {
        return withContext(Dispatchers.IO) {
            musicContentHelper.getAlbumArt(context, uri)
        }
    }
}