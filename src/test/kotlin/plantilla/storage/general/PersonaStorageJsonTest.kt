package plantilla.storage.general

import dev.newteam.newteam3.plantilla.models.Entrenador
import dev.newteam.newteam3.plantilla.models.Jugador
import dev.newteam.newteam3.plantilla.models.Persona
import dev.newteam.newteam3.plantilla.storage.general.PersonaStorageJson
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import java.io.File
import java.nio.file.Files
import java.time.LocalDate

/*class PersonaStorageJsonTest {
    private lateinit var storageJson: PersonaStorageJson
    private lateinit var tempFile: File


    @BeforeEach
    fun setUp() {
        storageJson = PersonaStorageJson()
        tempFile = Files.createTempFile("plantilla", ".json").toFile()
    }

    @AfterEach
    fun tearDown() {
        Files.deleteIfExists(tempFile.toPath())
    }

    @Test
    @DisplayName("Leer datos correctamente desde JSON")
    fun testLeerDelArchivo() {

        // Given
        val json = """
            [
                {
                    "tipo": "Jugador",
                    "id": "1",
                    "nombre": "Cristian",
                    "apellido": "Ortega",
                    "fechaNacimiento": "2003-05-05",
                    "fechaIncorporacion": "2025-05-05",
                    "salario": "1200.0",
                    "pais": "España",
                    "posicion": "CENTROCAMPISTA",
                    "dorsal": "8",
                    "altura": "1.70",
                    "peso": "60.0",
                    "goles": "35",
                    "partidosJugados": "30",
                    "imagen": "https://example.com/jugador.jpg",
                    "rol": "Jugador",
                    "equipo": "NEW_TEAM",
                    "mediaGoles": "1.1",
                    "minutosJugados": "90"
                },
                {
                    "tipo": "Entrenador",
                    "id": "2",
                    "nombre": "Cristina",
                    "apellido": "Ortega",
                    "fechaNacimiento": "2003-05-05",
                    "fechaIncorporacion": "2025-05-05",
                    "salario": "5000.0",
                    "pais": "España",
                    "imagen": "https://example.com/jugador.jpg",
                    "rol": "Entrenador",
                    "equipo": "NEW_TEAM",
                    "especializacion": "PRINCIPAL"
                }
            ]
        """.trimIndent()
        tempFile.writeText(json)

        // When
        val result = storageJson.readFromFile(tempFile)

        // Then
        assertAll("Validaciones lectura JSON",
            { assertTrue(result.isOk, "La operación debe ser exitosa") },
            { assertTrue(tempFile.exists(), "El archivo debe existir") },
            { assertTrue(tempFile.readText().contains("Cristian"), "Debe contener el nombre del jugador") },
            { assertTrue(tempFile.readText().contains("ENTRENADOR_PORTEROS"), "Debe contener la especialidad del entrenador") }
        )
    }

    @Test
    @DisplayName("Escribir correctamente datos a un archivo JSON")
    fun testEscribirAUnArchivo() {


        // Given
        val jugadoresYEntrenadores = listOf(
            Jugador(
                id = 1,
                nombre = "Cristian",
                apellido = "Ortega",
                fechaNacimiento = LocalDate.of(2003, 5, 5),
                fechaIncorporacion = LocalDate.of(2025, 5, 5),
                salario = 1200.0,
                pais = "España",
                posicion = Jugador.Posicion.DELANTERO,
                dorsal = 8,
                altura = 1.75,
                peso = 60.0,
                goles = 35,
                partidosJugados = 30,
                imagen = "https://example.com/jugador.jpg",
                rol = "Jugador",
                equipo = Persona.Equipos.NEW_TEAM,
                mediaGoles = 1.1,
                minutosJugados = 90
            ),
            Entrenador(
                id = 2,
                nombre = "Cristina",
                apellido = "Ortega",
                fechaNacimiento = LocalDate.of(2003, 5, 5),
                fechaIncorporacion = LocalDate.of(2025, 5, 5),
                salario = 5000.0,
                pais = "España",
                imagen = "https://example.com/jugador.jpg",
                rol = "Entrenador",
                equipo = Persona.Equipos.NEW_TEAM,
                especializacion = Entrenador.Especializacion.PRINCIPAL
            )
        )

        // When
        val result = storageJson.writeToFile(tempFile, jugadoresYEntrenadores)

        // Then
        assertAll("Validaciones escritura JSON",
            { assertTrue(result.isOk, "La operación debe ser exitosa") },
            { assertTrue(tempFile.exists(), "El archivo debe existir después de guardar") },
            { assertTrue(tempFile.readText().isNotEmpty(), "El archivo no debe estar vacío") },
            { assertTrue(tempFile.readText().contains("Cristian"), "Debe contener el nombre del jugador") },
            { assertTrue(tempFile.readText().contains("ENTRENADOR_PORTEROS"), "Debe contener la especialidad del entrenador") }
        )
    }
}*/