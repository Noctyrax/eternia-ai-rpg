package com.eternia.ai.rpg.database.dao

import androidx.room.*
import com.eternia.ai.rpg.database.entity.CompanionEntity

@Dao
interface CompanionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(companion: CompanionEntity)

    @Update
    suspend fun update(companion: CompanionEntity)

    @Delete
    suspend fun delete(companion: CompanionEntity)

    @Query("SELECT * FROM companions ORDER BY updatedAt DESC")
    suspend fun getAll(): List<CompanionEntity>

    @Query("SELECT * FROM companions WHERE id = :id")
    suspend fun getById(id: String): CompanionEntity?
}
