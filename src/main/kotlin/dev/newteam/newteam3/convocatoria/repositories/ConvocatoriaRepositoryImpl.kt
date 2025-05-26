package dev.newteam.newteam3.convocatoria.repositories

import dev.newteam.newteam3.convocatoria.dao.ConvocatoriaDao
import dev.newteam.newteam3.convocatoria.models.Convocatoria
import org.lighthousegames.logging.logging

private val logger = logging()
class ConvocatoriaRepositoryImpl(
    private val convocatoriaDao: ConvocatoriaDao
) : ConvocatoriaRepository {
    override fun findAll(): List<Convocatoria> {
        TODO("Not yet implemented")
    }

    override fun findById(id: Int): Convocatoria? {
        TODO("Not yet implemented")
    }

    override fun save(convocatoria: Convocatoria): Convocatoria {
        TODO("Not yet implemented")
    }

    override fun deleteById(id: Int): Int {
        TODO("Not yet implemented")
    }

    override fun deleteAll(): Int {
        TODO("Not yet implemented")
    }

    override fun saveAll(persona: List<Convocatoria>): List<Convocatoria> {
        TODO("Not yet implemented")
    }
}