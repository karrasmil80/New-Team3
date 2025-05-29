package dev.newteam.newteam3.convocatoria.repositories

import dev.newteam.newteam3.convocatoria.models.Convocatoria

interface ConvocatoriaRepository {
    fun findAll(): List<Convocatoria>
    fun findById(id: Int): Convocatoria?
    fun save(convocatoria: Convocatoria): Convocatoria
    fun deleteById(id: Int) : Int
    fun deleteAll() : Int
    fun saveAll(convocatoria : List<Convocatoria>): List<Convocatoria>
}