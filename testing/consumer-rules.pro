# Consumer ProGuard Rules for testing module

# Keep public API
-keep public class com.eternia.ai.rpg.testing.** { *; }
-keep public interface com.eternia.ai.rpg.testing.** { *; }

# Keep Test Utilities
-keep class com.eternia.ai.rpg.testing.** { *; }