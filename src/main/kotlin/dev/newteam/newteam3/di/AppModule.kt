package dev.newteam.newteam3.di

import com.github.benmanes.caffeine.cache.Cache
import dev.newteam.newteam3.config.Config
import dev.newteam.newteam3.database.JdbiManager
import dev.newteam.newteam3.plantilla.models.Persona
import dev.newteam.newteam3.plantilla.utils.provideCacheCaffeine
import dev.newteam.newteam3.plantilla.utils.provideDatabaseManager
import org.jdbi.v3.core.Jdbi
import org.koin.dsl.module
import org.lighthousegames.logging.logging

//Logger
private val logger = logging()

val AppModule = module {

    //Lo hacemos todo con try catch para no interrumpir el programa y saber cuando falla

    /**
     * Crea un singleton de [Config]
     */
    try {
        single { Config() }
    } catch (e : Exception) {
        println(e)
        logger.error { "No se ha podido crear un singleton de la ${Config()}" }
    }

    /**
     * Proporciona el [JdbiManager]
     */

    try {
        single<Jdbi> { provideDatabaseManager(
            config = get()
        ) }
    } catch (e : Exception) {
        println(e)
        logger.error { "No se ha podido proporcionar un singleton del manager}" }
    }

    /**
     * Proporciona la cache [Caffeine]
     */

    try {
        single<Cache<Int, Persona>> { provideCacheCaffeine() }
    } catch (e : Exception) {
        println(e)
        logger.error { "No se ha podido proporcionar la cache caffeine" }
    }



}