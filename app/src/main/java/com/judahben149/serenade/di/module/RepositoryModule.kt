package com.judahben149.serenade.di.module

import android.content.Context
import com.judahben149.serenade.data.repository.TrackRepositoryImpl
import com.judahben149.serenade.utils.MusicContentHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun providesTrackRepositoryImpl(
        context: Context,
        musicContentHelper: MusicContentHelper,
    ): TrackRepositoryImpl {
        return TrackRepositoryImpl(context, musicContentHelper)
    }
}