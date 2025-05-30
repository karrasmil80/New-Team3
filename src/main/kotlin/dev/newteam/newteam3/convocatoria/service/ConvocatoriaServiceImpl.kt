package dev.newteam.newteam3.convocatoria.service

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import dev.newteam.newteam3.convocatoria.error.ConvocatoriaError
import dev.newteam.newteam3.convocatoria.models.Convocatoria
import dev.newteam.newteam3.convocatoria.repositories.ConvocatoriaRepository

class ConvocatoriaServiceImpl(
    private val repository: ConvocatoriaRepository
) : ConvocatoriaService  {
    override fun findAll(): Result<List<Convocatoria>, ConvocatoriaError> {
        return Ok(repository.findAll())
    }

    override fun findById(id: Int): Result<Convocatoria?, ConvocatoriaError> {
        return Ok(repository.findById(id))
    }

    override fun save(convocatoria: Convocatoria): Result<Convocatoria, ConvocatoriaError> {
        return Ok(repository.save(convocatoria))
    }

    override fun deleteById(id: Int): Result<Int, ConvocatoriaError> {
        val filas = repository.deleteById(id)
        return Ok(filas)
    }

    override fun deleteAll(): Result<Int, ConvocatoriaError> {
        val deleteAll = repository.deleteAll()
        return Ok(deleteAll)
    }

    override fun saveAll(convocatoria: List<Convocatoria>): Result<List<Convocatoria>, ConvocatoriaError> {
        val saveAll = repository.saveAll(convocatoria)
        return Ok(saveAll)
    }

}