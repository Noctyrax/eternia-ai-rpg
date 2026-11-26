# ProGuard Rules for core module

# Keep public API
-keep public class com.eternia.ai.rpg.core.** { *; }
-keep public interface com.eternia.ai.rpg.core.** { *; }

# Keep serialization
-keep class kotlinx.serialization.** { *; }
-keepclassmembers class * {
    @kotlinx.serialization.Serializable *;
}

# Keep Result sealed class
-keep class com.eternia.ai.rpg.core.Result { *; }
-keep class com.eternia.ai.rpg.core.Result$* { *; }