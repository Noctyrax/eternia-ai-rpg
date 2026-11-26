# Consumer ProGuard Rules for design-system module

# Keep public API
-keep public class com.eternia.ai.rpg.designsystem.** { *; }
-keep public interface com.eternia.ai.rpg.designsystem.** { *; }

# Keep Theme
-keep class com.eternia.ai.rpg.designsystem.theme.** { *; }

# Keep Components
-keep class com.eternia.ai.rpg.designsystem.components.** { *; }

# Keep Typography
-keep class com.eternia.ai.rpg.designsystem.typography.** { *; }

# Keep Spacing
-keep class com.eternia.ai.rpg.designsystem.spacing.** { *; }