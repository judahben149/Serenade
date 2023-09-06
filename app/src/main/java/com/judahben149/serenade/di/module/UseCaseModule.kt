package com.judahben149.serenade.di.module

import com.judahben149.serenade.data.repository.TrackRepositoryImpl
import com.judahben149.serenade.domain.usecase.GetAlbumArtUseCase
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
}