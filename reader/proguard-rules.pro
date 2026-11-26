# ProGuard Rules for reader module

# Keep public API
-keep public class com.eternia.ai.rpg.reader.** { *; }
-keep public interface com.eternia.ai.rpg.reader.** { *; }

# Keep Tika/Apache Commons
-keep class org.apache.tika.** { *; }
-keep class org.apache.commons.** { *; }

# Keep Serialization
-keep class kotlinx.serialization.** { *; }
-keepclassmembers class * {
    @kotlinx.serialization.Serializable *;
}