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

const val OPENAI_BASE_URL = "https://api.openai.com/v1"

class OpenAIProvider : AiProvider {
    override val id = "openai"
    override val name = "OpenAI"
    override val supportsStreaming = true

    private val client = OkHttpClient()

    override suspend fun testConnection(apiKey: String, baseUrl: String? = null): AiProviderResult {
        return withContext(Dispatchers.Default) {
            try {
                val url = (baseUrl ?: OPENAI_BASE_URL) + "/chat/completions"
                val body = Json.encodeToString(JsonObject(
                    "model" to "gpt-4o-mini",
                    "messages" to JsonArray(listOf(
                        JsonObject("role" to "user", "content" to "test")
                    )),
                    "max_tokens" to 5
                ))
                val request = Request.Builder()
                    .url(url)
                    .header("Authorization", "Bearer $apiKey")
                    .header("Content-Type", "application/json")
                    .post(RequestBody.create(body, MediaType.get("application/json")!!))
                    .build()
                val response: Response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    Success("Connexion OpenAI réussie")
                } else {
                    Error("Erreur OpenAI: ${response.code} ${response.body?.string()}")
                }
            } catch (e: Exception) {
                Error("Échec de la connexion OpenAI", e)
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
                val url = (baseUrl ?: OPENAI_BASE_URL) + "/chat/completions"
                val body = Json.encodeToString(JsonObject(
                    "model" to model,
                    "messages" to JsonArray(listOf(
                        JsonObject("role" to "user", "content" to prompt)
                    )),
                    "temperature" to JsonPrimitive(temperature),
                    "max_tokens" to JsonPrimitive(maxTokens)
                ))
                val request = Request.Builder()
                    .url(url)
                    .header("Authorization", "Bearer $apiKey")
                    .header("Content-Type", "application/json")
                    .post(RequestBody.create(body, MediaType.get("application/json")!!))
                    .build()
                val response: Response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    val json = Json.parseToJsonElement(response.body!!.string()) as JsonObject
                    val choices = json["choices"]?.jsonArray
                    val text = choices?.firstOrNull()?.jsonObject?.get("message")?.jsonObject?.get("content")?.jsonPrimitive?.content ?: ""
                    Success(text)
                } else {
                    Error("Erreur OpenAI: ${response.code} ${response.body?.string()}")
                }
            } catch (e: Exception) {
                Error("Échec génération OpenAI", e)
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
