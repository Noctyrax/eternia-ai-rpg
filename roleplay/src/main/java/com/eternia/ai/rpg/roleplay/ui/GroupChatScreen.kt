package com.eternia.ai.rpg.roleplay.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.eternia.ai.rpg.designsystem.theme.EterniaTheme

@Composable
fun GroupChatScreen(conversationId: String) {
    EterniaTheme {
        Text(text = "Chat de Groupe: $conversationId - À venir")
    }
}
