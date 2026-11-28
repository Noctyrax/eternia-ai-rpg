package com.eternia.ai.rpg.ai_providers.providers

import com.eternia.ai.rpg.ai_providers.AiProvider
import com.eternia.ai.rpg.ai_providers.AiProviderResult
import com.eternia.ai.rpg.ai_providers.AiProviderResult.Error
import com.eternia.ai.rpg.ai_providers.AiProviderResult.Success
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

const val ANTHROPIC_BASE_URL = "https://api.anthropic.com/v1"

class AnthropicProvider : AiProvider {
    override val id = "anthropic"
    override val name = "Anthropic Claude"
    override val supportsStreaming = true

    private val client = OkHttpClient()

    override suspend fun testConnection(apiKey: String, baseUrl: String? = null): AiProviderResult {
        return withContext(Dispatchers.Default) {
            try {
                val url = (baseUrl ?: ANTHROPIC_BASE_URL) + "/messages"
                val body = Json.encodeToString(JsonObject(
                    "model" to "claude-3-haiku-20240307",
                    "max_tokens" to 5,
                    "messages" to JsonArray(listOf(
                        JsonObject("role" to "user", "content" to "test")
                    ))
                ))
                val request = Request.Builder()
                    .url(url)
                    .header("Authorization", "Bearer $apiKey")
                    .header("anthropic-version", "2026-10-21")
                    .header("Content-Type", "application/json")
                    .post(RequestBody.create(body, MediaType.get("application/json")!!))
                    .build()
                val response: Response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    Success("Connexion Anthropic réussie")
                } else {
                    Error("Erreur Anthropic: ${response.code} ${response.body?.string()}")
                }
            } catch (e: Exception) {
                Error("Échec de la connexion Anthropic", e)
            }
        }
    }

    override suspend fun generateText(
        apiKey: String,
        prompt: String,
        model: String,
        temperature: Float = 0.8f,
        maxTokens: Int = 2048,
        baseUrl: String? = null
    ): AiProviderResult {
        return withContext(Dispatchers.Default) {
            try {
                val url = (baseUrl ?: ANTHROPIC_BASE_URL) + "/messages"
                val body = Json.encodeToString(JsonObject(
                    "model" to model,
                    "max_tokens" to JsonPrimitive(maxTokens),
                    "temperature" to JsonPrimitive(temperature),
                    "messages" to JsonArray(listOf(
                        JsonObject("role" to "user", "content" to prompt)
                    ))
                ))
                val request = Request.Builder()
                    .url(url)
                    .header("Authorization", "Bearer $apiKey")
                    .header("anthropic-version", "2026-10-21")
                    .header("Content-Type", "application/json")
                    .post(RequestBody.create(body, MediaType.get("application/json")!!))
                    .build()
                val response: Response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    val json = Json.parseToJsonElement(response.body!!.string()) as JsonObject
                    val content = json["content"]?.jsonArray
                    val text = content?.firstOrNull()?.jsonObject?.get("text")?.jsonPrimitive?.content ?: ""
                    Success(text)
                } else {
                    Error("Erreur Anthropic: ${response.code} ${response.body?.string()}")
                }
            } catch (e: Exception) {
                Error("Échec génération Anthropic", e)
            }
        }
    }

    override suspend fun generateTextStream(
        apiKey: String,
        prompt: String,
        model: String,
        temperature: Float = 0.8f,
        maxTokens: Int = 2048,
        baseUrl: String? = null,
        onChunk: (String) -> Unit = {}
    ): AiProviderResult {
        return generateText(apiKey, prompt, model, temperature, maxTokens, baseUrl).let { result ->
            if (result is Success) {
                onChunk(result.text)
            }
            result
        }
    }
}
