package com.eternia.ai.rpg.ai_providers.providers

import com.eternia.ai.rpg.ai_providers.AiProvider
import com.eternia.ai.rpg.ai_providers.AiProviderResult
import com.eternia.ai.rpg.ai_providers.AiProviderResult.Error
import com.eternia.ai.rpg.ai_providers.AiProviderResult.Success
import okhttp3.MediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response
import kotlinx.coroutines.withContext
import kotlinx.coroutines.Dispatchers
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.JsonPrimitive

const val GEMINI_BASE_URL = "https://generativelanguage.googleapis.com/v1beta"

class GeminiProvider : AiProvider {
    override val id = "gemini"
    override val name = "Google Gemini"
    override val supportsStreaming = true

    private val client = OkHttpClient()

    override suspend fun testConnection(apiKey: String, baseUrl: String? = null): AiProviderResult {
        return withContext(Dispatchers.Default) {
            try {
                val url = (baseUrl ?: GEMINI_BASE_URL) + "/models/gemini-1.5-flash:generateContent"
                val body = Json.encodeToString(JsonObject(
                    "contents" to listOf(
                        JsonObject("parts" to listOf(
                            JsonObject("text" to "test")
                        ))
                    )
                ))
                val request = Request.Builder()
                    .url(url)
                    .header("x-goog-api-key", apiKey)
                    .header("Content-Type", "application/json")
                    .post(RequestBody.create(body, MediaType.get("application/json")!!))
                    .build()
                val response: Response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    Success("Connexion Gemini réussie")
                } else {
                    Error("Erreur Gemini: ${response.code} ${response.body?.string()}")
                }
            } catch (e: Exception) {
                Error("Échec de la connexion Gemini", e)
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
                val url = (baseUrl ?: GEMINI_BASE_URL) + "/models/$model:generateContent"
                val body = Json.encodeToString(JsonObject(
                    "contents" to listOf(
                        JsonObject("parts" to listOf(
                            JsonObject("text" to prompt)
                        ))
                    ),
                    "generationConfig" to JsonObject(
                        "temperature" to JsonPrimitive(temperature),
                        "maxOutputTokens" to JsonPrimitive(maxTokens)
                    )
                ))
                val request = Request.Builder()
                    .url(url)
                    .header("x-goog-api-key", apiKey)
                    .header("Content-Type", "application/json")
                    .post(RequestBody.create(body, MediaType.get("application/json")!!))
                    .build()
                val response: Response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    val json = Json.parseToJsonElement(response.body!!.string()) as JsonObject
                    val candidates = json["candidates"]?.jsonObject?.get("content")?.jsonObject
                    val parts = candidates?.get("parts")?.jsonArray
                    val text = parts?.firstOrNull()?.jsonObject?.get("text")?.jsonPrimitive?.content ?: ""
                    Success(text)
                } else {
                    Error("Erreur Gemini: ${response.code} ${response.body?.string()}")
                }
            } catch (e: Exception) {
                Error("Échec génération Gemini", e)
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
        // Streaming non implémenté ici, délègue à generateText
        return generateText(apiKey, prompt, model, temperature, maxTokens, baseUrl).let { result ->
            if (result is Success) {
                onChunk(result.text)
            }
            result
        }
    }
}
