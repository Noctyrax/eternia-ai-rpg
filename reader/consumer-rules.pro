# Consumer ProGuard Rules for reader module

# Keep public API
-keep public class com.eternia.ai.rpg.reader.** { *; }
-keep public interface com.eternia.ai.rpg.reader.** { *; }

# Keep Models
-keep class com.eternia.ai.rpg.reader.model.** { *; }