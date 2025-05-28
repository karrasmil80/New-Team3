package dev.newteam.newteam3.convocatoria.service

import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import dev.newteam.newteam3.convocatoria.models.Convocatoria
import dev.newteam.newteam3.convocatoria.repositories.ConvocatoriaRepository
import dev.newteam.newteam3.plantilla.error.PersonaError
import org.lighthousegames.logging.logging

private val logger = logging()

class ConvocatoriaServiceImpl (
    private val convocatoriaRepository: ConvocatoriaRepository
) : ConvocatoriaService {
    override fun findAll(): Result<List<Convocatoria>, PersonaError> {
        return Ok(convocatoriaRepository.findAll())
    }

    override fun findById(id: Int): Result<Convocatoria?, PersonaError> {
        return Ok(convocatoriaRepository.findById(id))
    }

    override fun save(convocatoria: Convocatoria): Result<Convocatoria, PersonaError> {
        return Ok(convocatoriaRepository.save(convocatoria))
    }

    override fun deleteById(id: Int): Result<Int, PersonaError> {
        val filasABorrar = convocatoriaRepository.deleteById(id)
        return Ok(filasABorrar)
    }

    override fun deleteAll(): Result<Int, PersonaError> {
        val filasABorrar = convocatoriaRepository.deleteAll()
        return Ok(filasABorrar)
    }

    override fun saveAll(convocatoria: List<Convocatoria>): Result<List<Convocatoria>, PersonaError> {
        val toSave = Ok(convocatoriaRepository.saveAll(convocatoria))
        return Ok(convocatoria)
    }

}