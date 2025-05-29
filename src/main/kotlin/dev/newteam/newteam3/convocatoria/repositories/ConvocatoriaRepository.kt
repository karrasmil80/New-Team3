package dev.newteam.newteam3.convocatoria.repositories

import dev.newteam.newteam3.convocatoria.models.Convocatoria
import dev.newteam.newteam3.plantilla.models.Entrenador
import dev.newteam.newteam3.plantilla.models.Jugador
import dev.newteam.newteam3.plantilla.models.Persona

interface ConvocatoriaRepository {
    fun findAll(): List<Convocatoria>
    fun findById(id: Int): Convocatoria?
    fun save(convocatoria: Convocatoria): Convocatoria
    fun deleteById(id: Int) : Int
    fun deleteAll() : Int
    fun saveAll(persona : List<Convocatoria>): List<Convocatoria>
}