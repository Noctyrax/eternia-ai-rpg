# ProGuard Rules for rpg-engine module

# Keep public API
-keep public class com.eternia.ai.rpg.rpgengine.** { *; }
-keep public interface com.eternia.ai.rpg.rpgengine.** { *; }

# Keep Serialization
-keep class kotlinx.serialization.** { *; }
-keepclassmembers class * {
    @kotlinx.serialization.Serializable *;
}