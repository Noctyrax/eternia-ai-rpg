package com.eternia.ai.rpg.reader.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.eternia.ai.rpg.designsystem.theme.EterniaTheme

@Composable
fun NovelReaderScreen(novelId: String) {
    EterniaTheme {
        Text(text = "Lecteur de Roman: $novelId - À venir")
    }
}
