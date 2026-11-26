# ProGuard Rules for personas module

# Keep public API
-keep public class com.eternia.ai.rpg.personas.** { *; }
-keep public interface com.eternia.ai.rpg.personas.** { *; }

# Keep Serialization
-keep class kotlinx.serialization.** { *; }
-keepclassmembers class * {
    @kotlinx.serialization.Serializable *;
}