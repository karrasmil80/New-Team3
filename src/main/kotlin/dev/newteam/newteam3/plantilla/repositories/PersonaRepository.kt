package dev.newteam.newteam3.plantilla.repositories

import dev.newteam.newteam3.plantilla.models.Entrenador
import dev.newteam.newteam3.plantilla.models.Jugador
import dev.newteam.newteam3.plantilla.models.Persona

interface PersonaRepository {
    fun findAll(): List<Persona>
    fun findById(id: Int): Persona?
    fun save(persona: Persona): Int
    fun deleteById(id: Int) : Int
    fun saveJugador(jugador: Jugador): Int
    fun saveEntrenador(entrenador: Entrenador): Int
    fun deleteAll() : Int
    fun saveAll(persona : List<Persona>): List<Persona>
}