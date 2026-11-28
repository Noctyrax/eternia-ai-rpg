package com.eternia.ai.rpg.character_cards

import com.eternia.ai.rpg.database.entity.CharacterEntity

class CharacterManager {

    private var characters: MutableList<CharacterEntity> = mutableListOf()

    fun createCharacter(name: String, description: String? = null): CharacterEntity {
        val character = CharacterEntity(
            id = java.util.UUID.randomUUID().toString(),
            name = name,
            description = description,
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis()
        )
        characters.add(character)
        return character
    }

    fun getAll(): List<CharacterEntity> = characters.toList()

    fun getById(id: String): CharacterEntity? = characters.find { it.id == id }

    fun updateCharacter(id: String, name: String?, description: String?): Boolean {
        val index = characters.indexOfFirst { it.id == id }
        if (index < 0) return false
        val current = characters[index]
        characters[index] = current.copy(
            name = name ?: current.name,
            description = description ?: current.description,
            updatedAt = System.currentTimeMillis()
        )
        return true
    }

    fun deleteCharacter(id: String): Boolean = characters.removeIf { it.id == id }

    fun count(): Int = characters.size
}
