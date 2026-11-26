package com.eternia.ai.rpg.di

import android.content.Context
import com.eternia.ai.rpg.database.EterniaDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): EterniaDatabase {
        return EterniaDatabase.getInstance(context)
    }
}