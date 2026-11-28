package com.eternia.ai.rpg.character_cards.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.eternia.ai.rpg.designsystem.theme.EterniaTheme

@Composable
fun CharacterDetailScreen(characterId: String) {
    EterniaTheme {
        Text(text = "Détail Personnage: $characterId - À venir")
    }
}
