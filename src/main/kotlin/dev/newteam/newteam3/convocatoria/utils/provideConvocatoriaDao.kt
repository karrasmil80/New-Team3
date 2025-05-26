package dev.newteam.newteam3.convocatoria.utils

import dev.newteam.newteam3.convocatoria.dao.ConvocatoriaDao
import dev.newteam.newteam3.plantilla.dao.PersonaDao
import org.jdbi.v3.core.Jdbi
import org.lighthousegames.logging.logging

fun provideConvocatoriaDao(jdbi: Jdbi): ConvocatoriaDao {
    val logger = logging()
    logger.debug { "Inicializando PersonaDao" }
    return jdbi.onDemand(ConvocatoriaDao::class.java)
}