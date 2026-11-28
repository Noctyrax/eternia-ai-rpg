package com.eternia.ai.rpg.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(
    tableName = "chapters",
    foreignKeys = [
        ForeignKey(
            entity = NovelEntity::class,
            parentColumns = ["id"],
            childColumns = ["novelId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("novelId")]
)
@Serializable
data class ChapterEntity(
    @PrimaryKey val id: String,
    val novelId: String,
    val title: String,
    val content: String? = null,
    val order: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
)
