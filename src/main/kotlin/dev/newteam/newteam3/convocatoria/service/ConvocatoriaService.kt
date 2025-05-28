package dev.newteam.newteam3.convocatoria.service

import com.github.michaelbull.result.Result
import dev.newteam.newteam3.convocatoria.models.Convocatoria
import dev.newteam.newteam3.plantilla.error.PersonaError

interface ConvocatoriaService {
    fun findAll(): Result<List<Convocatoria>, PersonaError>
    fun findById(id: Int): Result<Convocatoria?, PersonaError>
    fun save(convocatoria: Convocatoria): Result<Convocatoria, PersonaError>
    fun deleteById(id: Int): Result<Int, PersonaError>
    fun deleteAll(): Result<Int, PersonaError>
    fun saveAll(convocatoria: List<Convocatoria>): Result<List<Convocatoria>, PersonaError>

}