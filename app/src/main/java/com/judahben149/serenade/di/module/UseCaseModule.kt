package com.judahben149.serenade.di.module

import android.content.Context
import com.judahben149.serenade.data.repository.TrackRepositoryImpl
import com.judahben149.serenade.domain.usecase.GetAlbumArtUseCase
import com.judahben149.serenade.domain.usecase.GetAllTracksUseCase
import com.judahben149.serenade.domain.usecase.QueueManagementUseCase
import com.judahben149.serenade.domain.usecase.SaveAlbumArtUseCase
import com.judahben149.serenade.domain.usecase.SaveTracksToDatabaseUseCase
import com.judahben149.serenade.utils.resourceUtils.MusicContentHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun providesGetAlbumArtUseCase(repository: TrackRepositoryImpl): GetAlbumArtUseCase {
        return GetAlbumArtUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesSaveTracksToDatabaseUseCase(repository: TrackRepositoryImpl): SaveTracksToDatabaseUseCase {
        return SaveTracksToDatabaseUseCase(repository)
    }

    @Provides
    @Singleton
    fun providesGetAllTracksUseCase(
        repository: TrackRepositoryImpl,
        musicContentHelper: MusicContentHelper,
    ): GetAllTracksUseCase {
        return GetAllTracksUseCase(repository, musicContentHelper)
    }

    @Provides
    @Singleton
    fun providesSaveAlbumArtUseCase(
        context: Context,
        getAlbumArtUseCase: GetAlbumArtUseCase,
    ): SaveAlbumArtUseCase {
        return SaveAlbumArtUseCase(context, getAlbumArtUseCase)
    }

    @Provides
    @Singleton
    fun providesQueueManagementUseCase(): QueueManagementUseCase {
        return QueueManagementUseCase()
    }
}