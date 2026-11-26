# ProGuard Rules for lorebooks module

# Keep public API
-keep public class com.eternia.ai.rpg.lorebooks.** { *; }
-keep public interface com.eternia.ai.rpg.lorebooks.** { *; }

# Keep Serialization
-keep class kotlinx.serialization.** { *; }
-keepclassmembers class * {
    @kotlinx.serialization.Serializable *;
}