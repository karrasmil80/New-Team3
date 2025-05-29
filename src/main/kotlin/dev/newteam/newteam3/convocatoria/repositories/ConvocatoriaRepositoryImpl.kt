package dev.newteam.newteam3.convocatoria.repositories

import dev.newteam.newteam3.convocatoria.dao.ConvocatoriaDao
import dev.newteam.newteam3.convocatoria.dao.ConvocatoriaEntity
import dev.newteam.newteam3.convocatoria.dao.JugadorConvocadoDao
import dev.newteam.newteam3.convocatoria.dao.JugadorConvocadoEntity
import dev.newteam.newteam3.convocatoria.models.Convocatoria
import dev.newteam.newteam3.convocatoria.models.JugadorConvocado
import java.util.*

class ConvocatoriaRepositoryImpl(
    private val convocadoDao: JugadorConvocadoDao,
    private val dao: ConvocatoriaDao
) : ConvocatoriaRepository {

    override fun findAll(): List<Convocatoria> {
        return dao.findAll().map { entity ->
            val convocados = convocadoDao.findByConvocatoriaId(entity.id)
            Convocatoria(
                id = entity.id,
                jornada = entity.jornada,
                personalList = convocados.map {
                    JugadorConvocado(
                        id = UUID.fromString(it.id),
                        convocatoriaId = it.convocatoriaId,
                        personaId = it.personaId,
                    )
                }
            )
        }
    }


    override fun findById(id: Int): Convocatoria? {
        val convocatoriaEntity = dao.findById(id) ?: null
        val convocados = convocadoDao.findByConvocatoriaId(id)
        return Convocatoria(
            id = convocatoriaEntity!!.id,
            jornada = convocatoriaEntity.jornada,
            personalList = convocados.map {
                JugadorConvocado(
                    id = UUID.fromString(it.id),
                    convocatoriaId = it.convocatoriaId,
                    personaId = it.personaId,
                )
            }
        )
    }

    override fun save(convocatoria: Convocatoria): Convocatoria {
        val convocatoriaEntity = ConvocatoriaEntity(
            id = convocatoria.id,
            jornada = convocatoria.jornada
        )

        val id = dao.save(convocatoriaEntity)

        convocatoria.personalList.forEach { convocado ->
            convocadoDao.save(
                JugadorConvocadoEntity(
                    id = convocado.id.toString(),
                    personaId = convocado.personaId,
                    convocatoriaId = id
                )
            )
        }

        return convocatoria.copy(id = id)
    }

    override fun deleteById(id: Int): Int {
        convocadoDao.deleteByConvocatoriaId(id)
        return dao.deleteById(id)
    }

    override fun deleteAll(): Int {
        return dao.deleteAll()
    }

    override fun saveAll(convocatoria: List<Convocatoria>): List<Convocatoria> {
        return convocatoria.map { save(it) }
    }

}