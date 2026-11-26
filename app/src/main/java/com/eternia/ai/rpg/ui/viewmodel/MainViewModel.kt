package com.eternia.ai.rpg.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eternia.ai.rpg.ai_providers.AiProviderManager
import com.eternia.ai.rpg.character_cards.CharacterCardParser
import com.eternia.ai.rpg.companions.CompanionManager
import com.eternia.ai.rpg.core.Result
import com.eternia.ai.rpg.lorebooks.LorebookScanner
import com.eternia.ai.rpg.memory.MemoryManager
import com.eternia.ai.rpg.personas.PersonaManager
import com.eternia.ai.rpg.rpg_engine.RpgEngine
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val aiProviderManager: AiProviderManager,
    private val personaManager: PersonaManager,
    private val characterCardParser: CharacterCardParser,
    private val lorebookScanner: LorebookScanner,
    private val memoryManager: MemoryManager,
    private val rpgEngine: RpgEngine,
    private val companionManager: CompanionManager
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoadingFlow = _isLoading.asStateFlow()

    private val _recentConversations = MutableStateFlow(emptyList<ConversationSummary>())
    val recentConversationsFlow = _recentConversations.asStateFlow()

    private val _recentPersonas = MutableStateFlow(emptyList<PersonaSummary>())
    val recentPersonasFlow = _recentPersonas.asStateFlow()

    private val _recentCharacters = MutableStateFlow(emptyList<CharacterSummary>())
    val recentCharactersFlow = _recentCharacters.asStateFlow()

    private val _recentNovels = MutableStateFlow(emptyList<NovelSummary>())
    val recentNovelsFlow = _recentNovels.asStateFlow()

    private val _recentVisualNovels = MutableStateFlow(emptyList<VisualNovelSummary>())
    val recentVisualNovelsFlow = _recentVisualNovels.asStateFlow()

    private val _recentWorlds = MutableStateFlow(emptyList<WorldSummary>())
    val recentWorldsFlow = _recentWorlds.asStateFlow()

    init {
        loadDashboardData()
    }

    private fun loadDashboardData() {
        viewModelScope.launch {
            _isLoading.value = true
            // TODO: Load actual data from repositories
            _isLoading.value = false
        }
    }

    fun refreshDashboard() {
        loadDashboardData()
    }

    data class ConversationSummary(
        val id: String,
        val title: String,
        val lastMessage: String,
        val timestamp: Long,
        val personaName: String?
    )

    data class PersonaSummary(
        val id: String,
        val name: String,
        val description: String,
        val isFavorite: Boolean,
        val isNsfw: Boolean
    )

    data class CharacterSummary(
        val id: String,
        val name: String,
        val description: String,
        val isFavorite: Boolean,
        val isNsfw: Boolean
    )

    data class NovelSummary(
        val id: String,
        val title: String,
        val author: String,
        val progress: Float
    )

    data class VisualNovelSummary(
        val id: String,
        val title: String,
        val coverImageUrl: String?,
        val progress: Float
    )

    data class WorldSummary(
        val id: String,
        val name: String,
        val description: String
    )
}