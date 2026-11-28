package com.eternia.ai.rpg.personas.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.eternia.ai.rpg.designsystem.theme.EterniaTheme

@Composable
fun PersonaDetailScreen(personaId: String) {
    EterniaTheme {
        Text(text = "Détail Persona: $personaId - À venir")
    }
}
