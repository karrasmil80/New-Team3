package dev.newteam.newteam3.convocatoria.utils

import dev.newteam.newteam3.convocatoria.dao.ConvocatoriaDao
import dev.newteam.newteam3.convocatoria.dao.JugadorConvocadoDao
import org.jdbi.v3.core.Jdbi
import org.lighthousegames.logging.logging

fun provideJugadorConvocadoDao(jdbi: Jdbi): JugadorConvocadoDao {
    val logger = logging()
    logger.debug { "Inicializando PersonaDao" }
    return jdbi.onDemand(JugadorConvocadoDao::class.java)
}