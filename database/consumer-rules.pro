# Consumer ProGuard Rules for database module

# Keep public API
-keep public class com.eternia.ai.rpg.database.** { *; }
-keep public interface com.eternia.ai.rpg.database.** { *; }

# Keep Room Database
-keep class com.eternia.ai.rpg.database.EterniaDatabase { *; }

# Keep DAOs
-keep class com.eternia.ai.rpg.database.dao.** { *; }

# Keep Entities
-keep class com.eternia.ai.rpg.database.entity.** { *; }

# Keep Converters
-keep class com.eternia.ai.rpg.database.converters.** { *; }