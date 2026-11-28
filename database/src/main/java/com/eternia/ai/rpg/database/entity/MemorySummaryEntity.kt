package com.eternia.ai.rpg.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "memory_summaries")
@Serializable
data class MemorySummaryEntity(
    @PrimaryKey val id: String,
    val conversationId: String,
    val summary: String,
    val type: String = "conversation",
    val createdAt: Long = System.currentTimeMillis()
)
