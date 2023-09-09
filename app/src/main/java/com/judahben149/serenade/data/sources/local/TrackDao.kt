package com.judahben149.serenade.data.sources.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.judahben149.serenade.data.sources.local.entity.TrackEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TrackDao {

    @Upsert
    suspend fun saveTracks(trackEntity: TrackEntity)

    @Query("SELECT * FROM track_entity")
    fun getAllTracks(): Flow<List<TrackEntity>>
}