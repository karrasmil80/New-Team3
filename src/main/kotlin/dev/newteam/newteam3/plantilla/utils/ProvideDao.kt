package dev.newteam.newteam3.plantilla.utils

import dev.newteam.newteam3.plantilla.dao.PersonaDao
import org.jdbi.v3.core.Jdbi
import org.lighthousegames.logging.logging

fun providePersonaDao(jdbi: Jdbi): PersonaDao {
    val logger = logging()
    logger.debug { "Inicializando PersonaDao" }
    return jdbi.onDemand(PersonaDao::class.java)
}

