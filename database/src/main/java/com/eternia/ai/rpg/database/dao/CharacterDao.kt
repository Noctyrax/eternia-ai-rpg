package com.eternia.ai.rpg.database.dao

import androidx.room.*
import com.eternia.ai.rpg.database.entity.CharacterEntity

@Dao
interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: CharacterEntity)

    @Update
    suspend fun update(character: CharacterEntity)

    @Delete
    suspend fun delete(character: CharacterEntity)

    @Query("DELETE FROM characters WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("SELECT * FROM characters ORDER BY updatedAt DESC")
    suspend fun getAll(): List<CharacterEntity>

    @Query("SELECT * FROM characters WHERE id = :id")
    suspend fun getById(id: String): CharacterEntity?

    @Query("SELECT * FROM characters WHERE isFavorite = 1 ORDER BY updatedAt DESC")
    suspend fun getFavorites(): List<CharacterEntity>

    @Query("SELECT COUNT(*) FROM characters")
    suspend fun count(): Int
}
