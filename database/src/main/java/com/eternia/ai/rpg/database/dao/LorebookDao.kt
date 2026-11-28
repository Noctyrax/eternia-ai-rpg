package com.eternia.ai.rpg.database.dao

import androidx.room.*
import com.eternia.ai.rpg.database.entity.LorebookEntity

@Dao
interface LorebookDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(lorebook: LorebookEntity)

    @Update
    suspend fun update(lorebook: LorebookEntity)

    @Delete
    suspend fun delete(lorebook: LorebookEntity)

    @Query("DELETE FROM lorebooks WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("SELECT * FROM lorebooks ORDER BY updatedAt DESC")
    suspend fun getAll(): List<LorebookEntity>

    @Query("SELECT * FROM lorebooks WHERE id = :id")
    suspend fun getById(id: String): LorebookEntity?

    @Query("SELECT * FROM lorebooks WHERE worldId = :worldId ORDER BY updatedAt DESC")
    suspend fun getByWorld(worldId: String): List<LorebookEntity>

    @Query("SELECT COUNT(*) FROM lorebooks")
    suspend fun count(): Int
}
