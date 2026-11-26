# ProGuard Rules for roleplay module

# Keep public API
-keep public class com.eternia.ai.rpg.roleplay.** { *; }
-keep public interface com.eternia.ai.rpg.roleplay.** { *; }

# Keep Serialization
-keep class kotlinx.serialization.** { *; }
-keepclassmembers class * {
    @kotlinx.serialization.Serializable *;
}