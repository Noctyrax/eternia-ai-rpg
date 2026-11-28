package com.eternia.ai.rpg.database.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(
    tableName = "inventory_items",
    foreignKeys = [
        ForeignKey(
            entity = RpgCharacterEntity::class,
            parentColumns = ["id"],
            childColumns = ["characterId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index("characterId")]
)
@Serializable
data class InventoryItemEntity(
    @PrimaryKey val id: String,
    val characterId: String,
    val name: String,
    val description: String? = null,
    val rarity: String = "common",
    val quantity: Int = 1,
    val weight: Double = 0.0,
    val equipped: Boolean = false,
    val createdAt: Long = System.currentTimeMillis()
)
