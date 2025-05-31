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

    /**
     * Se encarga de leer un listado de personas facilitadas por un archivo de extensión .csv.
     *
     * @param file Este es el archivo CSV desde el cual se leen los datos.
     * @return Devuelve la lista de personas leídas del archivo.
     * @throws PersonaError.PersonaStorageError Si el archivo no existe, no se puede leer, o tiene un formato incorrecto.
     */
    override fun readFromFile(file: File): Result<List<Persona>, PersonaError> {
        logger.debug { "Leyendo el archivo..." }

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
            val imagen = it[7]
            val rol = it[8]

            when (rol) {
                "Entrenador" -> Entrenador(
                    id = id,
                    nombre = nombre,
                    apellido = apellido,
                    fechaNacimiento = fechaNacimiento.toString(),
                    fechaIncorporacion = fechaIncorporacion.toString(),
                    salario = salario,
                    pais = pais,
                    imagen = imagen,
                    equipo = Persona.Equipos.valueOf(it[9]),
                    especializacion = Entrenador.Especializacion.valueOf(it[10])
                )

                "Jugador" -> Jugador(
                    id = id,
                    nombre = nombre,
                    apellido = apellido,
                    fechaNacimiento = fechaNacimiento.toString(),
                    fechaIncorporacion = fechaIncorporacion.toString(),
                    salario = salario,
                    pais = pais,
                    imagen = imagen,
                    equipo = Persona.Equipos.valueOf(it[9]),
                    posicion = Jugador.Posicion.valueOf(it[11]),
                    dorsal = it[12].toInt(),
                    altura = it[13].toDouble(),
                    peso = it[14].toDouble(),
                    goles = it[15].toInt(),
                    mediaGoles = it[16].toDouble(),
                    partidosJugados = it[17].toInt(),
                    minutosJugados = it[18].toInt()
                )
                else -> return Err(PersonaError.PersonaStorageError("Tiene que ser o un Jugador o un Entrenador."))
            }
        })
    }

    /**
     * Se encarga de escribir una lista de personas en un archivo CSV.
     *
     * @param file El archivo CSV donde escribir los datos.
     * @param persona La lista de personas a escribir en el archivo.
     * @throws PersonaError.PersonaStorageError Si el archivo no es válido o no se puede escribir.
     */

    override fun writeToFile(file: File, persona: List<Persona>): Result<String, PersonaError> {
        logger.debug { "Escribiendo personas en el fichero CSV: $file" }

        val parentFile = file.parentFile
        if (parentFile != null) {
            if (!parentFile.exists()) {
                parentFile.mkdirs()
            }
            if (!parentFile.isDirectory) {
                logger.error { "El directorio padre no es un directorio: ${parentFile.absolutePath}" }
                return Err(PersonaError.PersonaStorageError("El directorio padre no es válido \uD83D\uDE14"))
            }
        }

        if (!file.name.endsWith(".csv", true)) {
            logger.error { "El archivo no tiene extensión .csv: ${file.name}" }
            return Err(PersonaError.PersonaStorageError("El archivo debe tener extensión .csv \uD83D\uDE14"))
        }

        // escritura
        file.writeText("id,nombre,apellidos,fechaNacimiento,fechaIncorporacion,salario,pais,imagen,rol,equipo,especializacion,posicion,dorsal,altura,peso,goles,mediaGoles,partidosJugados,minutosJugados\n")

        persona.forEach { persona ->
            val csvRow = when (persona) {
                is Entrenador -> "${persona.id},${persona.nombre},${persona.apellido},${persona.fechaNacimiento},${persona.fechaIncorporacion},${persona.salario},${persona.pais},${persona.imagen},Entrenador,${persona.equipo},${persona.especializacion},,,,,,,,"
                is Jugador -> "${persona.id},${persona.nombre},${persona.apellido},${persona.fechaNacimiento},${persona.fechaIncorporacion},${persona.salario},${persona.pais},${persona.imagen},Jugador,${persona.equipo},,${persona.posicion},${persona.dorsal},${persona.altura},${persona.peso},${persona.goles},${persona.mediaGoles},${persona.partidosJugados},${persona.minutosJugados}"
                else -> return Err(PersonaError.PersonaStorageError("Tipo de persona desconocido: ${persona::class.simpleName}"))
            }
            file.appendText("$csvRow\n")
        }

        logger.info { "✅ Datos guardados correctamente" }
        return Ok(file.absolutePath)
    }
}