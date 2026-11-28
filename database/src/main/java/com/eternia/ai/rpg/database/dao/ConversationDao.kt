package com.eternia.ai.rpg.database.dao

import androidx.room.*
import com.eternia.ai.rpg.database.entity.ConversationEntity

@Dao
interface ConversationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(conversation: ConversationEntity)

    @Update
    suspend fun update(conversation: ConversationEntity)

    @Delete
    suspend fun delete(conversation: ConversationEntity)

    @Query("DELETE FROM conversations WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("SELECT * FROM conversations ORDER BY timestamp DESC")
    suspend fun getAll(): List<ConversationEntity>

    @Query("SELECT * FROM conversations WHERE id = :id")
    suspend fun getById(id: String): ConversationEntity?

    @Query("SELECT * FROM conversations WHERE personaId = :personaId ORDER BY timestamp DESC")
    suspend fun getByPersona(personaId: String): List<ConversationEntity>

    @Query("SELECT COUNT(*) FROM conversations")
    suspend fun count(): Int
}
