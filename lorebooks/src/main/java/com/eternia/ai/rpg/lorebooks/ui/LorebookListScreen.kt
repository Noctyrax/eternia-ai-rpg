package com.eternia.ai.rpg.lorebooks.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.eternia.ai.rpg.designsystem.theme.EterniaTheme

@Composable
fun LorebookListScreen(worldId: String? = null) {
    EterniaTheme {
        Text(text = "Liste des Lorebooks - À venir")
    }
}
