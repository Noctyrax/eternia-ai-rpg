# ProGuard Rules for memory module

# Keep public API
-keep public class com.eternia.ai.rpg.memory.** { *; }
-keep public interface com.eternia.ai.rpg.memory.** { *; }

# Keep Serialization
-keep class kotlinx.serialization.** { *; }
-keepclassmembers class * {
    @kotlinx.serialization.Serializable *;
}