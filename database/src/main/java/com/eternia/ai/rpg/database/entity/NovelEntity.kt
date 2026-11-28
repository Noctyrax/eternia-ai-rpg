package com.eternia.ai.rpg.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "novels")
@Serializable
data class NovelEntity(
    @PrimaryKey val id: String,
    val title: String,
    val author: String? = null,
    val progress: Float = 0f,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
