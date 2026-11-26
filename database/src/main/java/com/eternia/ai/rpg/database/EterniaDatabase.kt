package com.eternia.ai.rpg.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.eternia.ai.rpg.database.converters.Converters
import com.eternia.ai.rpg.database.dao.*
import com.eternia.ai.rpg.database.entity.*
import com.eternia.ai.rpg.core.Constants

@Database(
    entities = [
        // Core
        PersonaEntity::class,
        CharacterEntity::class,
        ConversationEntity::class,
        MessageEntity::class,
        WorldEntity::class,
        // Lorebooks
        LorebookEntity::class,
        LorebookEntryEntity::class,
        // RPG
        RpgCharacterEntity::class,
        InventoryItemEntity::class,
        SkillEntity::class,
        QuestEntity::class,
        QuestObjectiveEntity::class,
        CombatLogEntity::class,
        NpcEntity::class,
        LocationEntity::class,
        // Reader
        NovelEntity::class,
        ChapterEntity::class,
        BranchEntity::class,
        BookmarkEntity::class,
        ReadingSessionEntity::class,
        // Visual Novel
        VisualNovelEntity::class,
        VnSceneEntity::class,
        VnCharacterEntity::class,
        VnDialogueEntity::class,
        VnChoiceEntity::class,
        VnVariableEntity::class,
        VnSaveEntity::class,
        VnEndingEntity::class,
        // Memory
        MemorySummaryEntity::class,
        RecollectionEntity::class,
        MemoryIndexEntity::class,
        ContextPackEntity::class,
        // Companions
        CompanionGroupEntity::class,
        CompanionGroupMemberEntity::class,
        RelationshipEntity::class,
        ProactiveMessageEntity::class,
        NotificationEntity::class,
        // Media
        GeneratedImageEntity::class,
        AudioFileEntity::class,
        AttachmentEntity::class,
        MediaAlbumEntity::class,
        MediaAlbumItemEntity::class,
        // Settings
        ApiProviderEntity::class,
        ModelProfileEntity::class,
        AppSettingsEntity::class,
        UserPreferenceEntity::class,
        PresetEntity::class,
        TemplateEntity::class,
        // Import/Export
        ImportHistoryEntity::class,
        ExportHistoryEntity::class,
        BackupRecordEntity::class,
        MigrationRecordEntity::class,
        QuickReplyEntity::class,
        ScriptEntity::class,
        AgentActionEntity::class
    ],
    version = 1,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class EterniaDatabase : RoomDatabase() {

    abstract fun personaDao(): PersonaDao
    abstract fun characterDao(): CharacterDao
    abstract fun conversationDao(): ConversationDao
    abstract fun messageDao(): MessageDao
    abstract fun worldDao(): WorldDao
    abstract fun lorebookDao(): LorebookDao
    abstract fun rpgDao(): RpgDao
    abstract fun readerDao(): ReaderDao
    abstract fun visualNovelDao(): VisualNovelDao
    abstract fun memoryDao(): MemoryDao
    abstract fun companionDao(): CompanionDao
    abstract fun mediaDao(): MediaDao
    abstract fun settingsDao(): SettingsDao
    abstract fun importExportDao(): ImportExportDao

    companion object {
        @Volatile
        private var INSTANCE: EterniaDatabase? = null

        fun getInstance(context: Context): EterniaDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EterniaDatabase::class.java,
                    Constants.DATABASE_NAME
                )
                    .addMigrations(MIGRATION_1_2) // Add migrations as needed
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

        // Migration example for future versions
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                // Example migration - add new columns, tables, etc.
                // database.execSQL("ALTER TABLE personas ADD COLUMN new_column TEXT DEFAULT ''")
            }
        }
    }
}