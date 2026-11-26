# ProGuard Rules for settings module

# Keep public API
-keep public class com.eternia.ai.rpg.settings.** { *; }
-keep public interface com.eternia.ai.rpg.settings.** { *; }

# Keep Security Crypto
-keep class androidx.security.crypto.** { *; }

# Keep Datastore
-keep class androidx.datastore.** { *; }

# Keep Biometric
-keep class androidx.biometric.** { *; }

# Keep Serialization
-keep class kotlinx.serialization.** { *; }
-keepclassmembers class * {
    @kotlinx.serialization.Serializable *;
}