# Consumer ProGuard Rules for ai-providers module

# Keep public API
-keep public class com.eternia.ai.rpg.aiproviders.** { *; }
-keep public interface com.eternia.ai.rpg.aiproviders.** { *; }

# Keep Provider Classes
-keep class com.eternia.ai.rpg.aiproviders.AiProvider { *; }
-keep class com.eternia.ai.rpg.aiproviders.BaseAiProvider { *; }
-keep class com.eternia.ai.rpg.aiproviders.AiProviderFactory { *; }
-keep class com.eternia.ai.rpg.aiproviders.AiProviderManager { *; }
-keep class com.eternia.ai.rpg.aiproviders.GeminiProvider { *; }
-keep class com.eternia.ai.rpg.aiproviders.DeepSeekProvider { *; }
-keep class com.eternia.ai.rpg.aiproviders.OpenAiProvider { *; }
-keep class com.eternia.ai.rpg.aiproviders.AnthropicProvider { *; }
-keep class com.eternia.ai.rpg.aiproviders.OpenRouterProvider { *; }
-keep class com.eternia.ai.rpg.aiproviders.CustomProvider { *; }

# Keep Model Classes
-keep class com.eternia.ai.rpg.aiproviders.model.** { *; }