package com.eternia.ai.rpg.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "companions")
@Serializable
data class CompanionEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String? = null,
    val personality: String? = null,
    val affectionLevel: Int = 0,
    val imageUrl: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
