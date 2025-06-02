package dev.newteam.newteam3.user.utils

import dev.newteam.newteam3.plantilla.dao.PersonaDao
import dev.newteam.newteam3.user.dao.UserDao
import org.jdbi.v3.core.Jdbi
import org.lighthousegames.logging.logging

fun provideUserDao(jdbi: Jdbi): UserDao {
    val logger = logging()
    logger.debug { "Inicializando PersonaDao" }
    return jdbi.onDemand(UserDao::class.java)
}

