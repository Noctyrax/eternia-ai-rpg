# Consumer ProGuard Rules for core module

# Keep public API
-keep public class com.eternia.ai.rpg.core.** { *; }
-keep public interface com.eternia.ai.rpg.core.** { *; }

# Keep Result sealed class
-keep class com.eternia.ai.rpg.core.Result { *; }
-keep class com.eternia.ai.rpg.core.Result$* { *; }

# Keep Constants
-keep class com.eternia.ai.rpg.core.Constants { *; }

# Keep Extensions
-keep class com.eternia.ai.rpg.core.Extensions { *; }

# Keep ApiModels
-keep class com.eternia.ai.rpg.core.api.** { *; }

# Keep Preferences
-keep class com.eternia.ai.rpg.core.preferences.** { *; }