package com.eternia.ai.rpg.core.di

import android.content.Context
import androidx.room.Room
import com.eternia.ai.rpg.core.Constants
import com.eternia.ai.rpg.core.preferences.PreferencesManager
import com.eternia.ai.rpg.database.EterniaDatabase
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    @Singleton
    fun provideDatabase(@BindsInstance context: Context): EterniaDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            EterniaDatabase::class.java,
            Constants.DATABASE_NAME
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun providePreferencesManager(context: Context): PreferencesManager {
        return PreferencesManager(context)
    }
}