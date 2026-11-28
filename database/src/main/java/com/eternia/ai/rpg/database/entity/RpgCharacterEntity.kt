package com.eternia.ai.rpg.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "rpg_characters")
@Serializable
data class RpgCharacterEntity(
    @PrimaryKey val id: String,
    val name: String,
    val level: Int = 1,
    val experience: Long = 0,
    val hp: Int = 10,
    val maxHp: Int = 10,
    val mana: Int = 5,
    val maxMana: Int = 5,
    val stamina: Int = 5,
    val maxStamina: Int = 5,
    val strength: Int = 1,
    val dexterity: Int = 1,
    val constitution: Int = 1,
    val intelligence: Int = 1,
    val wisdom: Int = 1,
    val charisma: Int = 1,
    val luck: Int = 1,
    val attributePoints: Int = 0,
    val createdAt: Long = System.currentTimeMillis(),
    val updatedAt: Long = System.currentTimeMillis()
)
