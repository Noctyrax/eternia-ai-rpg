package com.eternia.ai.rpg.database.dao

import androidx.room.*
import com.eternia.ai.rpg.database.entity.WorldEntity

@Dao
interface WorldDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(world: WorldEntity)

    @Update
    suspend fun update(world: WorldEntity)

    @Delete
    suspend fun delete(world: WorldEntity)

    @Query("DELETE FROM worlds WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("SELECT * FROM worlds ORDER BY updatedAt DESC")
    suspend fun getAll(): List<WorldEntity>

    @Query("SELECT * FROM worlds WHERE id = :id")
    suspend fun getById(id: String): WorldEntity?

    @Query("SELECT COUNT(*) FROM worlds")
    suspend fun count(): Int
}
