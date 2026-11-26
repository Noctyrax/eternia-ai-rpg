# Consumer ProGuard Rules for media module

# Keep public API
-keep public class com.eternia.ai.rpg.media.** { *; }
-keep public interface com.eternia.ai.rpg.media.** { *; }

# Keep Models
-keep class com.eternia.ai.rpg.media.model.** { *; }