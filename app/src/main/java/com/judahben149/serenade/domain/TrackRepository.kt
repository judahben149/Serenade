package com.judahben149.serenade.domain

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri

interface TrackRepository {

    suspend fun loadCoverBitmap(uri: Uri): Bitmap?
}