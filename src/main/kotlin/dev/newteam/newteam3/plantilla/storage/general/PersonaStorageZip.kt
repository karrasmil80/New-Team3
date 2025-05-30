package dev.newteam.newteam3.plantilla.storage.general

import com.github.michaelbull.result.Result
import dev.newteam.newteam3.plantilla.error.PersonaError
import dev.newteam.newteam3.plantilla.models.Persona
import java.io.File

interface PersonaStorageZip{
    fun exportToZip(fileToZip: File, data: List<Persona>): Result<File, PersonaError>
    fun loadFromZip(fileToUnzip: File): Result<List<Persona>, PersonaError>
}