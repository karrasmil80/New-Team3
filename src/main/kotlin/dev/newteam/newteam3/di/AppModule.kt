package dev.newteam.newteam3.di

import dev.newteam.newteam3.config.Config
import org.koin.dsl.module
import org.lighthousegames.logging.logging

private val logger = logging()

val AppModule = module {

    try {
        single { Config() }
    } catch (e : Exception) {
        println(e)
        logger.error { "No se ha podido crear un singleton de la ${Config()}" }

    }

}