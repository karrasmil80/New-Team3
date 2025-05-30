package dev.newteam.newteam3.convocatoria.service

import com.github.michaelbull.result.Result
import dev.newteam.newteam3.convocatoria.error.ConvocatoriaError
import dev.newteam.newteam3.convocatoria.models.Convocatoria
import dev.newteam.newteam3.plantilla.error.PersonaError
import dev.newteam.newteam3.plantilla.models.Persona

interface ConvocatoriaService {
    fun findAll(): Result<List<Convocatoria>, ConvocatoriaError>
    fun findById(id: Int): Result<Convocatoria?,  ConvocatoriaError>
    fun save(convocatoria: Convocatoria): Result<Convocatoria,  ConvocatoriaError>
    fun deleteById(id: Int): Result<Int,  ConvocatoriaError>
    fun deleteAll(): Result<Int,  ConvocatoriaError>
    fun saveAll(convocatoria: List<Convocatoria>): Result<List<Convocatoria>,  ConvocatoriaError>
}