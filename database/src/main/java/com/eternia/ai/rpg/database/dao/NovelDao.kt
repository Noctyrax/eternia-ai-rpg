package com.eternia.ai.rpg.database.dao

import androidx.room.*
import com.eternia.ai.rpg.database.entity.NovelEntity

@Dao
interface NovelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(novel: NovelEntity)

    @Update
    suspend fun update(novel: NovelEntity)

    @Delete
    suspend fun delete(novel: NovelEntity)

    @Query("DELETE FROM novels WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("SELECT * FROM novels ORDER BY updatedAt DESC")
    suspend fun getAll(): List<NovelEntity>

    @Query("SELECT * FROM novels WHERE id = :id")
    suspend fun getById(id: String): NovelEntity?

    @Query("SELECT COUNT(*) FROM novels")
    suspend fun count(): Int
}
