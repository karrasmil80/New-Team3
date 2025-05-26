package dev.newteam.newteam3.plantilla.repositories

import dev.newteam.newteam3.plantilla.dao.PersonaDao
import dev.newteam.newteam3.plantilla.extensions.copy
import dev.newteam.newteam3.plantilla.mapper.toEntity
import dev.newteam.newteam3.plantilla.mapper.toEntrenador
import dev.newteam.newteam3.plantilla.mapper.toJugador
import dev.newteam.newteam3.plantilla.mapper.toModel
import dev.newteam.newteam3.plantilla.models.Entrenador
import dev.newteam.newteam3.plantilla.models.Jugador
import dev.newteam.newteam3.plantilla.models.Persona
import org.lighthousegames.logging.logging
import kotlin.jvm.Throws

private val logger = logging()

class PersonaRepositoryImpl (
    private val dao : PersonaDao
) : PersonaRepository {

    /**
     * Funcion que busca desde [PersonaDao] a todas las personas en la base de datos
     */

    override fun findAll(): List<Persona> {
        logger.debug { "Obteniendo todos los miembros de la plantilla" }
        return dao.findAll().map { it.toModel() }
    }

    /**
     * Funcion que busca desde [PersonaDao] un id en concreto
     */

    override fun findById(id: Int): Persona? {
        val persona : Persona? = dao.findById(id)?.toModel()
        return when(persona?.rol) {
            "jugador" -> persona.toJugador()
            "entrenador" -> persona.toEntrenador()
            else -> persona
        }
    }

    /**
     * Funcion que desde [PersonaDao] inserta un nuevo miembro
     */

    override fun save(item: Persona): Persona {
        val personaEntity = item.toEntity()
        val id = dao.save(personaEntity)

        return when(personaEntity.rol) {
            "jugador" -> {
                val jugador = item.toJugador().copy(id = personaEntity.id)
                dao.saveJugador(jugador.toEntity())
                jugador
            }
            "entrenador" -> {
                val entrenador = item.toEntrenador().copy(id = personaEntity.id)
                dao.saveEntrenador(entrenador.toEntity())
                entrenador
            }
            else -> throw IllegalArgumentException("Tipo no soportado")
        }
    }


    /**
    * Funcion que desde [PersonaDao] borra el id de un miembro
     *
     * Borra el identificador de la fila [Entrenador] [Jugador] y [Persona]
    */

    override fun deleteById(id: Int) : Int {
        val persona = dao.findById(id)
        return when(persona?.rol) {
            "jugador" -> {
                val jugador = persona.toJugador()
                dao.deleteJugador(jugador.id)
                dao.delete(persona.id)
            }

            "entrenador" -> {
                val entrenador = persona.toEntrenador()
                dao.deleteEntrenador(entrenador.id)
                dao.delete(persona.id)
            }

            else -> throw IllegalArgumentException("No se puede eliminar por id")
        }
    }

    /**
     * Funcion que guarda un [Jugador]
     */

    override fun saveJugador(jugador: Jugador): Int {
        return dao.saveJugador(jugador.toEntity())
    }

    /**
     * Funcion que guarda un [Entrenador]
     */

    override fun saveEntrenador(entrenador: Entrenador): Int {
        return dao.saveEntrenador(entrenador.toEntity())
    }

    /**
     * Funcion que desde [PersonaDao] borra datos de todos los miembros
     */

    override fun deleteAll() : Int {
        dao.deleteAllJugadores()
        dao.deleteAllEntrenadores()
        return dao.deleteAll()
    }

    /**
     * Funcion que guarda a todos los miembros en la plantilla
     *
     * Con este map los transformamos en una lista
     */

    override fun saveAll(items: List<Persona>): List<Persona> {

        return items.map { item ->
            val id = dao.save(item.toEntity())

            when (item) {
                is Jugador -> {
                    val jugadorConId = item.copy(id = id)
                    dao.saveJugador(jugadorConId.toEntity())
                    jugadorConId
                }
                is Entrenador -> {
                    val entrenadorConId = item.copy(id = id)
                    dao.saveEntrenador(entrenadorConId.toEntity())
                    entrenadorConId
                }
                else -> throw IllegalArgumentException("Rol no soportado: ${item.rol}")
            }
        }
    }
}