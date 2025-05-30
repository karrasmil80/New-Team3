package dev.newteam.newteam3.di

import com.github.benmanes.caffeine.cache.Cache
import dev.newteam.newteam3.config.Config
import dev.newteam.newteam3.convocatoria.repositories.ConvocatoriaRepositoryImpl
import dev.newteam.newteam3.convocatoria.utils.provideConvocatoriaDao
import dev.newteam.newteam3.database.JdbiManager
import dev.newteam.newteam3.plantilla.models.Persona
import dev.newteam.newteam3.plantilla.repositories.PersonaRepositoryImpl
import dev.newteam.newteam3.plantilla.service.PersonaServiceImpl
import dev.newteam.newteam3.plantilla.storage.general.PersonaStorageZipImpl
import dev.newteam.newteam3.plantilla.utils.provideCacheCaffeine
import dev.newteam.newteam3.plantilla.utils.provideDatabaseManager
import dev.newteam.newteam3.plantilla.utils.providePersonaDao
import dev.newteam.newteam3.utils.html
import org.jdbi.v3.core.Jdbi
import org.koin.dsl.module
import org.lighthousegames.logging.logging
import kotlin.math.log
import kotlin.math.sin

//Logger
private val logger = logging()

val AppModule = module {

    //Lo hacemos todo con try catch para no interrumpir el programa y saber cuando falla
    //PLANTILLA

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

    /**
     * Proporciona el dao para [PersonaDao]
     */

    try {
        single { providePersonaDao(
            jdbi = get()
        ) }
    } catch (e : Exception) {
        println(e)
        logger.error { "No se ha podido proporcionar el dao" }
    }

    /**
     * Crea un singleton de [PersonaRepositoryImpl]
     */

    try {
        single { PersonaRepositoryImpl(
            dao = get())
        }
    } catch (e : Exception) {
        println(e)
        logger.error { "No se ha podido proporcionar el repositorio" }
    }

    /**
     * Crea un singleton de [PersonaServiceImpl]
     */

    try {
        single { PersonaServiceImpl(
            repository = get(),
            cache = get()
        ) }
    } catch (e : Exception) {
        println(e)
        logger.error { "No se ha podido proporcionar el servicio" }
    }

    /**
     * Crea un singleton de [PersonaViewModel]
     */

    try {

    } catch (e: Exception) {
        println(e)
        logger.error { "No se ha podido proporcionar el view model" }
    }

    //Convocatoria

    /**
     * Proporciona el dao [ConvocatoriaDao]
     */

    try {
        single { provideConvocatoriaDao(
            jdbi = get()
        )}
    } catch (e : Exception) {
        println(e)
        logger.error { "no se ha podido proporcionar el dao par" }
    }

    /**
     * Crea un singleton de [ConvocatoriaRepository]
     */

    try {
        single { ConvocatoriaRepositoryImpl(
            convocadoDao = get(),
            dao = get()
        )}
    } catch (e : Exception) {
        println(e)
        logger.error { "No se ha podido crear un singleton de ConvocatoriaRepositoryImpl" }
    }

    /**
     * Proporciona un singleton de la clase [html]
     */

    try {
        single { html() }
    } catch (e : Exception) {
        println(e)
        logger.debug { "No se ha podido crear un singleton de la clase html" }
    }

    /**
     * Crea un singleton de [PersonaStorageZipImpl]
     */

    try {
        single { PersonaStorageZipImpl(
            config = get(),
            storageJson = get()
        )}
    } catch (e : Exception) {
        println(e)
        logger.error { "No se ha podido crear un singleton de PersonaStorageZipImpl" }
    }

}