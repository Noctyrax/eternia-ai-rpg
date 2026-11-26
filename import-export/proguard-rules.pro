# ProGuard Rules for import-export module

# Keep public API
-keep public class com.eternia.ai.rpg.importexport.** { *; }
-keep public interface com.eternia.ai.rpg.importexport.** { *; }

# Keep Tika/Apache Commons
-keep class org.apache.tika.** { *; }
-keep class org.apache.commons.** { *; }

# Keep Serialization
-keep class kotlinx.serialization.** { *; }
-keepclassmembers class * {
    @kotlinx.serialization.Serializable *;
}