package com.eternia.ai.rpg.core.preferences

import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.preferencesKey
import androidx.datastore.preferences.rxjava3.RxPreferenceDataStoreBuilder
import androidx.datastore.rxjava3.RxDataStore
import com.eternia.ai.rpg.core.Constants
import com.eternia.ai.rpg.core.Result
import com.eternia.ai.rpg.core.Result.failure
import com.eternia.ai.rpg.core.Result.success
import com.google.common.util.concurrent.ListenableFuture
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

class PreferencesManager(private val context: Context) {

    private val dataStore: RxDataStore<Preferences> = RxPreferenceDataStoreBuilder(context, "eternia_prefs").build()

    private val KEY_THEME = preferencesKey<String>("theme")
    private val KEY_LANGUAGE = preferencesKey<String>("language")
    private val KEY_FIRST_LAUNCH = preferencesKey<Boolean>("first_launch")
    private val KEY_DEFAULT_PERSONA = preferencesKey<String>("default_persona")
    private val KEY_ENCRYPTED_API_KEYS = preferencesKey<String>("encrypted_api_keys")
    private val KEY_PROVIDER_PROFILES = preferencesKey<String>("provider_profiles")
    private val KEY_CHAT_SETTINGS = preferencesKey<String>("chat_settings")
    private val KEY_RPG_SETTINGS = preferencesKey<String>("rpg_settings")
    private val KEY_MEDIA_SETTINGS = preferencesKey<String>("media_settings")
    private val KEY_BACKUP_SETTINGS = preferencesKey<String>("backup_settings")

    // Encryption
    private val KEY_ALIAS = "eternia_master_key"
    private val ANDROID_KEYSTORE = "AndroidKeyStore"
    private val AES_GCM = "AES/GCM/NoPadding"
    private val TAG_LENGTH = 128
    private val IV_LENGTH = 12

    private val masterKey: SecretKey by lazy { getOrCreateMasterKey() }

    private fun getOrCreateMasterKey(): SecretKey {
        val keyStore = KeyStore.getInstance(ANDROID_KEYSTORE).apply { load(null) }
        return if (keyStore.containsAlias(KEY_ALIAS)) {
            keyStore.getKey(KEY_ALIAS, null) as SecretKey
        } else {
            val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, ANDROID_KEYSTORE)
            val spec = KeyGenParameterSpec.Builder(KEY_ALIAS, KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                .setKeySize(256)
                .setUserAuthenticationRequired(false)
                .build()
            keyGenerator.init(spec)
            keyGenerator.generateKey()
        }
    }

    private fun encrypt(data: String): Result<String> = Result.runCatching {
        val cipher = Cipher.getInstance(AES_GCM)
        val iv = java.security.SecureRandom().apply { nextBytes(ByteArray(IV_LENGTH)) }
        val spec = GCMParameterSpec(TAG_LENGTH, iv)
        cipher.init(Cipher.ENCRYPT_MODE, masterKey, spec)
        val encrypted = cipher.doFinal(data.toByteArray())
        // Prepend IV
        ByteArray(IV_LENGTH + encrypted.size).apply {
            iv.copyInto(this, 0)
            encrypted.copyInto(this, IV_LENGTH)
        }.let { android.util.Base64.encodeToString(it, android.util.Base64.NO_WRAP) }
    }

    private fun decrypt(encryptedData: String): Result<String> = Result.runCatching {
        val decoded = android.util.Base64.decode(encryptedData, android.util.Base64.NO_WRAP)
        require(decoded.size >= IV_LENGTH) { "Invalid encrypted data" }
        val iv = decoded.copyOfRange(0, IV_LENGTH)
        val ciphertext = decoded.copyOfRange(IV_LENGTH, decoded.size)
        val cipher = Cipher.getInstance(AES_GCM)
        val spec = GCMParameterSpec(TAG_LENGTH, iv)
        cipher.init(Cipher.DECRYPT_MODE, masterKey, spec)
        String(cipher.doFinal(ciphertext))
    }

    // ========================================================================
    // Theme
    // ========================================================================

    suspend fun setTheme(theme: String): Result<Unit> = Result.runCatching {
        dataStore.updateDataAsync { it[KEY_THEME] = theme }.await()
    }

