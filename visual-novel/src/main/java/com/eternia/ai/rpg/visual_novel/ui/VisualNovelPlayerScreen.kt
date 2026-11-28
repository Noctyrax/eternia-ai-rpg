package com.eternia.ai.rpg.visual_novel.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.eternia.ai.rpg.designsystem.theme.EterniaTheme

@Composable
fun VisualNovelPlayerScreen(vnId: String) {
    EterniaTheme {
        Text(text = "Joueur Visual Novel: $vnId - À venir")
    }
}
