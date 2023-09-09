package com.judahben149.serenade.domain

import android.graphics.Bitmap
import android.net.Uri
import com.judahben149.serenade.data.sources.local.entity.TrackEntity
import kotlinx.coroutines.flow.Flow

interface TrackRepository {

    suspend fun loadCoverBitmap(uri: Uri): Bitmap?

    suspend fun saveTracks(trackEntity: TrackEntity)

    suspend fun getAllTracks(): Flow<List<TrackEntity>>
}