    fun observeTheme(): Single<String> = dataStore.data.map { it[KEY_THEME] ?: "system" }.firstOrError()

    // ========================================================================
    // Language
    // ========================================================================

    suspend fun setLanguage(language: String): Result<Unit> = Result.runCatching {
        dataStore.updateDataAsync { it[KEY_LANGUAGE] = language }.await()
    }

    fun observeLanguage(): Single<String> = dataStore.data.map { it[KEY_LANGUAGE] ?: "fr" }.firstOrError()

    // ========================================================================
    // First Launch
    // ========================================================================

    suspend fun setFirstLaunchComplete(): Result<Unit> = Result.runCatching {
        dataStore.updateDataAsync { it[KEY_FIRST_LAUNCH] = false }.await()
    }

    fun isFirstLaunch(): Single<Boolean> = dataStore.data.map { it[KEY_FIRST_LAUNCH] ?: true }.firstOrError()

    // ========================================================================
    // Default Persona
    // ========================================================================

    suspend fun setDefaultPersona(personaId: String?): Result<Unit> = Result.runCatching {
        dataStore.updateDataAsync {
            if (personaId != null) it[KEY_DEFAULT_PERSONA] = personaId else it.remove(KEY_DEFAULT_PERSONA)
        }.await()
    }

    fun observeDefaultPersona(): Single<String?> = dataStore.data.map { it[KEY_DEFAULT_PERSONA] }.firstOrError()

    // ========================================================================
    // API Keys (Encrypted)
    // ========================================================================

    suspend fun saveApiKeys(apiKeys: Map<String, String>): Result<Unit> = Result.runCatching {
        val json = kotlinx.serialization.json.Json.encodeToString(apiKeys)
        val encrypted = encrypt(json).getOrThrow()
        dataStore.updateDataAsync { it[KEY_ENCRYPTED_API_KEYS] = encrypted }.await()
    }

    suspend fun getApiKeys(): Result<Map<String, String>> = Result.runCatching {
        val encrypted = dataStore.data.map { it[KEY_ENCRYPTED_API_KEYS] }.blockingFirst()
            ?: return@runCatching emptyMap()
        val decrypted = decrypt(encrypted).getOrThrow()
        kotlinx.serialization.json.Json.decodeFromString<Map<String, String>>(decrypted)
    }

    suspend fun saveApiKey(providerId: String, apiKey: String): Result<Unit> = Result.runCatching {
        val current = getApiKeys().getOrElse(emptyMap()).toMutableMap()
        current[providerId] = apiKey
        saveApiKeys(current).getOrThrow()
    }

    suspend fun getApiKey(providerId: String): Result<String?> = Result.runCatching {
        getApiKeys().getOrElse(emptyMap())[providerId]
    }

    suspend fun deleteApiKey(providerId: String): Result<Unit> = Result.runCatching {
        val current = getApiKeys().getOrElse(emptyMap()).toMutableMap()
        current.remove(providerId)
        saveApiKeys(current).getOrThrow()
    }

    // ========================================================================
    // Provider Profiles
    // ========================================================================

    @kotlinx.serialization.Serializable
    data class ProviderProfile(
        val providerId: String,
        val modelId: String,
        val profileName: String,
        val temperature: Float = 0.8f,
        val topP: Float = 0.9f,
        val topK: Int = 40,
        val maxTokens: Int = 2048,
        val systemPrompt: String = "",
        val customParams: Map<String, String> = emptyMap()
    )

    suspend fun saveProviderProfiles(profiles: List<ProviderProfile>): Result<Unit> = Result.runCatching {
        val json = kotlinx.serialization.json.Json.encodeToString(profiles)
        dataStore.updateDataAsync { it[KEY_PROVIDER_PROFILES] = json }.await()
    }

    fun observeProviderProfiles(): Single<List<ProviderProfile>> = dataStore.data.map {
        val json = it[KEY_PROVIDER_PROFILES] ?: return@map emptyList()
        kotlinx.serialization.json.Json.decodeFromString<List<ProviderProfile>>(json)
    }.firstOrError()

    // ========================================================================
    // Chat Settings
    // ========================================================================

