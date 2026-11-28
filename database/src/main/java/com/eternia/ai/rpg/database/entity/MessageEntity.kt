package com.eternia.ai.rpg.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(
    tableName = "messages",
    foreignKeys = [
        ForeignKey(
            entity = ConversationEntity::class,
            parentColumns = ["id"],
            childColumns = ["conversationId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("conversationId")]
)
@Serializable
data class MessageEntity(
    @PrimaryKey val id: String,
    val conversationId: String,
    val role: String, // "user", "assistant", "system", "tool"
    val content: String,
    val timestamp: Long = System.currentTimeMillis(),
    val metadata: String? = null
)
