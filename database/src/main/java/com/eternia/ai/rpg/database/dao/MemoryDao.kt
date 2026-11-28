package com.eternia.ai.rpg.database.dao

import androidx.room.*
import com.eternia.ai.rpg.database.entity.MemorySummaryEntity

@Dao
interface MemoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(summary: MemorySummaryEntity)

    @Update
    suspend fun update(summary: MemorySummaryEntity)

    @Query("SELECT * FROM memory_summaries WHERE conversationId = :conversationId ORDER BY createdAt DESC")
    suspend fun getByConversation(conversationId: String): List<MemorySummaryEntity>

    @Query("SELECT * FROM memory_summaries WHERE id = :id")
    suspend fun getById(id: String): MemorySummaryEntity?
}
