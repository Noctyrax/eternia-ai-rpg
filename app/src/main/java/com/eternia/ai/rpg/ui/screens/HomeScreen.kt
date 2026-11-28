package com.eternia.ai.rpg.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.eternia.ai.rpg.designsystem.theme.EterniaTheme
import com.eternia.ai.rpg.designsystem.theme.FantasySpacing

@Composable
fun HomeScreen(
    onPersonaClick: (String) -> Unit = {},
    onCharacterClick: (String) -> Unit = {},
    onConversationClick: (String) -> Unit = {},
    onNovelClick: (String) -> Unit = {},
    onNewPersona: () -> Unit = {},
    onNewCharacter: () -> Unit = {},
    onNewConversation: () -> Unit = {}
) {
    val recentConversations by remember { mutableStateOf(listOf<String>()) }
    val recentPersonas by remember { mutableStateOf(listOf<String>()) }

    EterniaTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            androidx.compose.material3.TopAppBar(
                title = {
                    Text(
                        text = "ETERNIA AI RPG",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                        )
                    )
                },
                colors = MaterialTheme.colorScheme.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = FantasySpacing.md, top = FantasySpacing.sm)
            ) {
                // Quick Actions
                item {
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(bottom = FantasySpacing.md),
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        ),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        Column(modifier = Modifier.padding(16.dp)) {
                            Text(
                                text = "Actions rapides",
                                style = MaterialTheme.typography.titleMedium
                            )
                            androidx.compose.foundation.layout.Spacer(
                                modifier = Modifier.height(FantasySpacing.sm)
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(FantasySpacing.sm)
                            ) {
                                FantasyOutlinedButton(
                                    text = "Nouvelle Persona",
                                    icon = Icons.Default.PersonAdd,
                                    onClick = onNewPersona,
                                    modifier = Modifier.weight(1f)
                                )
                                FantasyOutlinedButton(
                                    text = "Nouveau Personnage",
                                    icon = Icons.Default.AutoAwesome,
                                    onClick = onNewCharacter,
                                    modifier = Modifier.weight(1f)
                                )
                                FantasyOutlinedButton(
                                    text = "Nouvelle Aventure",
                                    icon = Icons.Default.Chat,
                                    onClick = onNewConversation,
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                }

                // Recent Personas
                item {
                    if (recentPersonas.isNotEmpty()) {
                        Column(modifier = Modifier.fillMaxWidth().padding(bottom = FantasySpacing.md)) {
                            Text(
                                text = "Personas récentes",
                                style = MaterialTheme.typography.titleMedium
                            )
                            androidx.compose.foundation.layout.Spacer(
                                modifier = Modifier.height(FantasySpacing.sm)
                            )
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(FantasySpacing.sm)
                            ) {
                                recentPersonas.take(4).forEach { personaId ->
                                    FantasyCharacterCard(
                                        name = "Persona",
                                        rarity = com.eternia.ai.rpg.designsystem.components.ItemRarity.COMMON,
                                        modifier = Modifier.weight(1f),
                                        onClick = { onPersonaClick(personaId) }
                                    )
                                }
                            }
                        }
                    }
                }

                // Empty state
                item {
                    if (recentPersonas.isEmpty() && recentConversations.isEmpty()) {
                        androidx.compose.foundation.layout.Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.Center,
                            verticalArrangement = androidx.compose.foundation.layout.Arrangement.Center
                        ) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Icon(
                                    imageVector = Icons.Default.AutoAwesome,
                                    contentDescription = null,
                                    modifier = Modifier.size(48.dp),
                                    tint = MaterialTheme.colorScheme.primary
                                )
                                androidx.compose.foundation.layout.Spacer(
                                    modifier = Modifier.height(FantasySpacing.md)
                                )
                                Text(
                                    text = "Bienvenue dans ETERNIA AI RPG",
                                    style = MaterialTheme.typography.headlineSmall,
                                    textAlign = androidx.compose.ui.text.TextAlign.Center
                                )
                                Text(
                                    text = "Créez votre première persona pour commencer",
                                    style = MaterialTheme.typography.bodyMedium,
                                    textAlign = androidx.compose.ui.text.TextAlign.Center,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun FantasyOutlinedButton(
    text: String,
    icon: androidx.compose.material.icons.Icons.Default,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    androidx.compose.material3.OutlinedButton(
        onClick = onClick,
        modifier = modifier,
        colors = androidx.compose.material3.OutlinedButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = androidx.compose.foundation.layout.Arrangement.spacedBy(8.dp)
        ) {
            Icon(imageVector = icon, contentDescription = null, modifier = Modifier.size(20.dp))
            Text(text = text)
        }
    }
}
