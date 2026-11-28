package com.eternia.ai.rpg.media.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.eternia.ai.rpg.designsystem.theme.EterniaTheme
import com.eternia.ai.rpg.designsystem.theme.FantasySpacing

@Composable
fun MediaListScreen() {
    EterniaTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(FantasySpacing.md)
        ) {
            androidx.compose.material3.TopAppBar(
                title = { Text(text = "Médias") },
                colors = MaterialTheme.colorScheme.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )

            Text(
                text = "Images, audio et pièces jointes",
                style = MaterialTheme.typography.bodyMedium
            )
            androidx.compose.foundation.layout.Spacer(
                modifier = Modifier.height(FantasySpacing.lg)
            )
            Text(
                text = "Médias à venir",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
