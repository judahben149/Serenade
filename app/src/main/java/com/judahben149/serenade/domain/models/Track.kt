package com.judahben149.serenade.domain.models

import android.graphics.Bitmap

data class Track(
    val id: Long = -1L,
    val trackMediaId: Long = -1L,
    val trackName: String = "",
    val artistName: String = "",
    val thumbnail: String = "",
    val duration: Int = 0,
    val contentUri: String = "",
    val albumArt: Bitmap? = null,
    val albumArtUri: String? = null,
)
