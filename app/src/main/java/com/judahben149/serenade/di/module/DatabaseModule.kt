package com.judahben149.serenade.di.module

import android.content.Context
import androidx.room.Room
import com.judahben149.serenade.data.sources.local.SerenadeDatabase
import com.judahben149.serenade.data.sources.local.TrackDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesSerenadeDatabase(context: Context): SerenadeDatabase {
        return Room.databaseBuilder(
            context,
            SerenadeDatabase::class.java,
            "SERENADE_DATABASE"
        ).build()
    }

    @Provides
    @Singleton
    fun providesTrackDao(database: SerenadeDatabase): TrackDao {
        return database.trackDao()
    }
}