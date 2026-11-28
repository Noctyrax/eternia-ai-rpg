package com.eternia.ai.rpg.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(
    tableName = "lorebook_entries",
    foreignKeys = [
        ForeignKey(
            entity = LorebookEntity::class,
            parentColumns = ["id"],
            childColumns = ["lorebookId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("lorebookId")]
)
@Serializable
data class LorebookEntryEntity(
    @PrimaryKey val id: String,
    val lorebookId: String,
    val key: String,
    val content: String,
    val priority: Int = 0,
    val createdAt: Long = System.currentTimeMillis()
)
