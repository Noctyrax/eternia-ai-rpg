package com.eternia.ai.rpg.core

object Constants {
    // App Info
    const val APP_NAME = "ETERNIA AI RPG"
    const val APP_VERSION = "1.0.0"
    const val APP_PACKAGE = "com.eternia.ai.rpg"

    // Database
    const val DATABASE_NAME = "eternia.db"
    const val DATABASE_VERSION = 1

    // SharedPreferences / DataStore Keys
    const val PREF_FIRST_LAUNCH = "first_launch"
    const val PREF_THEME = "theme"
    const val PREF_LANGUAGE = "language"
    const val PREF_API_KEYS = "api_keys_encrypted"
    const val PREF_PROVIDER_PROFILES = "provider_profiles"
    const val PREF_DEFAULT_PERSONA = "default_persona"
    const val PREF_CHAT_SETTINGS = "chat_settings"
    const val PREF_RPG_SETTINGS = "rpg_settings"
    const val PREF_MEDIA_SETTINGS = "media_settings"
    const val PREF_BACKUP_SETTINGS = "backup_settings"

    // Intents
    const val ACTION_OPEN_CHARACTER = "com.eternia.ai.rpg.OPEN_CHARACTER"
    const val ACTION_OPEN_CHAT = "com.eternia.ai.rpg.OPEN_CHAT"
    const val ACTION_NEW_SESSION = "com.eternia.ai.rpg.NEW_SESSION"
    const val ACTION_IMPORT_FILE = "com.eternia.ai.rpg.IMPORT_FILE"
    const val EXTRA_CHARACTER_ID = "character_id"
    const val EXTRA_SESSION_ID = "session_id"
    const val EXTRA_FILE_URI = "file_uri"

    // Permissions
    const val PERMISSION_READ_MEDIA = "android.permission.READ_MEDIA_IMAGES"
    const val PERMISSION_WRITE_MEDIA = "android.permission.WRITE_EXTERNAL_STORAGE"
    const val PERMISSION_RECORD_AUDIO = "android.permission.RECORD_AUDIO"
    const val PERMISSION_POST_NOTIFICATIONS = "android.permission.POST_NOTIFICATIONS"

    // File Provider
    const val FILE_PROVIDER_AUTHORITY = "com.eternia.ai.rpg.fileprovider"

    // Notifications
    const val NOTIFICATION_CHANNEL_CHAT = "chat_notifications"
    const val NOTIFICATION_CHANNEL_COMPANION = "companion_notifications"
    const val NOTIFICATION_CHANNEL_SYSTEM = "system_notifications"
    const val NOTIFICATION_ID_NEW_MESSAGE = 1001
    const val NOTIFICATION_ID_COMPANION_MESSAGE = 1002
    const val NOTIFICATION_ID_PROACTIVE = 1003

    // WorkManager
    const val WORK_BACKUP = "backup_work"
    const val WORK_SYNC = "sync_work"
    const val WORK_AI_SUMMARY = "ai_summary_work"
    const val WORK_IMAGE_GENERATION = "image_generation_work"

    // AI Providers
    const val PROVIDER_GEMINI = "gemini"
    const val PROVIDER_DEEPSEEK = "deepseek"
    const val PROVIDER_OPENAI = "openai"
    const val PROVIDER_ANTHROPIC = "anthropic"
    const val PROVIDER_OPENROUTER = "openrouter"
    const val PROVIDER_CUSTOM = "custom"

    // Default Models
    const val DEFAULT_GEMINI_MODEL = "gemini-1.5-flash"
    const val DEFAULT_DEEPSEEK_MODEL = "deepseek-chat"
    const val DEFAULT_OPENAI_MODEL = "gpt-4o-mini"
    const val DEFAULT_ANTHROPIC_MODEL = "claude-3-haiku-20240307"
    const val DEFAULT_OPENROUTER_MODEL = "openrouter/auto"

    // Free Tier Limits (approximate)
    const val GEMINI_FREE_RPM = 15
    const val GEMINI_FREE_RPD = 1500
    const val DEEPSEEK_FREE_TOKENS = 500000
    const val OPENROUTER_FREE_MODELS = true

    // Chat Settings
    const val DEFAULT_TEMPERATURE = 0.8f
    const val DEFAULT_TOP_P = 0.9f
    const val DEFAULT_TOP_K = 40
    const val DEFAULT_MAX_TOKENS = 2048
    const val DEFAULT_CONTEXT_WINDOW = 8192
    const val MAX_CONTEXT_WINDOW = 128000
    const val MIN_TEMPERATURE = 0.0f
    const val MAX_TEMPERATURE = 2.0f

    // RPG Engine
    const val BASE_ATTRIBUTES = 7
    const val MAX_ATTRIBUTE_VALUE = 30
    const val MIN_ATTRIBUTE_VALUE = 1
    const val STARTING_ATTRIBUTE_POINTS = 27
    const val LEVEL_UP_ATTRIBUTE_POINTS = 1
    const val MAX_LEVEL = 50
    const val BASE_HP = 10
    const val BASE_MP = 5
    const val BASE_SP = 5

    // Memory
    const val MAX_SUMMARY_LENGTH = 2000
    const val MAX_RECOLLECTIONS = 100
    const val MAX_CONTEXT_PACK_SIZE = 4096
    const val SUMMARY_TRIGGER_THRESHOLD = 50
    const val FACT_EXTRACTION_INTERVAL = 10

    // Media
    const val MAX_IMAGE_SIZE_MB = 10
    const val MAX_AUDIO_DURATION_SEC = 300
    const val SUPPORTED_IMAGE_FORMATS = "image/jpeg,image/png,image/webp,image/gif"
    const val SUPPORTED_AUDIO_FORMATS = "audio/mpeg,audio/ogg,audio/wav,audio/mp4"
    const val THUMBNAIL_SIZE = 256

    // Import/Export
    const val EXPORT_VERSION = 3
    const val SUPPORTED_IMPORT_VERSIONS = intArrayOf(1, 2, 3)
    const val MAX_IMPORT_FILE_SIZE_MB = 100
    const val BACKUP_COMPRESSION_LEVEL = 6

    // UI
    const val ANIMATION_DURATION_SHORT = 150L
    const val ANIMATION_DURATION_MEDIUM = 300L
    const val ANIMATION_DURATION_LONG = 500L
    const val DEFAULT_PADDING = 16f
    const val SMALL_PADDING = 8f
    const val LARGE_PADDING = 24f
    const val CARD_ELEVATION = 4f
    const val FAB_ELEVATION = 6f
    const val BOTTOM_SHEET_PEEK_HEIGHT = 300

    // Network
    const val DEFAULT_TIMEOUT_SECONDS = 60
    const val MAX_RETRIES = 3
    const val RETRY_BASE_DELAY_MS = 1000

    // Logging
    const val LOG_TAG_PREFIX = "ETERNIA_"
    const val LOG_MAX_LINES = 1000

    // Feature Flags
    const val ENABLE_AI_IMAGES = true
    const val ENABLE_TTS = true
    const val ENABLE_STT = true
    const val ENABLE_PROACTIVE_COMPANIONS = true
    const val ENABLE_BACKGROUND_SYNC = false
    const val ENABLE_CRASH_REPORTING = false
    const val ENABLE_ANALYTICS = false
}