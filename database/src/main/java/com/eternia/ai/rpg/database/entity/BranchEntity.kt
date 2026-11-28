package com.eternia.ai.rpg.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(
    tableName = "branches",
    foreignKeys = [
        ForeignKey(
            entity = NovelEntity::class,
            parentColumns = ["id"],
            childColumns = ["novelId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("novelId")]
)
@Serializable
data class BranchEntity(
    @PrimaryKey val id: String,
    val novelId: String,
    val name: String,
    val description: String? = null,
    val createdAt: Long = System.currentTimeMillis()
)
