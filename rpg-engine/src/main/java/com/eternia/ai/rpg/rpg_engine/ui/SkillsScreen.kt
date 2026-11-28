package com.eternia.ai.rpg.rpg_engine.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.eternia.ai.rpg.designsystem.theme.EterniaTheme

@Composable
fun SkillsScreen(characterId: String) {
    EterniaTheme {
        Text(text = "Compétences: $characterId - À venir")
    }
}
