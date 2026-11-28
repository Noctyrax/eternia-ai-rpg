package com.eternia.ai.rpg.personas

import com.eternia.ai.rpg.database.entity.PersonaEntity

class PersonaManager {

    fun createPersona(name: String, description: String? = null): PersonaEntity {
        return PersonaEntity(
            id = java.util.UUID.randomUUID().toString(),
            name = name,
            description = description,
            createdAt = System.currentTimeMillis(),
            updatedAt = System.currentTimeMillis()
        )
    }
}
