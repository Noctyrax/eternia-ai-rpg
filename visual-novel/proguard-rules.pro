# ProGuard Rules for visual-novel module

# Keep public API
-keep public class com.eternia.ai.rpg.visualnovel.** { *; }
-keep public interface com.eternia.ai.rpg.visualnovel.** { *; }

# Keep Media3/ExoPlayer
-keep class androidx.media3.** { *; }
-keep class com.google.android.exoplayer2.** { *; }

# Keep Serialization
-keep class kotlinx.serialization.** { *; }
-keepclassmembers class * {
    @kotlinx.serialization.Serializable *;
}