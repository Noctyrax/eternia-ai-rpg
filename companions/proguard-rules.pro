# ProGuard Rules for companions module

# Keep public API
-keep public class com.eternia.ai.rpg.companions.** { *; }
-keep public interface com.eternia.ai.rpg.companions.** { *; }

# Keep Serialization
-keep class kotlinx.serialization.** { *; }
-keepclassmembers class * {
    @kotlinx.serialization.Serializable *;
}