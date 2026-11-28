package com.eternia.ai.rpg.import_export.ui

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
fun ImportExportScreen() {
    EterniaTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(FantasySpacing.md)
        ) {
            androidx.compose.material3.TopAppBar(
                title = { Text(text = "Import/Export") },
                colors = MaterialTheme.colorScheme.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )

            Text(
                text = "Sauvegarde et restauration de vos données",
                style = MaterialTheme.typography.bodyMedium
            )
            androidx.compose.foundation.layout.Spacer(
                modifier = Modifier.height(FantasySpacing.lg)
            )
            Text(
                text = "Import/Export à venir",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
