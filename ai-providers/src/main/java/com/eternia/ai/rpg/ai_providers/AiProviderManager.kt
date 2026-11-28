package com.eternia.ai.rpg.ai_providers

import com.eternia.ai.rpg.ai_providers.providers.GeminiProvider
import com.eternia.ai.rpg.ai_providers.providers.DeepSeekProvider
import com.eternia.ai.rpg.ai_providers.providers.OpenAIProvider
import com.eternia.ai.rpg.ai_providers.providers.AnthropicProvider
import com.eternia.ai.rpg.ai_providers.providers.MistralProvider
import com.eternia.ai.rpg.ai_providers.providers.OpenRouterProvider
import com.eternia.ai.rpg.ai_providers.providers.GroqProvider

class AiProviderManager {

    private val providers = mutableMapOf<String, AiProvider>()

    fun registerProvider(id: String, provider: AiProvider) {
        providers[id] = provider
    }

    fun getProvider(id: String): AiProvider? = providers[id]

    fun getAllProviderIds(): List<String> = providers.keys.toList()

    fun initDefaultProviders() {
        registerProvider("gemini", GeminiProvider())
        registerProvider("deepseek", DeepSeekProvider())
        registerProvider("openai", OpenAIProvider())
        registerProvider("anthropic", AnthropicProvider())
        registerProvider("mistral", MistralProvider())
        registerProvider("openrouter", OpenRouterProvider())
        registerProvider("groq", GroqProvider())
    }
}

sealed class AiProviderResult {
    data class Success(val text: String) : AiProviderResult()
    data class Error(val message: String, val throwable: Throwable? = null) : AiProviderResult()
}

interface AiProvider {
    val id: String
    val name: String
    val supportsStreaming: Boolean

    suspend fun testConnection(apiKey: String, baseUrl: String? = null): AiProviderResult

    suspend fun generateText(
        apiKey: String,
        prompt: String,
        model: String,
        temperature: Float = 0.8f,
        maxTokens: Int = 2048,
        baseUrl: String? = null
    ): AiProviderResult

    suspend fun generateTextStream(
        apiKey: String,
        prompt: String,
        model: String,
        temperature: Float = 0.8f,
        maxTokens: Int = 2048,
        baseUrl: String? = null,
        onChunk: (String) -> Unit = {}
    ): AiProviderResult
}
