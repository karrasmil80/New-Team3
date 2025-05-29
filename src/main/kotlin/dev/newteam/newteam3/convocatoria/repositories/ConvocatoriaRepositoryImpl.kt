package dev.newteam.newteam3.convocatoria.repositories

import dev.newteam.newteam3.convocatoria.dao.ConvocatoriaDao
import dev.newteam.newteam3.convocatoria.mapper.toModel
import dev.newteam.newteam3.convocatoria.models.Convocatoria
import org.lighthousegames.logging.logging

private val logger = logging()
class ConvocatoriaRepositoryImpl(
    private val convocatoriaDao: ConvocatoriaDao
) : ConvocatoriaRepository {

    /**
     * Funcion que muestra todo sobre la [Convocatoria]
     */

    override fun findAll(): List<Convocatoria> {
        return convocatoriaDao.findAll().map { it.toModel() }
    }

    /**
     * Funcion que busca por id una [Convocatoria]
     */

    override fun findById(id: Int): Convocatoria? {
        return convocatoriaDao.findById(id)?.toModel()
    }

    /**
     * Funcion que guarda una [Convocatoria]
     */

    override fun save(convocatoria: Convocatoria): Convocatoria {
        val toSave = convocatoriaDao.save(
            jornada = convocatoria.jornada,
            descripcion = convocatoria.descripcion,
        )
        val saved = convocatoria.copy(id = toSave)
        return saved
    }

    /**
     * Funcion que elimina el id de la [Convocatoria]
     */

    override fun deleteById(id: Int): Int {
        return convocatoriaDao.delete(id)
    }

    /**
     * Funcion que borra todo sobre una [Convocatoria]
     */

    override fun deleteAll(): Int {
        return convocatoriaDao.deleteAll()
    }

    /**
     * Funcion que guarda todos los datos sobre las [Convocatoria]
     */

    override fun saveAll(persona: List<Convocatoria>): List<Convocatoria> {
        return persona.map { save(it) }
    }
}