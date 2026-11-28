package com.eternia.ai.rpg.database.dao

import androidx.room.*
import com.eternia.ai.rpg.database.entity.VisualNovelEntity

@Dao
interface VisualNovelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vn: VisualNovelEntity)

    @Update
    suspend fun update(vn: VisualNovelEntity)

    @Delete
    suspend fun delete(vn: VisualNovelEntity)

    @Query("SELECT * FROM visual_novels ORDER BY updatedAt DESC")
    suspend fun getAll(): List<VisualNovelEntity>

    @Query("SELECT * FROM visual_novels WHERE id = :id")
    suspend fun getById(id: String): VisualNovelEntity?
}
