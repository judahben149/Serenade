package com.judahben149.serenade.data.sources.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.judahben149.serenade.data.sources.local.entity.TrackEntity

@Database(entities = [TrackEntity::class], exportSchema = false, version = 1)
abstract class SerenadeDatabase : RoomDatabase() {
    abstract fun trackDao(): TrackDao
}