package com.judahben149.serenade.data.repository

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import com.judahben149.serenade.data.sources.local.SerenadeDatabase
import com.judahben149.serenade.data.sources.local.entity.TrackEntity
import com.judahben149.serenade.domain.TrackRepository
import com.judahben149.serenade.utils.resourceUtils.MusicContentHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import javax.inject.Inject


class TrackRepositoryImpl @Inject constructor(
    private val context: Context,
    private val database: SerenadeDatabase,
    private val musicContentHelper: MusicContentHelper,
) : TrackRepository {

    override suspend fun loadCoverBitmap(uri: Uri): Bitmap? {
        return withContext(Dispatchers.IO) {
            musicContentHelper.getAlbumArt(context, uri)
        }
    }

    override suspend fun saveTracks(trackEntity: TrackEntity) {
        database.trackDao().saveTracks(trackEntity)
    }

    override suspend fun getAllTracks(): Flow<List<TrackEntity>> {
        return database.trackDao().getAllTracks()
    }
}