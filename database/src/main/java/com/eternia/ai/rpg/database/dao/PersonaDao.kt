package com.eternia.ai.rpg.database.dao

import androidx.room.*
import com.eternia.ai.rpg.database.entity.PersonaEntity

@Dao
interface PersonaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(persona: PersonaEntity)

    @Update
    suspend fun update(persona: PersonaEntity)

    @Delete
    suspend fun delete(persona: PersonaEntity)

    @Query("DELETE FROM personas WHERE id = :id")
    suspend fun deleteById(id: String)

    @Query("SELECT * FROM personas ORDER BY updatedAt DESC")
    suspend fun getAll(): List<PersonaEntity>

    @Query("SELECT * FROM personas WHERE id = :id")
    suspend fun getById(id: String): PersonaEntity?

    @Query("SELECT * FROM personas WHERE isFavorite = 1 ORDER BY updatedAt DESC")
    suspend fun getFavorites(): List<PersonaEntity>

    @Query("SELECT COUNT(*) FROM personas")
    suspend fun count(): Int
}
