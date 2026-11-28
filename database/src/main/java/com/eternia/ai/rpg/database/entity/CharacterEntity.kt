package com.eternia.ai.rpg.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.eternia.ai.rpg.core.Result
import kotlinx.serialization.Serializable

@Entity(
    tableName = "characters",
    foreignKeys = [
        ForeignKey(
            entity = PersonaEntity::class,
            parentColumns = ["id"],
            childColumns = ["personaId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("personaId")]
)
@Serializable
data class CharacterEntity(
    @PrimaryKey val id: String,
    val personaId: String?,
    val name: String,
    val description: String? = null,
    val avatarUrl: String? = null,
    val isFavorite: Boolean = false,
    val isNsfw: Boolean = false,
    val systemPrompt: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
) {
    fun toSummary(): Result<CharacterSummary> = Result.success(
        CharacterSummary(id, name, description.orEmpty(), isFavorite, isNsfw)
    )
}

data class CharacterSummary(
    val id: String,
    val name: String,
    val description: String,
    val isFavorite: Boolean,
    val isNsfw: Boolean
)
