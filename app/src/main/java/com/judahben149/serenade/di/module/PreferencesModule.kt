package com.judahben149.serenade.di.module

import android.content.Context
import android.content.SharedPreferences
import com.judahben149.serenade.utils.Constants
import com.judahben149.serenade.utils.PrefUtils
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {

    @Provides
    @Singleton
    fun providesSharedPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(Constants.SERENADE_PREFS, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun providesPrefUtils(sharedPreferences: SharedPreferences): PrefUtils {
        return PrefUtils(sharedPreferences)
    }
}