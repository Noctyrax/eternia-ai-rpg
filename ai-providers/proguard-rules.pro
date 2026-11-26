# ProGuard Rules for ai-providers module

# Keep public API
-keep public class com.eternia.ai.rpg.aiproviders.** { *; }
-keep public interface com.eternia.ai.rpg.aiproviders.** { *; }

# Keep Provider Classes
-keep class com.eternia.ai.rpg.aiproviders.AiProvider { *; }
-keep class com.eternia.ai.rpg.aiproviders.BaseAiProvider { *; }
-keep class com.eternia.ai.rpg.aiproviders.AiProviderFactory { *; }
-keep class com.eternia.ai.rpg.aiproviders.AiProviderManager { *; }

# Keep OkHttp/Retrofit
-keep class okhttp3.** { *; }
-keep class okio.** { *; }
-keep class retrofit2.** { *; }
-keep class com.squareup.moshi.** { *; }

# Keep Serialization
-keep class kotlinx.serialization.** { *; }
-keepclassmembers class * {
    @kotlinx.serialization.Serializable *;
}