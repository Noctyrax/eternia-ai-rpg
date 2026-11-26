# ProGuard Rules for design-system module

# Keep public API
-keep public class com.eternia.ai.rpg.designsystem.** { *; }
-keep public interface com.eternia.ai.rpg.designsystem.** { *; }

# Keep Compose components
-keep class androidx.compose.** { *; }
-keepclassmembers class * {
    @androidx.compose.runtime.Composable *;
}

# Keep Theme
-keep class com.eternia.ai.rpg.designsystem.theme.** { *; }