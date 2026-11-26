# Consumer ProGuard Rules for app module
# These rules are applied to projects that depend on this module

# Keep public API
-keep public class com.eternia.ai.rpg.** { *; }
-keep public interface com.eternia.ai.rpg.** { *; }

# Keep Hilt entry points
-keep class dagger.hilt.** { *; }

# Keep Room entities
-keep class com.eternia.ai.rpg.database.entity.** { *; }

# Keep serialization
-keep class kotlinx.serialization.** { *; }