package com.eternia.ai.rpg.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.migration.Migration
import com.eternia.ai.rpg.database.dao.CharacterDao
import com.eternia.ai.rpg.database.dao.ConversationDao
import com.eternia.ai.rpg.database.dao.LorebookDao
import com.eternia.ai.rpg.database.dao.LorebookEntryDao
import com.eternia.ai.rpg.database.dao.MessageDao
import com.eternia.ai.rpg.database.dao.PersonaDao
import com.eternia.ai.rpg.database.dao.RpgDao
import com.eternia.ai.rpg.database.dao.WorldDao
import com.eternia.ai.rpg.database.entity.CharacterEntity
import com.eternia.ai.rpg.database.entity.ConversationEntity
import com.eternia.ai.rpg.database.entity.InventoryItemEntity
import com.eternia.ai.rpg.database.entity.LorebookEntity
import com.eternia.ai.rpg.database.entity.LorebookEntryEntity
import com.eternia.ai.rpg.database.entity.MessageEntity
import com.eternia.ai.rpg.database.entity.PersonaEntity
import com.eternia.ai.rpg.database.entity.RpgCharacterEntity
import com.eternia.ai.rpg.database.entity.WorldEntity
import com.eternia.ai.rpg.core.Constants

@Database(
    entities = [
        PersonaEntity::class,
        CharacterEntity::class,
        ConversationEntity::class,
        MessageEntity::class,
        WorldEntity::class,
        LorebookEntity::class,
        LorebookEntryEntity::class,
        RpgCharacterEntity::class,
        InventoryItemEntity::class
    ],
    version = 2,
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
    abstract fun lorebookEntryDao(): LorebookEntryDao
    abstract fun rpgDao(): RpgDao

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
                    .addMigrations(MIGRATION_1_2)
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("CREATE TABLE IF NOT EXISTS lorebooks (id TEXT PRIMARY KEY, world_id TEXT, name TEXT NOT NULL, description TEXT, created_at INTEGER NOT NULL, updated_at INTEGER NOT NULL)")
                database.execSQL("CREATE TABLE IF NOT EXISTS lorebook_entries (id TEXT PRIMARY KEY, lorebook_id TEXT NOT NULL, key TEXT NOT NULL, content TEXT NOT NULL, priority INTEGER NOT NULL DEFAULT 0, created_at INTEGER NOT NULL, FOREIGN KEY(lorebook_id) REFERENCES lorebooks(id) ON DELETE CASCADE)")
                database.execSQL("CREATE TABLE IF NOT EXISTS rpg_characters (id TEXT PRIMARY KEY, name TEXT NOT NULL, level INTEGER NOT NULL DEFAULT 1, experience INTEGER NOT NULL DEFAULT 0, hp INTEGER NOT NULL DEFAULT 10, max_hp INTEGER NOT NULL DEFAULT 10, mana INTEGER NOT NULL DEFAULT 5, max_mana INTEGER NOT NULL DEFAULT 5, stamina INTEGER NOT NULL DEFAULT 5, max_stamina INTEGER NOT NULL DEFAULT 5, strength INTEGER NOT NULL DEFAULT 1, dexterity INTEGER NOT NULL DEFAULT 1, constitution INTEGER NOT NULL DEFAULT 1, intelligence INTEGER NOT NULL DEFAULT 1, wisdom INTEGER NOT NULL DEFAULT 1, charisma INTEGER NOT NULL DEFAULT 1, luck INTEGER NOT NULL DEFAULT 1, attribute_points INTEGER NOT NULL DEFAULT 0, created_at INTEGER NOT NULL, updated_at INTEGER NOT NULL)")
                database.execSQL("CREATE TABLE IF NOT EXISTS inventory_items (id TEXT PRIMARY KEY, character_id TEXT NOT NULL, name TEXT NOT NULL, description TEXT, rarity TEXT NOT NULL DEFAULT 'common', quantity INTEGER NOT NULL DEFAULT 1, weight REAL NOT NULL DEFAULT 0.0, equipped INTEGER NOT NULL DEFAULT 0, created_at INTEGER NOT NULL, FOREIGN KEY(character_id) REFERENCES rpg_characters(id) ON DELETE CASCADE)")
            }
        }
    }
}
