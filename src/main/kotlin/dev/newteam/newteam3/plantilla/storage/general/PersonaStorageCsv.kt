package dev.newteam.newteam3.plantilla.storage.general

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import dev.newteam.newteam3.plantilla.error.PersonaError
import dev.newteam.newteam3.plantilla.models.Entrenador
import dev.newteam.newteam3.plantilla.models.Jugador
import dev.newteam.newteam3.plantilla.models.Persona
import org.lighthousegames.logging.logging
import java.io.File
import java.time.LocalDate

class PersonaStorageCsv: PersonaStorage {
    private val logger = logging()


    override fun readFromFile(file: File): Result<List<Persona>, PersonaError> {
        logger.debug { "Leyendo el archivo..." }

        // condicional simple para verificar que el archivo existe y se puede leer.
        if (!file.isFile || !file.exists() || !file.canRead()) {
            logger.error { "El fichero no existe o no se puede leer: $file" }
            return Err(PersonaError.PersonaStorageError("El fichero no existe o no se puede leer: $file"))
        }

        return Ok(file.readLines().drop(1).map { it.split(",") }.map { it -> it.map { it.trim() } }.map {
            val id = it[0].toInt()
            val nombre = it[1]
            val apellido = it[2]
            val fechaNacimiento = LocalDate.parse(it[3])
            val fechaIncorporacion = LocalDate.parse(it[4])
            val salario = it[5].toDouble()
            val pais = it[6]
            when (it[7]) {
                "Entrenador" -> Entrenador(
                    id = id,
                    nombre = nombre,
                    apellido = apellido,
                    fechaNacimiento = fechaNacimiento,
                    fechaIncorporacion = fechaIncorporacion,
                    salario = salario,
                    pais = pais,
                    imagen = it[8],
                    equipo = Persona.Equipos.valueOf(it[9]),
                    especializacion = Entrenador.Especializacion.valueOf(it[9])
                )

                "Jugador" -> Jugador(
                    id = id,
                    nombre = nombre,
                    apellido = apellido,
                    fechaNacimiento = fechaIncorporacion,
                    fechaIncorporacion = fechaIncorporacion,
                    salario = salario,
                    pais = pais,
                    imagen = it[8],
                    equipo = Persona.Equipos.valueOf(it[9]),
                    posicion = Jugador.Posicion.valueOf(it[10]),
                    dorsal = it[11].toInt(),
                    altura = it[12].toDouble(),
                    peso = it[13].toDouble(),
                    goles = it[14].toInt(),
                    mediaGoles = it[15].toDouble(),
                    partidosJugados = it[16].toInt(),
                    minutosJugados = it[17].toInt()
                )

                else -> return Err(PersonaError.PersonaStorageError("Tiene que ser o un Jugador o un Entrenador."))
            }
        })
    }

    override fun writeToFile(file: File, persona: List<Persona>): Result<String, PersonaError> {
        /*logger.debug { "Escribiendo personas en el fichero CSV: $file" }
        // comprobación de si el archivo es válido
        if (!file.parentFile.exists() || !file.parentFile.isDirectory || !file.name.endsWith(".csv", true)) {
            logger.error { "El directorio padre del fichero no existe o el archivo no es un CSV: ${file.parentFile.absolutePath}" }
            return Err(PersonaError.PersonaStorageError("No se puede escribir en el archivo debido a que no existe o no es de la extensión adecuada 😔"))
        }*/
        TODO("Not yet implemented")
    }


}