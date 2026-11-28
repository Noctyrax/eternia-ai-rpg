package com.eternia.ai.rpg.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "worlds")
@Serializable
data class WorldEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)

data class WorldSummary(
    val id: String,
    val name: String,
    val description: String
)
