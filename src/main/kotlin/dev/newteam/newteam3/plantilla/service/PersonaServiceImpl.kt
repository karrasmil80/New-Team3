package dev.newteam.newteam3.plantilla.service

import com.github.benmanes.caffeine.cache.Cache
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import dev.newteam.newteam3.plantilla.error.PersonaError
import dev.newteam.newteam3.plantilla.extensions.copy
import dev.newteam.newteam3.plantilla.mapper.toEntrenador
import dev.newteam.newteam3.plantilla.mapper.toJugador
import dev.newteam.newteam3.plantilla.models.Jugador
import dev.newteam.newteam3.plantilla.models.Persona
import dev.newteam.newteam3.plantilla.repositories.PersonaRepository
import org.lighthousegames.logging.logging

class PersonaServiceImpl(
    private val repository: PersonaRepository,
    private val cache: Cache<Int, Persona>
) : PersonaService {
    private val logger = logging()

    /**
     * Maneja la logica de negocio desde el repositorio
     * Funcion que busca todos los miembros de la plantilla llamando a [PersonaRepository.findAll].
     * Devuelve un [Result] con la lista de [Persona] o un error de tipo [PersonaError].
     */

    override fun findAll(): Result<List<Persona>, PersonaError> {
        logger.debug { "Buscando todos los miembros de la plantilla" }
        return Ok(repository.findAll())
    }

    /**
     * Funcion que maneja la logica de negocio para buscar una persona por su id
     * desde [PersonaRepository.findById].
     * Dependee del rol, convierte la [Persona] a [Jugador] o [Entrenador].
     * Devuelve un [Result] con la persona o un error [PersonaError.PersonaIdNotFound].
     */

    override fun findById(id: Int): Result<Persona, PersonaError> {
        logger.debug { "findById($id)" }

        val persona = repository.findById(id)

        return when {
            persona == null -> Err(PersonaError.PersonaIdNotFound(id))

            persona.rol == "jugador" -> Ok(persona.toJugador())

            persona.rol == "entrenador" -> Ok(persona.toEntrenador())

            else -> Err(PersonaError.PersonaIdNotFound(id))
        }
    }

    /**
     * Maneja la logica de negocio desde el repositorio
     * Funcion que guarda una [Persona] utilizando [PersonaRepository.save].
     * Devuelve un [Result] con el id guardado o un error [PersonaError].
     */

    override fun save(persona: Persona): Result<Int, PersonaError> {
        val savePersona = repository.save(persona)
        cache.put(savePersona, persona)
        return Ok(savePersona)
    }

    /**
     * Maneja la logica de negocio desde el repositorio
     * Funcion que elimina una persona por su id utilizando [PersonaRepository.deleteById].
     * Devuelve un [Result] con el número de filas afectadas o un error [PersonaError].
     */

    override fun deleteById(id: Int): Result<Int, PersonaError> {
        val filasABorrar = repository.deleteById(id)
        cache.invalidate(id)
        return Ok(filasABorrar)
    }

    /**
     * Maneja la logica de negocio desde el repositorio
     * Funcion que elimina todos los miembros utilizando [PersonaRepository.deleteAll].
     * Devuelve un [Result] con el número de filas afectadas o un error [PersonaError].
     */

    override fun deleteAll(): Result<Int, PersonaError> {
        val filasABorrar = repository.deleteAll()
        cache.invalidateAll() // Limpiamos toda la cache
        return Ok(filasABorrar)
    }

    /**
     * Maneja la logica de negocio desde el repositorio
     * Funcion que guarda una lista de [Persona] utilizando [PersonaRepository.saveAll].
     * Devuelve un [Result] con la lista de personas guardadas o un error [PersonaError].
     */

    override fun saveAll(personas: List<Persona>): Result<List<Persona>, PersonaError> {
        return Ok(repository.saveAll(personas))
    }
}
