package com.eternia.ai.rpg.roleplay.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.eternia.ai.rpg.designsystem.theme.EterniaTheme

@Composable
fun ChatSessionScreen(conversationId: String) {
    EterniaTheme {
        Text(text = "Session de Chat: $conversationId - À venir")
    }
}