    @kotlinx.serialization.Serializable
    data class ChatSettings(
        val defaultTemperature: Float = 0.8f,
        val defaultTopP: Float = 0.9f,
        val defaultTopK: Int = 40,
        val defaultMaxTokens: Int = 2048,
        val enableStreaming: Boolean = true,
        val enableMarkdown: Boolean = true,
        val enableCodeHighlight: Boolean = true,
        val fontSize: Int = 16,
        val showTimestamps: Boolean = true,
        val groupByPersona: Boolean = true
    )

    suspend fun saveChatSettings(settings: ChatSettings): Result<Unit> = Result.runCatching {
        val json = kotlinx.serialization.json.Json.encodeToString(settings)
        dataStore.updateDataAsync { it[KEY_CHAT_SETTINGS] = json }.await()
    }

    fun observeChatSettings(): Single<ChatSettings> = dataStore.data.map {
        val json = it[KEY_CHAT_SETTINGS] ?: return@map ChatSettings()
        kotlinx.serialization.json.Json.decodeFromString<ChatSettings>(json)
    }.firstOrError()

    // ========================================================================
    // RPG Settings
    // ========================================================================

    @kotlinx.serialization.Serializable
    data class RpgSettings(
        val autoRollDice: Boolean = true,
        val showDiceAnimations: Boolean = true,
        val showDamageNumbers: Boolean = true,
        val confirmCriticalActions: Boolean = true,
        val difficulty: String = "normal",
        val permadeath: Boolean = false,
        val autoSave: Boolean = true,
        val autoSaveInterval: Int = 300
    )

    suspend fun saveRpgSettings(settings: RpgSettings): Result<Unit> = Result.runCatching {
        val json = kotlinx.serialization.json.Json.encodeToString(settings)
        dataStore.updateDataAsync { it[KEY_RPG_SETTINGS] = json }.await()
    }

    fun observeRpgSettings(): Single<RpgSettings> = dataStore.data.map {
        val json = it[KEY_RPG_SETTINGS] ?: return@map RpgSettings()
        kotlinx.serialization.json.Json.decodeFromString<RpgSettings>(json)
    }.firstOrError()

    // ========================================================================
    // Media Settings
    // ========================================================================

    @kotlinx.serialization.Serializable
    data class MediaSettings(
        val autoDownloadImages: Boolean = true,
        val maxImageQuality: Int = 80,
        val enableTts: Boolean = true,
        val defaultTtsVoice: String = "",
        val ttsSpeed: Float = 1.0f,
        val ttsPitch: Float = 1.0f,
        val enableStt: Boolean = true,
        val sttLanguage: String = "fr"
    )

    suspend fun saveMediaSettings(settings: MediaSettings): Result<Unit> = Result.runCatching {
        val json = kotlinx.serialization.json.Json.encodeToString(settings)
        dataStore.updateDataAsync { it[KEY_MEDIA_SETTINGS] = json }.await()
    }

    fun observeMediaSettings(): Single<MediaSettings> = dataStore.data.map {
        val json = it[KEY_MEDIA_SETTINGS] ?: return@map MediaSettings()
        kotlinx.serialization.json.Json.decodeFromString<MediaSettings>(json)
    }.firstOrError()

    // ========================================================================
    // Backup Settings
    // ========================================================================

    @kotlinx.serialization.Serializable
    data class BackupSettings(
        val autoBackup: Boolean = true,
        val backupIntervalHours: Int = 24,
        val backupLocation: String = "internal",
        val encryptBackups: Boolean = true,
        val maxBackups: Int = 10,
        val includeMedia: Boolean = true,
        val includeDatabase: Boolean = true
    )

    suspend fun saveBackupSettings(settings: BackupSettings): Result<Unit> = Result.runCatching {
        val json = kotlinx.serialization.json.Json.encodeToString(settings)
        dataStore.updateDataAsync { it[KEY_BACKUP_SETTINGS] = json }.await()
    }

    fun observeBackupSettings(): Single<BackupSettings> = dataStore.data.map {
        val json = it[KEY_BACKUP_SETTINGS] ?: return@map BackupSettings()
        kotlinx.serialization.json.Json.decodeFromString<BackupSettings>(json)
    }.firstOrError()

    // ========================================================================
    // Clear All
    // ========================================================================

    suspend fun clearAll(): Result<Unit> = Result.runCatching {
        dataStore.updateDataAsync { it.clear() }.await()
    }
}