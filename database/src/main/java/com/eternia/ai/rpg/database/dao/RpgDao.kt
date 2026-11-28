package com.eternia.ai.rpg.database.dao

import androidx.room.*
import com.eternia.ai.rpg.database.entity.RpgCharacterEntity

@Dao
interface RpgDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(character: RpgCharacterEntity)

    @Update
    suspend fun update(character: RpgCharacterEntity)

    @Delete
    suspend fun delete(character: RpgCharacterEntity)

    @Query("SELECT * FROM rpg_characters ORDER BY updatedAt DESC")
    suspend fun getAll(): List<RpgCharacterEntity>

    @Query("SELECT * FROM rpg_characters WHERE id = :id")
    suspend fun getById(id: String): RpgCharacterEntity?

    @Query("SELECT COUNT(*) FROM rpg_characters")
    suspend fun count(): Int
}
