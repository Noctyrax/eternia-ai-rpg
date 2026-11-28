package com.eternia.ai.rpg.designsystem.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.FilledButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.eternia.ai.rpg.designsystem.theme.FantasySpacing

@Composable
fun FantasyCard(
    modifier: Modifier = Modifier,
    contentPadding: androidx.compose.foundation.layout.PaddingValues = androidx.compose.foundation.layout.PaddingValues(0.dp),
    content: @Composable () -> Unit
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        shape = MaterialTheme.shapes.medium,
        contentPadding = contentPadding
    ) {
        Column(
            modifier = Modifier.padding(contentPadding).clipToBounds()
        ) {
            content()
        }
    }
}

@Composable
fun FantasyIconButton(
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        onClick = onClick,
        modifier = modifier
    ) {
        icon()
    }
}

@Composable
fun FantasyFilledButton(
    text: String,
    onClick: () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    FilledButton(
        onClick = onClick,
        modifier = modifier,
        colors = FilledButtonDefaults.filledButtonColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (leadingIcon != null) {
                leadingIcon()
            }
            Text(text = text)
        }
    }
}

@Composable
fun FantasyOutlinedButton(
    text: String,
    onClick: () -> Unit,
    leadingIcon: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
    ) {
        Row(
            modifier = Modifier,
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (leadingIcon != null) {
                leadingIcon()
            }
            Text(text = text)
        }
    }
}

@Composable
fun SectionCard(
    title: String,
    onMoreClick: (() -> Unit)? = null,
    itemCount: Int = 0,
    content: @Composable () -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.weight(1f)
            }
            if (onMoreClick != null) {
                TextButton(onClick = onMoreClick) {
                    Text(text = "Voir tout")
                }
            }
        }
        androidx.compose.foundation.layout.Spacer(
            modifier = Modifier.height(FantasySpacing.sm)
        )
        content()
    }
}

@Composable
fun FantasyCharacterCard(
    name: String,
    subtitle: String? = null,
    rarity: ItemRarity = ItemRarity.COMMON,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    Card(
        modifier = modifier
            .clipToBounds()
            .size(120.dp, 140.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        shape = MaterialTheme.shapes.medium,
        onClick = onClick
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium,
                maxLines = 2
            )
            subtitle?.let {
                Text(
                    text = it,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 12.sp
                    ),
                    maxLines = 1
                )
            }
            Text(
                text = rarity.label,
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontSize = 10.sp,
                    color = rarity.color
                )
            )
        }
    }
}

@Composable
fun ConversationCard(
    conversation: Any,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        shape = MaterialTheme.shapes.medium,
        onClick = onClick
    ) {
        androidx.compose.foundation.layout.Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            androidx.compose.material3.Icon(
                imageVector = Icons.Default.Chat,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            androidx.compose.foundation.layout.Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = (conversation as? com.eternia.ai.rpg.database.entity.ConversationEntity)?.title ?: "Conversation",
                    style = MaterialTheme.typography.bodyMedium
                )
                Text(
                    text = (conversation as? com.eternia.ai.rpg.database.entity.ConversationEntity)?.lastMessage ?: "",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontSize = 12.sp,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    maxLines = 1
                )
            }
        }
    }
}

@Composable
fun NovelCard(
    novel: Any,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant
        ),
        shape = MaterialTheme.shapes.medium,
        onClick = onClick
    ) {
        androidx.compose.foundation.layout.Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = androidx.compose.ui.Alignment.CenterVertically
        ) {
            androidx.compose.material3.Icon(
                imageVector = Icons.Default.Book,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.primary
            )
            androidx.compose.foundation.layout.Spacer(modifier = Modifier.width(16.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = (novel as? com.eternia.ai.rpg.database.entity.NovelEntity)?.title ?: "Roman",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

enum class ItemRarity(
    val label: String,
    val color: Color
) {
    COMMON("Commun", Color(0xFF9E9E9E)),
    UNCOMMON("Peu commun", Color(0xFF4CAF50)),
    RARE("Rare", Color(0xFF2196F3)),
    EPIC("Épique", Color(0xFF9C27B0)),
    LEGENDARY("Légendaire", Color(0xFFFF9800))
}
