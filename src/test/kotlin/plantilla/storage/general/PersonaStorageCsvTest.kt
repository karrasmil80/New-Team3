package plantilla.storage.general

import com.github.michaelbull.result.get
import com.github.michaelbull.result.getError
import dev.newteam.newteam3.plantilla.error.PersonaError
import dev.newteam.newteam3.plantilla.models.Entrenador
import dev.newteam.newteam3.plantilla.models.Jugador
import dev.newteam.newteam3.plantilla.models.Persona
import dev.newteam.newteam3.plantilla.storage.general.PersonaStorageCsv
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.io.File
import java.time.LocalDate

class PersonaStorageCsvTest {

    private val tempFile = File("data_copia/test_personas.csv")
    private val storage = PersonaStorageCsv()

    private val entrenador = Entrenador (
        id = 1,
        nombre = "Carlos",
        apellido = "Alcaraz",
        fechaNacimiento = LocalDate.of(2003, 5, 5),
        fechaIncorporacion = LocalDate.of(2025, 5, 20),
        salario = 4000.0,
        pais = "España",
        rol = "Entrenador",
        imagen = "https://www.directvsports.com/__export/1734643585495/sites/dsports/img/2024/12/19/alcaraz.jpg_1627369046.jpg",
        equipo = Persona.Equipos.NEW_TEAM,
        especializacion = Entrenador.Especializacion.PRINCIPAL
    )

    private val jugador = Jugador (
        id = 1,
        nombre = "Carlos",
        apellido = "Alcaraz",
        fechaNacimiento = LocalDate.of(2003, 5, 5),
        fechaIncorporacion = LocalDate.of(2025, 5, 20),
        salario = 4000.0,
        pais = "España",
        rol = "Jugador",
        imagen = "https://www.directvsports.com/__export/1734643585495/sites/dsports/img/2024/12/19/alcaraz.jpg_1627369046.jpg",
        posicion = Jugador.Posicion.DELANTERO,
        dorsal = 7,
        altura = 1.83,
        peso = 74.0,
        goles = 0,
        partidosJugados = 0,
        equipo = Persona.Equipos.MUPPET,
        mediaGoles = 0.0,
        minutosJugados = 0
    )

    @Test
    @DisplayName("Test de Escribir y leer personas correctamente desde un archivo CSV")
    fun testWriteAndReadCsv() {

        // escritura
        val writeResult = storage.writeToFile(tempFile, listOf(entrenador, jugador))
        assertTrue(writeResult.isOk)
        assertTrue(tempFile.exists())

        // lectura
        val readResult = storage.readFromFile(tempFile)
        assertTrue(readResult.isOk)
        val personas = readResult.get()!!
        assertEquals(2, personas.size)

        val readEntrenador = personas[0] as Entrenador
        val readJugador = personas[1] as Jugador


        // comprobación entrenador
        assertEquals(entrenador.id, readEntrenador.id)
        assertEquals(entrenador.nombre, readEntrenador.nombre)
        assertEquals(entrenador.especializacion, readEntrenador.especializacion)

        // comprobación jugador
        assertEquals(jugador.id, readJugador.id)
        assertEquals(jugador.nombre, readJugador.nombre)
        assertEquals(jugador.posicion, readJugador.posicion)
        assertEquals(jugador.dorsal, readJugador.dorsal)
    }

    @Test
    @DisplayName("Test fallido al intentar leer un archivo inexistente.")
    fun testReadNonExistentFile() {
        val fakeFile = File("archivo_que_no_existe.csv")
        val result = storage.readFromFile(fakeFile)

        assertTrue(result.getError() is PersonaError.PersonaStorageError)
    }

    @Test
    @DisplayName("Test fallido al leer una persona con rol desconocido en el CSV")
    fun testReadFromFileWithUnknownRole() {
        val tempFile = createTempFile("testLecturaRolDesconocido", ".csv")

        tempFile.writeText("""
        id,nombre,apellidos,fechaNacimiento,fechaIncorporacion,salario,pais,imagen,rol,equipo,especializacion,posicion,dorsal,altura,peso,goles,mediaGoles,partidosJugados,minutosJugados
        1,Carlos,Alcaraz,2003-05-03,2025-01-01,3000.0,España,imagen.jpg,Manager,NEW_TEAM,,,,,,,,,
    """.trimIndent())

        val result = storage.readFromFile(tempFile)

        // comprobacion
        assertTrue(result.isErr)
        assertTrue(result.error is PersonaError.PersonaStorageError)
        assertEquals("Tiene que ser o un Jugador o un Entrenador.", result.error.message)

        // borrado del archivo temporal
        tempFile.delete()
    }


    @Test
    @DisplayName("Test fallido al intentar escribir en un directorio inválido.")
    fun testWriteToInvalidFile() {
        val invalidFile = File("CON/personas.csv")
        val result = storage.writeToFile(invalidFile, listOf(jugador))

        // comproacion
        assertTrue(result.getError() is PersonaError.PersonaStorageError)
    }

    @Test
    @DisplayName("Test fallido al intentar escribir en un archivo cuyo directorio padre no existe.")
    fun testWriteToFileWithNonExistentParentDirectory() {

        val nonExistentDir = File("nonExistentDirBloqueada")

        if (nonExistentDir.exists()) {
            nonExistentDir.deleteRecursively()
        }

        nonExistentDir.writeText("bloqueo")

        val tempFile = File(nonExistentDir, "test.csv")
        val result = storage.writeToFile(tempFile, listOf(jugador))

        // comprobaciones
        assertTrue(result.isErr, "El resultado debe ser un error")
        assertTrue(result.error is PersonaError.PersonaStorageError, "El error debe ser del tipo PersonaStorageError")
        assertEquals("El directorio padre no es válido 😔", result.error.message)

        nonExistentDir.delete()
    }
    @Test
    @DisplayName("Test fallido al intentar escribir en un archivo sin extensión .csv")
    fun testWriteToFileWithInvalidExtension() {
        val tempFile = File("data_copia/personas.txt")

        val result = storage.writeToFile(tempFile, listOf(jugador))

        // comrpobaciones
        assertTrue(result.isErr)
        assertTrue(result.error is PersonaError.PersonaStorageError)
        assertEquals("El archivo debe tener extensión .csv 😔", result.error.message)
    }

    @Test
    @DisplayName("Test fallido al intentar escribir una persona de tipo desconocido.")
    fun testWriteToFileWithUnknownPersonaType() {

        val personaDesconocida = object : Persona(
            id = 999,
            nombre = "Desconocido",
            apellido = "Desconocido",
            fechaNacimiento = LocalDate.now(),
            fechaIncorporacion = LocalDate.now(),
            salario = 0.0,
            pais = "N/A",
            imagen = "N/A",
            equipo = Equipos.NEW_TEAM,
            rol = "Desconocido"
        ) {}

        val tempFile = createTempFile("testEscribirArchivo", ".csv")

        val result = storage.writeToFile(tempFile, listOf(personaDesconocida))

        // comprobaciones
        assertTrue(result.isErr)
        assertTrue(result.error is PersonaError.PersonaStorageError)
        assertEquals("Tipo de persona desconocido: ${personaDesconocida::class.simpleName}",result.error.message)

        tempFile.delete()
    }

    // para crear el archivo temporal
    private fun createTempFile(prefix: String, suffix: String): File =
        kotlin.io.path.createTempFile(prefix, suffix).toFile()

}
