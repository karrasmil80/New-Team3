package dev.newteam.newteam3.plantilla.utils

import com.github.benmanes.caffeine.cache.Cache
import com.github.benmanes.caffeine.cache.Caffeine
import dev.newteam.newteam3.plantilla.models.Persona
import org.lighthousegames.logging.logging
import java.util.concurrent.TimeUnit

/**
 * Funcion que proporciona la cache [Caffeine]
 * Su tamaña maximo es de 10
 * Expira cada 60 segundos
 */

fun provideCacheCaffeine(): Cache<Int, Persona> {
    val logger = logging()
    logger.debug { "Proporcionando cache caffeine" }
    return Caffeine.newBuilder()
        .maximumSize(10)
        .expireAfterWrite(60, TimeUnit.SECONDS)
        .build<Int, Persona>()
}