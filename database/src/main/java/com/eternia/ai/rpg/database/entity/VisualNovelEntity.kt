package com.eternia.ai.rpg.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "visual_novels")
@Serializable
data class VisualNovelEntity(
    @PrimaryKey val id: String,
    val title: String,
    val description: String? = null,
    val coverImageUrl: String? = null,
    val progress: Float = 0f,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
