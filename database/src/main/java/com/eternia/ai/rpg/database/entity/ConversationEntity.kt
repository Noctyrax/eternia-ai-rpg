package com.eternia.ai.rpg.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(
    tableName = "conversations",
    foreignKeys = [
        ForeignKey(
            entity = PersonaEntity::class,
            parentColumns = ["id"],
            childColumns = ["personaId"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [Index("personaId")]
)
@Serializable
data class ConversationEntity(
    @PrimaryKey val id: String,
    val personaId: String?,
    val title: String,
    val lastMessage: String? = null,
    val timestamp: Long = System.currentTimeMillis()
)

data class ConversationSummary(
    val id: String,
    val title: String,
    val lastMessage: String,
    val timestamp: Long,
    val personaName: String?
)
