# ProGuard Rules for database module

# Keep public API
-keep public class com.eternia.ai.rpg.database.** { *; }
-keep public interface com.eternia.ai.rpg.database.** { *; }

# Keep Room
-keep class androidx.room.** { *; }
-keep class * extends androidx.room.RoomDatabase { *; }
-keep class * implements androidx.room.TypeConverter { *; }
-keepclassmembers class * implements androidx.room.TypeConverter {
    *;
}

# Keep Entities
-keep class com.eternia.ai.rpg.database.entity.** { *; }

# Keep DAOs
-keep class com.eternia.ai.rpg.database.dao.** { *; }

# Keep Converters
-keep class com.eternia.ai.rpg.database.converters.** { *; }