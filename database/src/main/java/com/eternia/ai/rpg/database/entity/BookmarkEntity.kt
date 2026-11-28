package com.eternia.ai.rpg.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(
    tableName = "bookmarks",
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
data class BookmarkEntity(
    @PrimaryKey val id: String,
    val novelId: String,
    val chapterId: String?,
    val position: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
)
