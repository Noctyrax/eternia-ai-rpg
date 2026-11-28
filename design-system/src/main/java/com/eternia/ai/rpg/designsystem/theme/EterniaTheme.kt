package com.eternia.ai.rpg.designsystem.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eternia.ai.rpg.core.Constants
import com.eternia.ai.rpg.designsystem.theme.dark.EterniaDarkColorScheme
import com.eternia.ai.rpg.designsystem.theme.light.EterniaLightColorScheme

@Composable
fun EterniaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) {
        EterniaDarkColorScheme
    } else {
        EterniaLightColorScheme
    }

    val viewConfiguration = LocalContext.current.resources.configuration
    val windowWidthSizeClass = rememberUpdatedState(
        calculateWindowWidthSizeClass(viewConfiguration)
    ) { value }

    SideEffect {
        windowWidthSizeClass.value
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = FantasyTypography,
        shapes = FantasyShapes,
        content = content
    )
}

val FantasyTypography = androidx.compose.material3.Typography(
    headlineMedium = androidx.compose.material3.TextStyle(
        fontSize = 24.sp,
        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
    ),
    headlineSmall = androidx.compose.material3.TextStyle(
        fontSize = 20.sp,
        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
    ),
    titleMedium = androidx.compose.material3.TextStyle(
        fontSize = 16.sp,
        fontWeight = androidx.compose.ui.text.font.FontWeight.Medium
    ),
    bodyMedium = androidx.compose.material3.TextStyle(
        fontSize = 14.sp
    )
)

val FantasySpacing = object {
    val xs = 4.dp
    val sm = 8.dp
    val md = 16.dp
    val lg = 24.dp
    val xl = 32.dp
}

val FantasyShapes = androidx.compose.material3.Shapes(
    small = androidx.compose.foundation.shape.RoundedCornerShape(8.dp),
    medium = androidx.compose.foundation.shape.RoundedCornerShape(12.dp),
    large = androidx.compose.foundation.shape.RoundedCornerShape(16.dp)
)
