package com.eternia.ai.rpg.core

object ThemeState {
    var isDarkTheme: Boolean = false
        private set

    fun toggle() {
        isDarkTheme = !isDarkTheme
    }
}
