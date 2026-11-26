package com.eternia.ai.rpg.di

import com.eternia.ai.rpg.ai_providers.AiProviderManager
import com.eternia.ai.rpg.character_cards.CharacterCardParser
import com.eternia.ai.rpg.companions.CompanionManager
import com.eternia.ai.rpg.lorebooks.LorebookScanner
import com.eternia.ai.rpg.memory.MemoryManager
import com.eternia.ai.rpg.personas.PersonaManager
import com.eternia.ai.rpg.rpg_engine.RpgEngine
import com.eternia.ai.rpg.settings.KeyManager
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
    fun provideAiProviderManager(): AiProviderManager = AiProviderManager()

    @Provides
    @Singleton
    fun providePersonaManager(): PersonaManager = PersonaManager()

    @Provides
    @Singleton
    fun provideCharacterCardParser(): CharacterCardParser = CharacterCardParser()

    @Provides
    @Singleton
    fun provideLorebookScanner(): LorebookScanner = LorebookScanner()

    @Provides
    @Singleton
    fun provideMemoryManager(): MemoryManager = MemoryManager()

    @Provides
    @Singleton
    fun provideRpgEngine(): RpgEngine = RpgEngine()

    @Provides
    @Singleton
    fun provideCompanionManager(): CompanionManager = CompanionManager()

    @Provides
    @Singleton
    fun provideKeyManager(): KeyManager = KeyManager()
}