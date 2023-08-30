package com.judahben149.serenade.di.module

import android.content.Context
import com.judahben149.serenade.utils.MusicContentHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesAppContext(@ApplicationContext appContext: Context): Context {
        return appContext
    }

    @Provides
    @Singleton
    fun providesMusicContentHelper(context: Context): MusicContentHelper {
        return MusicContentHelper(context)
    }
}