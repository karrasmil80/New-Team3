package dev.newteam.newteam3.config

import org.lighthousegames.logging.logging
import java.io.File
import java.io.InputStream
import java.util.*

private val logger = logging()

private const val CONFIG_FILE_NAME = "application.properties"

class Config {

    private val properties: Properties = Properties()
    private val appPath: String = System.getProperty("user.dir")

    val imagesDirectory: String
    val databaseUrl: String
    val databaseInitTables: Boolean
    val databaseInitData: Boolean
    val databaseLogger: Boolean
    val cacheCapacity: Long
    val cacheExpiration: Long

    init {
        logger.debug { "Cargando configuración de la aplicación con Koin" }
        val inputStream: InputStream = ClassLoader.getSystemResourceAsStream(CONFIG_FILE_NAME)
            ?: throw Exception("No se puede leer el fichero de configuración $CONFIG_FILE_NAME")
        properties.load(inputStream)

        imagesDirectory = "$appPath${File.separator}${readProperty("app.images", "imagenes")}/"
        databaseUrl = readProperty("app.database.url", "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1")
        databaseInitTables = readProperty("app.database.init.tables", "false").toBoolean()
        databaseInitData = readProperty("app.database.init.data", "false").toBoolean()
        databaseLogger = readProperty("app.database.logger", "false").toBoolean()
        cacheCapacity = readProperty("app.cache.capacity", "10").toLong()
        cacheExpiration = readProperty("app.cache.expiration", "60").toLong()
    }

    private fun readProperty(prop: String, default: String): String {
        return try {
            logger.debug { "Leyendo propiedad: $prop" }
            properties.getProperty(prop, default)
        } catch (e: Exception) {
            logger.error { "Error al leer la propiedad $prop: ${e.message}" }
            default
        }
    }
}
