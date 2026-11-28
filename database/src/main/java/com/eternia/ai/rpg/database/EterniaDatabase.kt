package com.eternia.ai.rpg.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.eternia.ai.rpg.database.dao.CharacterDao
import com.eternia.ai.rpg.database.dao.ConversationDao
import com.eternia.ai.rpg.database.dao.MessageDao
import com.eternia.ai.rpg.database.dao.PersonaDao
import com.eternia.ai.rpg.database.dao.WorldDao
import com.eternia.ai.rpg.database.entity.CharacterEntity
import com.eternia.ai.rpg.database.entity.ConversationEntity
import com.eternia.ai.rpg.database.entity.MessageEntity
import com.eternia.ai.rpg.database.entity.PersonaEntity
import com.eternia.ai.rpg.database.entity.WorldEntity
import com.eternia.ai.rpg.core.Constants

@Database(
    entities = [
        PersonaEntity::class,
        CharacterEntity::class,
        ConversationEntity::class,
        MessageEntity::class,
        WorldEntity::class
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
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
