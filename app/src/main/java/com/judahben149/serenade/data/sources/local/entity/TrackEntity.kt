package com.judahben149.serenade.data.sources.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "track_entity")
data class TrackEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long,
    val trackMediaId: Long,
    val trackName: String,
    val artistName: String,
    val thumbnail: String,
    val duration: Int,
    val contentUri: String,
    val albumArtUri: String? = null,
)
