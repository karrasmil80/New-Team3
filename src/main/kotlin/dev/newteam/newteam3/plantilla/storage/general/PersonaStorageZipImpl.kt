package dev.newteam.newteam3.plantilla.storage.general

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import dev.newteam.newteam3.config.Config
import dev.newteam.newteam3.plantilla.error.PersonaError
import dev.newteam.newteam3.plantilla.models.Persona
import org.lighthousegames.logging.logging
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardCopyOption
import java.util.zip.ZipEntry
import java.util.zip.ZipFile
import java.util.zip.ZipOutputStream
import kotlin.io.path.name

private val logger = logging()
class PersonaStorageZipImpl(
    private val config: Config,
    private val storageJson : PersonaStorageJson
) : PersonaStorageZip {

    private val tempDirName = "personal"
    override fun exportToZip(fileToZip: File, data: List<Persona>): Result<File, PersonaError> {
        logger.debug { "Exportando a ZIP $fileToZip" }
        val tempDir = Files.createTempDirectory(tempDirName)
        return try {

            data.forEach {
                val file = File(config.imagesDirectory + it.imagen)
                if (file.exists()) {
                    Files.copy(
                        file.toPath(),
                        Paths.get(tempDir.toString(), file.name),
                        StandardCopyOption.REPLACE_EXISTING
                    )
                }
            }
            storageJson.writeToFile(File("$tempDir/data.json"), data)
            // Listamos por consola el contenido del directorio temporal
            Files.walk(tempDir).forEach { logger.debug { it } }
            val archivos = Files.walk(tempDir)
                .filter { Files.isRegularFile(it) }
                .toList()
            ZipOutputStream(Files.newOutputStream(fileToZip.toPath())).use { zip ->
                archivos.forEach { archivo ->
                    val entradaZip = ZipEntry(tempDir.relativize(archivo).toString())
                    zip.putNextEntry(entradaZip)
                    Files.copy(archivo, zip)
                    zip.closeEntry()
                }
            }
            tempDir.toFile().deleteRecursively()
            Ok(fileToZip)
        } catch (e: Exception) {
            logger.error { "Error al exportar a ZIP: ${e.message}" }
            Err(PersonaError.PersonaStorageError("Error al exportar a ZIP: ${e.message}"))
        }
    }

    override fun loadFromZip(fileToUnzip: File): Result<List<Persona>, PersonaError> {
        logger.debug { "Importando desde ZIP $fileToUnzip" }
        val tempDir = Files.createTempDirectory(tempDirName)
        return try {
            ZipFile(fileToUnzip).use { zip ->
                zip.entries().asSequence().forEach { entrada ->
                    zip.getInputStream(entrada).use { input ->
                        Files.copy(
                            input,
                            Paths.get(tempDir.toString(), entrada.name),
                            StandardCopyOption.REPLACE_EXISTING
                        )
                    }
                }
            }
            Files.walk(tempDir).forEach {
                // copiamos todas las imagenes, es decir, todo lo que no es .json al directorio de imagenes
                if (!it.toString().endsWith(".json") && Files.isRegularFile(it)) {
                    Files.copy(
                        it,
                        Paths.get(config.imagesDirectory, it.name),
                        StandardCopyOption.REPLACE_EXISTING
                    )
                }
            }
            val data = storageJson.readFromFile(File("$tempDir/data.json"))
            tempDir.toFile().deleteRecursively()
            return data
        } catch (e: Exception) {
            logger.error { "Error al importar desde ZIP: ${e.message}" }
            Err(PersonaError.PersonaStorageError("Error al importar desde ZIP: ${e.message}"))
        }
    }
}