package com.judahben149.serenade.domain.usecase

import android.graphics.Bitmap
import android.net.Uri
import com.judahben149.serenade.data.repository.TrackRepositoryImpl
import javax.inject.Inject

class GetAlbumArtUseCase @Inject constructor(private val repository: TrackRepositoryImpl) {

    suspend fun getEmbeddedAlbumArt(uri: Uri): Bitmap? = repository.loadCoverBitmap(uri)
}