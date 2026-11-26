# ProGuard Rules for character-cards module

# Keep public API
-keep public class com.eternia.ai.rpg.charactercards.** { *; }
-keep public interface com.eternia.ai.rpg.charactercards.** { *; }

# Keep Tika/Apache Commons
-keep class org.apache.tika.** { *; }
-keep class org.apache.commons.** { *; }

# Keep Serialization
-keep class kotlinx.serialization.** { *; }
-keepclassmembers class * {
    @kotlinx.serialization.Serializable *;
}