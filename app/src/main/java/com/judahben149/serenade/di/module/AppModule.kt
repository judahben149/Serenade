package com.judahben149.serenade.di.module

import android.content.Context
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.session.MediaSession
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.judahben149.serenade.domain.SerenadePlayer
import com.judahben149.serenade.domain.SessionManager
import com.judahben149.serenade.domain.models.SerenadeColor
import com.judahben149.serenade.domain.models.SerenadeSwatch
import com.judahben149.serenade.utils.appUtils.PrefUtils
import com.judahben149.serenade.utils.resourceUtils.MusicContentHelper
import com.judahben149.serenade.utils.serializationUtils.SerenadeColorSerializer
import com.judahben149.serenade.utils.serializationUtils.SerenadeSwatchSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
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

    @Provides
    @Singleton
    fun providesExoPlayer(context: Context): Player {
        return ExoPlayer.Builder(context).apply {
            setHandleAudioBecomingNoisy(true)
        }.build()
    }

    @Provides
    @Singleton
    fun providesMediaSession(context: Context, player: Player): MediaSession {
        return MediaSession.Builder(context, player).build()
    }

    @Provides
    @Singleton
    fun providesSerenadePlayer(player: Player): SerenadePlayer {
        return SerenadePlayer(player)
    }

    @Provides
    @Singleton
    @Named("serenade_gson")
    fun providesGson(): Gson {
        return GsonBuilder()
            .registerTypeAdapter(SerenadeSwatch::class.java, SerenadeSwatchSerializer())
            .registerTypeAdapter(SerenadeColor::class.java, SerenadeColorSerializer())
            .create()
    }

    @Provides
    @Singleton
    fun providesSessionManager(prefUtils: PrefUtils): SessionManager {
        return SessionManager(prefUtils)
    }
}