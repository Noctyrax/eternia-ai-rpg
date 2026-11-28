package com.eternia.ai.rpg.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.eternia.ai.rpg.core.Result
import kotlinx.serialization.Serializable

@Entity(tableName = "personas")
@Serializable
data class PersonaEntity(
    @PrimaryKey val id: String,
    val name: String,
    val description: String? = null,
    val avatarUrl: String? = null,
    val isFavorite: Boolean = false,
    val isNsfw: Boolean = false,
    val systemPrompt: String? = null,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
) {
    fun toSummary(): Result<PersonaSummary> = Result.success(
        PersonaSummary(id, name, description.orEmpty(), isFavorite, isNsfw)
    )
}

data class PersonaSummary(
    val id: String,
    val name: String,
    val description: String,
    val isFavorite: Boolean,
    val isNsfw: Boolean
)
