package dev.newteam.newteam3.plantilla.storage.general

import com.github.michaelbull.result.Result
import dev.newteam.newteam3.plantilla.error.PersonaError
import dev.newteam.newteam3.plantilla.models.Persona
import java.io.File

/**
 * Interfaz que actúa a modo de contrato que obligará a todas las clases
 * de almacenamiento que hereden de esta a implementar los métodos que se encargarán
 * de leer los archivos y escribir a otros archivos.
 * */
interface PersonaStorage {
    fun leerDelArchivo (file: File): Result<List<Persona>, PersonaError>
    fun escribirAUnArchivo (file: File, persona: List<Persona>): Result<String, PersonaError>
}