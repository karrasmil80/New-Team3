package dev.newteam.newteam3.plantilla.storage.general

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import dev.newteam.newteam3.plantilla.dto.PersonaDto
import dev.newteam.newteam3.plantilla.error.PersonaError
import dev.newteam.newteam3.plantilla.mapper.toDto
import dev.newteam.newteam3.plantilla.mapper.toEntrenador
import dev.newteam.newteam3.plantilla.mapper.toJugador
import dev.newteam.newteam3.plantilla.models.Entrenador
import dev.newteam.newteam3.plantilla.models.Jugador
import dev.newteam.newteam3.plantilla.models.Persona
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.lighthousegames.logging.logging
import java.io.File

class PersonaStorageJson: PersonaStorage{

    private val logger = logging()

    /**
     * Se encarga de leer un listado de personas facilitadas por un archivo de extensión .json.
     *
     * @param file Este es el archivo JSON desde el cual se leen los datos.
     * @return Devuelve la lista de personas leídas del archivo.
     * @throws PersonaError.PersonaStorageError Si el archivo no existe, no se puede leer, o tiene un formato incorrecto.
     */
    override fun readFromFile(file: File): Result<List<Persona>, PersonaError> {
        logger.debug { "🔷 Leyendo personas de fichero JSON: $file" }

        // verificación de que el archivo sea válido antes de intentar leerlo
        if (!file.exists() || !file.isFile || !file.canRead() || file.length() == 0L || !file.name.endsWith(".json")) {
            logger.error { "El fichero no existe/no es legible/está vacío/no es .json: $file" }
            return Err(PersonaError.PersonaStorageError("El fichero no existe/no es legible/está vacío/no es .json: $file"))
        }
        val json = Json { ignoreUnknownKeys = true; prettyPrint = true }
        val imprimirJson = file.readText()
        val listaPlantillaDto = json.decodeFromString<List<PersonaDto>>(imprimirJson)

        val listaPersonalModel = listaPlantillaDto.map {
            // condicional para separar por roles
            when (it.rol) {
                "Entrenador" -> it.toEntrenador()
                else -> it.toJugador()
            }
        }
        return Ok(listaPersonalModel)
    }


    /**
     * Se encarga de escribir una lista de personas en un archivo JSON.
     *
     * @param file El archivo JSON donde escribir los datos.
     * @param persona La lista de personas a escribir en el archivo.
     * @throws PersonaError.PersonaStorageError Si el archivo no es válido o no se puede escribir.
     */
    override fun writeToFile(file: File, persona: List<Persona>): Result<String, PersonaError> {
        if (!file.parentFile.exists() || !file.parentFile.isDirectory || !file.canWrite()) {
            throw IllegalArgumentException("El fichero json no se puede sobreescribir o no existe en su directorio padre")
        } else {
            val json = Json { ignoreUnknownKeys = true; prettyPrint = true }
            val listaPersonalDto = persona.map {
                when (it) {
                    is Jugador -> { it.toDto() }
                    is Entrenador -> { it.toDto() }
                    else -> null
                }
            }

            val jsonString = json.encodeToString(listaPersonalDto)
            file.writeText(jsonString)
        }

        return Ok("Archivo escrito correctamente en: ${file.absolutePath}")
    }
}