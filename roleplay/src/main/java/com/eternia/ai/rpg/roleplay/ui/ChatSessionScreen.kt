package com.eternia.ai.rpg.roleplay.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eternia.ai.rpg.designsystem.theme.EterniaTheme
import com.eternia.ai.rpg.designsystem.theme.FantasySpacing

@Composable
fun ChatSessionScreen(
    conversationId: String,
    onSendMessage: (String) -> Unit = {}
) {
    val messages by remember { mutableStateOf(listOf<String>()) }
    val inputText by remember { mutableStateOf("") }

    EterniaTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            androidx.compose.material3.TopAppBar(
                title = { Text(text = "Conversation: $conversationId") },
                colors = MaterialTheme.colorScheme.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = FantasySpacing.md)
            ) {
                items(items = messages, key = { it }) { message ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = FantasySpacing.sm)
                            .clickable {},
                        colors = CardDefaults.cardColors(
                            containerColor = MaterialTheme.colorScheme.surfaceVariant
                        ),
                        shape = MaterialTheme.shapes.medium
                    ) {
                        androidx.compose.foundation.layout.Row(
                            modifier = Modifier.padding(16.dp)
                        ) {
                            Text(text = message, style = MaterialTheme.typography.bodyMedium)
                        }
                    }
                }
            }

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(FantasySpacing.md)
            ) {
                TextField(
                    label = { Text(text = "Écrivez votre message...") },
                    value = inputText,
                    onValueChange = { inputText = it },
                    modifier = Modifier.fillMaxWidth().padding(bottom = FantasySpacing.sm),
                    maxLines = 3
                )
                Button(
                    onClick = { if (inputText.isNotBlank()) { onSendMessage(inputText) } },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Envoyer")
                }
            }
        }
    }
}
