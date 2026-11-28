package com.eternia.ai.rpg.database.dao

import androidx.room.*
import com.eternia.ai.rpg.database.entity.LorebookEntryEntity

@Dao
interface LorebookEntryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entry: LorebookEntryEntity)

    @Update
    suspend fun update(entry: LorebookEntryEntity)

    @Delete
    suspend fun delete(entry: LorebookEntryEntity)

    @Query("DELETE FROM lorebook_entries WHERE lorebookId = :lorebookId")
    suspend fun deleteByLorebook(lorebookId: String)

    @Query("SELECT * FROM lorebook_entries WHERE lorebookId = :lorebookId ORDER BY priority DESC")
    suspend fun getByLorebook(lorebookId: String): List<LorebookEntryEntity>
}
