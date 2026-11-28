package com.eternia.ai.rpg.personas

import com.eternia.ai.rpg.database.entity.PersonaEntity

class PersonaManager {

    private var personas: MutableList<PersonaEntity> = mutableListOf()

    fun createPersona(name: String, description: String? = null): PersonaEntity {
        val persona = PersonaEntity(
            id = java.util.UUID.randomUUID().toString(),
            name = name,
            description = description,
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis()
        )
        personas.add(persona)
        return persona
    }

    fun getAll(): List<PersonaEntity> = personas.toList()

    fun getById(id: String): PersonaEntity? = personas.find { it.id == id }

    fun updatePersona(id: String, name: String?, description: String?): Boolean {
        val index = personas.indexOfFirst { it.id == id }
        if (index < 0) return false
        val current = personas[index]
        personas[index] = current.copy(
            name = name ?: current.name,
            description = description ?: current.description,
            updatedAt = System.currentTimeMillis()
        )
        return true
    }

    fun deletePersona(id: String): Boolean = personas.removeIf { it.id == id }

    fun duplicatePersona(id: String, newName: String? = null): PersonaEntity? {
        val original = getById(id) ?: return null
        return createPersona(
            name = newName ?: "${original.name} (Copie)",
            description = original.description
        )
    }

    fun count(): Int = personas.size
}
