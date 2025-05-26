package dev.newteam.newteam3.plantilla.service

import com.github.michaelbull.result.Result
import dev.newteam.newteam3.plantilla.error.PersonaError
import dev.newteam.newteam3.plantilla.models.Persona

interface PersonaService {
    fun findAll(): Result<List<Persona>, PersonaError>
    fun findById(id: Int): Result<Persona?, PersonaError>
    fun save(persona: Persona): Result<Persona, PersonaError>
    fun deleteById(id: Int): Result<Int, PersonaError>
    fun deleteAll(): Result<Int, PersonaError>
    fun saveAll(personas: List<Persona>): Result<List<Persona>, PersonaError>

}