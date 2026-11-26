# Consumer ProGuard Rules for companions module

# Keep public API
-keep public class com.eternia.ai.rpg.companions.** { *; }
-keep public interface com.eternia.ai.rpg.companions.** { *; }

# Keep Models
-keep class com.eternia.ai.rpg.companions.model.** { *; }