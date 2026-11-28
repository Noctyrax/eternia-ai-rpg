package com.eternia.ai.rpg.database.dao

import androidx.room.*
import com.eternia.ai.rpg.database.entity.ChapterEntity

@Dao
interface ChapterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(chapter: ChapterEntity)

    @Update
    suspend fun update(chapter: ChapterEntity)

    @Delete
    suspend fun delete(chapter: ChapterEntity)

    @Query("SELECT * FROM chapters WHERE novelId = :novelId ORDER BY `order` ASC")
    suspend fun getByNovel(novelId: String): List<ChapterEntity>

    @Query("SELECT * FROM chapters WHERE id = :id")
    suspend fun getById(id: String): ChapterEntity?
}
