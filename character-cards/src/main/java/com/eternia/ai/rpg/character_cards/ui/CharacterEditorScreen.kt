package com.eternia.ai.rpg.character_cards.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.eternia.ai.rpg.designsystem.theme.EterniaTheme
import com.eternia.ai.rpg.designsystem.theme.FantasySpacing

@Composable
fun CharacterEditorScreen(
    initialCharacterId: String? = null,
    onSave: (String, String, String?) -> Unit = { _, _, _ -> },
    onCancel: () -> Unit = {}
) {
    val name by remember { mutableStateOf("") }
    val description by remember { mutableStateOf("") }

    EterniaTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(FantasySpacing.md)
        ) {
            androidx.compose.material3.TopAppBar(
                title = { Text(text = "Nouveau Personnage IA") },
                navigationIcon = {
                    androidx.compose.material3.IconButton(onClick = onCancel) {
                        androidx.compose.material3.Icon(
                            imageVector = androidx.compose.material.icons.Icons.Default.Close,
                            contentDescription = null
                        )
                    }
                },
                actions = {
                    androidx.compose.material3.TextButton(onClick = { onSave(name, description, null) }) {
                        Text(text = "Enregistrer")
                    }
                },
                colors = MaterialTheme.colorScheme.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )

            TextField(
                label = { Text(text = "Nom du personnage") },
                value = name,
                onValueChange = { name = it },
                modifier = Modifier.fillMaxWidth().padding(bottom = FantasySpacing.md)
            )

            TextField(
                label = { Text(text = "Description et personnalité") },
                value = description,
                onValueChange = { description = it },
                modifier = Modifier.fillMaxWidth().padding(bottom = FantasySpacing.md),
                maxLines = 4
            )

            Text(
                text = "Ce personnage est contrôlé par l'IA dans vos conversations.",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
