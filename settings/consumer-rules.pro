# Consumer ProGuard Rules for settings module

# Keep public API
-keep public class com.eternia.ai.rpg.settings.** { *; }
-keep public interface com.eternia.ai.rpg.settings.** { *; }

# Keep KeyManager
-keep class com.eternia.ai.rpg.settings.KeyManager { *; }

# Keep Models
-keep class com.eternia.ai.rpg.settings.model.** { *; }