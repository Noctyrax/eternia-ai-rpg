package com.eternia.ai.rpg.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import com.eternia.ai.rpg.designsystem.components.*
import com.eternia.ai.rpg.designsystem.theme.*
import com.eternia.ai.rpg.ui.viewmodel.MainViewModel
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.navController
import androidx.navigation.compose.rememberNavController

@Composable
fun HomeScreen(
    viewModel: MainViewModel = viewModel(),
    navController: androidx.navigation.NavHostController = rememberNavController()
) {
    val recentConversations by viewModel.recentConversationsFlow.collectAsStateWithLifecycle()
    val recentPersonas by viewModel.recentPersonasFlow.collectAsStateWithLifecycle()
    val recentCharacters by viewModel.recentCharactersFlow.collectAsStateWithLifecycle()
    val recentNovels by viewModel.recentNovelsFlow.collectAsStateWithLifecycle()
    val recentVisualNovels by viewModel.recentVisualNovelsFlow.collectAsStateWithLifecycle()
    val recentWorlds by viewModel.recentWorldsFlow.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoadingFlow.collectAsStateWithLifecycle()

    Column(
        modifier = androidx.compose.foundation.layout.Modifier
            .fillMaxSize()
            .padding(FantasySpacing.md),
        verticalArrangement = Arrangement.spacedBy(FantasySpacing.lg)
    ) {
        // Header
        Row(
            modifier = androidx.compose.foundation.layout.Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Text(
                text = "ETERNIA AI RPG",
                style = FantasyTypography.headlineMedium.copy(fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
            )
            FantasyIconButton(
                onClick = { navController.navigate("settings") },
                icon = { androidx.compose.material.icons.Icons.Default.Settings }
            )
        }

        if (isLoading) {
            androidx.compose.material3.CircularProgressIndicator(
                modifier = androidx.compose.foundation.layout.Modifier
                    .fillMaxWidth()
                    .wrapContentSize(androidx.compose.ui.Alignment.Center)
                    .padding(FantasySpacing.xl)
            )
        } else {
            // Quick Actions
            FantasyCard(
                contentPadding = androidx.compose.foundation.layout.PaddingValues(FantasySpacing.md)
            ) {
                Text(text = "Actions rapides", style = FantasyTypography.titleMedium)
                androidx.compose.foundation.layout.Spacer(modifier = androidx.compose.foundation.layout.Modifier.height(FantasySpacing.md))
                Row(
                    modifier = androidx.compose.foundation.layout.Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(FantasySpacing.sm)
                ) {
                    FantasyFilledButton(
                        text = "Nouvelle Persona",
                        onClick = { navController.navigate("personas/persona_editor") },
                        leadingIcon = { androidx.compose.material.icons.Icons.Default.PersonAdd },
                        modifier = androidx.compose.foundation.layout.Modifier.weight(1f)
                    )
                    FantasyOutlinedButton(
                        text = "Nouveau Personnage",
                        onClick = { navController.navigate("characters/character_editor") },
                        leadingIcon = { androidx.compose.material.icons.Icons.Default.AutoAwesome },
                        modifier = androidx.compose.foundation.layout.Modifier.weight(1f)
                    )
                    FantasyOutlinedButton(
                        text = "Nouvelle Aventure",
                        onClick = { navController.navigate("chat/chat_session/new") },
                        leadingIcon = { androidx.compose.material.icons.Icons.Default.Chat },
                        modifier = androidx.compose.foundation.layout.Modifier.weight(1f)
                    )
                }
            }

            // Recent Conversations
            if (recentConversations.isNotEmpty()) {
                SectionCard(
                    title = "Conversations récentes",
                    onMoreClick = { navController.navigate("chat/chat_list") },
                    itemCount = recentConversations.size
                ) {
                    LazyColumn(
                        modifier = androidx.compose.foundation.layout.Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(FantasySpacing.sm)
                    ) {
                        items(recentConversations.take(5)) { conversation ->
                            ConversationCard(conversation = conversation) {
                                navController.navigate("chat/chat_session/${conversation.id}")
                            }
                        }
                    }
                }
            }

            // Recent Personas
            if (recentPersonas.isNotEmpty()) {
                SectionCard(
                    title = "Personas récentes",
                    onMoreClick = { navController.navigate("personas/persona_list") },
                    itemCount = recentPersonas.size
                ) {
                    Row(
                        modifier = androidx.compose.foundation.layout.Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(FantasySpacing.sm)
                    ) {
                        recentPersonas.take(4).forEach { persona ->
                            FantasyCharacterCard(
                                name = persona.name,
                                subtitle = persona.description.takeIf { it.isNotBlank() },
                                rarity = if (persona.isFavorite) ItemRarity.LEGENDARY else ItemRarity.COMMON,
                                modifier = androidx.compose.foundation.layout.Modifier.weight(1f)
                            ) {
                                navController.navigate("personas/persona_detail/${persona.id}")
                            }
                        }
                    }
                }
            }

            // Recent Characters
            if (recentCharacters.isNotEmpty()) {
                SectionCard(
                    title = "Personnages récents",
                    onMoreClick = { navController.navigate("characters/character_list") },
                    itemCount = recentCharacters.size
                ) {
                    Row(
                        modifier = androidx.compose.foundation.layout.Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(FantasySpacing.sm)
                    ) {
                        recentCharacters.take(4).forEach { character ->
                            FantasyCharacterCard(
                                name = character.name,
                                subtitle = character.description.takeIf { it.isNotBlank() },
                                rarity = when {
                                    character.isNsfw -> ItemRarity.EPIC
                                    character.isFavorite -> ItemRarity.LEGENDARY
                                    else -> ItemRarity.COMMON
                                },
                                modifier = androidx.compose.foundation.layout.Modifier.weight(1f)
                            ) {
                                navController.navigate("characters/character_detail/${character.id}")
                            }
                        }
                    }
                }
            }

            // Recent Novels
            if (recentNovels.isNotEmpty()) {
                SectionCard(
                    title = "Romans récents",
                    onMoreClick = { navController.navigate("reader/novel_list") },
                    itemCount = recentNovels.size
                ) {
                    LazyColumn(
                        modifier = androidx.compose.foundation.layout.Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(FantasySpacing.sm)
                    ) {
                        items(recentNovels.take(5)) { novel ->
                            NovelCard(novel = novel) {
                                navController.navigate("reader/novel_reader/${novel.id}")
                            }
                        }
                    }
                }
            }

            // Recent Visual Novels
            if (recentVisualNovels.isNotEmpty()) {
                SectionCard(
                    title = "Visual Novels récents",
                    onMoreClick = { navController.navigate("visual_novel/vn_list") },
                    itemCount = recentVisualNovels.size
                ) {
                    Row(
                        modifier = androidx.compose.foundation.layout.Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(FantasySpacing.sm)
                    ) {
                        recentVisualNovels.take(4).forEach { vn ->
                            FantasyCharacterCard(
                                name = vn.title,
                                subtitle = "Visual Novel",
                                rarity = ItemRarity.RARE,
                                modifier = androidx.compose.foundation.layout.Modifier.weight(1f)
                            ) {
                                navController.navigate("visual_novel/vn_player/${vn.id}")
                            }
                        }
                    }
                }
            }

            // Recent Worlds
            if (recentWorlds.isNotEmpty()) {
                SectionCard(
                    title = "Mondes récents",
                    onMoreClick = { navController.navigate("worlds/world_list") },
                    itemCount = recentWorlds.size
                ) {
                    Row(
                        modifier = androidx.compose.foundation.layout.Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(FantasySpacing.sm)
                    ) {
                        recentWorlds.take(4).forEach { world ->
                            FantasyCharacterCard(
                                name = world.name,
                                subtitle = world.description.takeIf { it.isNotBlank() },
                                rarity = ItemRarity.UNCOMMON,
                                modifier = androidx.compose.foundation.layout.Modifier.weight(1f)
                            ) {
                                navController.navigate("worlds/world_detail/${world.id}")
                            }
                        }
                    }
                }
            }

            // Empty State
            if (recentConversations.isEmpty() && recentPersonas.isEmpty() && recentCharacters.isEmpty() &&
                recentNovels.isEmpty() && recentVisualNovels.isEmpty() && recentWorlds.isEmpty()) {
                FantasyCard(
                    contentPadding = androidx.compose.foundation.layout.PaddingValues(FantasySpacing.xl),
                    modifier = androidx.compose.foundation.layout.Modifier.fillMaxWidth()
                ) {
                    Column(
                        modifier = androidx.compose.foundation.layout.Modifier.fillMaxWidth(),
                        horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(FantasySpacing.md)
                    ) {
                        androidx.compose.material.icons.Icons.Default.AutoAwesome
                        Text(
                            text = "Bienvenue dans ETERNIA AI RPG",
                            style = FantasyTypography.headlineSmall,
                            textAlign = androidx.compose.ui.text.TextAlign.Center
                        )
                        Text(
                            text = "Créez votre première persona, personnage ou aventure pour commencer",
                            style = FantasyTypography.bodyMedium,
                            textAlign = androidx.compose.ui.text.TextAlign.Center
                        )
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(FantasySpacing.md)
                        ) {
                            FantasyFilledButton(
                                text = "Nouvelle Persona",
                                onClick = { navController.navigate("personas/persona_editor") },
                                leadingIcon = { androidx.compose.material.icons.Icons.Default.PersonAdd }
                            )
                            FantasyOutlinedButton(
                                text = "Nouveau Personnage",
                                onClick = { navController.navigate("characters/character_editor") },
                                leadingIcon = { androidx.compose.material.icons.Icons.Default.AutoAwesome }
                            )
                        }
                    }
                }
            }
        }
    }
}