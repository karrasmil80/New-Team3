package dev.newteam.newteam3.players.utils
import dev.newteam.newteam3.config.Config
import dev.newteam.newteam3.database.JdbiManager
import org.jdbi.v3.core.Jdbi
import org.lighthousegames.logging.logging

/**
 * Funcion que proporciona el [JdbiManager]
 * Obtiene los datos desde [Config]
 * Config obtiene los datos de [config.properties]
 */

fun provideDatabaseManager(config: Config): Jdbi {
    val logger = logging()
    logger.debug { "Proporcionando instancia de JdbiManager" }
    return JdbiManager(
        config.databaseUrl,
        config.databaseInitData,
        config.databaseInitTables,
        config.databaseLogger
    ).jdbi
}