# ProGuard Rules for agent module

# Keep public API
-keep public class com.eternia.ai.rpg.agent.** { *; }
-keep public interface com.eternia.ai.rpg.agent.** { *; }

# Keep Serialization
-keep class kotlinx.serialization.** { *; }
-keepclassmembers class * {
    @kotlinx.serialization.Serializable *;
}