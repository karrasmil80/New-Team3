package storage

import com.github.michaelbull.result.get
import com.github.michaelbull.result.getError
import dev.newteam.newteam3.plantilla.error.PersonaError
import dev.newteam.newteam3.plantilla.models.Entrenador
import dev.newteam.newteam3.plantilla.models.Jugador
import dev.newteam.newteam3.plantilla.models.Persona
import dev.newteam.newteam3.plantilla.storage.general.PersonaStorageCsv
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.io.File
import java.time.LocalDate

/*class PersonaStorageCsvTest {

    private val personaStorageCsv = PersonaStorageCsv()

    private val jugadorCsvLine = "1,Cristiano,Ronaldo,1985-02-05,2023-08-15,4000.0,Portugal,Jugador,cristiano.png,REAL_MADRID,DELANTERO,7,1.87,84.0,700,0.83,900,80000"
    private val entrenadorCsvLine = "2,Zinedine,Zidane,1972-06-23,2022-05-10,80000.0,Francia,Entrenador,zidane.png,REAL_MADRID,PRINCIPAL"

    val jugador = Jugador(
        id = 1,
        nombre = "Cristiano",
        apellido = "Ronaldo",
        fechaNacimiento = LocalDate.of(1985, 2, 5),
        fechaIncorporacion = LocalDate.of(2023, 8, 15),
        salario = 4000.0,
        pais = "Portugal",
        imagen = "cristiano.png",
        equipo = Persona.Equipos.REAL_MADRID,
        posicion = Jugador.Posicion.DELANTERO,
        dorsal = 7,
        altura = 1.87,
        peso = 84.0,
        goles = 700,
        mediaGoles = 0.83,
        partidosJugados = 900,
        minutosJugados = 80000
    )

    val entrenador = Entrenador(
        id = 2,
        nombre = "Zinedine",
        apellido = "Zidane",
        fechaNacimiento = LocalDate.of(1972, 6, 23),
        fechaIncorporacion = LocalDate.of(2022, 5, 10),
        salario = 80000.0,
        pais = "Francia",
        imagen = "zidane.png",
        equipo = Persona.Equipos.REAL_MADRID,
        especializacion = Entrenador.Especializacion.PRINCIPAL
    )

    @Test
    @DisplayName("readFromFile retorna Ok con lista de personas si archivo existe y formato correcto")
    fun readFromFile_retornaOk_listaPersonas() {
        val content = """
            id,nombre,apellido,fechaNacimiento,fechaIncorporacion,salario,pais,tipo,imagen,equipo,especializacion,posicion,dorsal,altura,peso,goles,mediaGoles,partidosJugados,minutosJugados
            $jugadorCsvLine
            $entrenadorCsvLine
        """.trimIndent()

        val tempFile = createTempFile("test_persona", ".csv")
        tempFile.writeText(content)

        val result = personaStorageCsv.readFromFile(tempFile)

        assertTrue(result.isOk)
        val personas = result.get()
        assertEquals(2, personas.size)
        assertEquals(jugador, personas[0])
        assertEquals(entrenador, personas[1])

        tempFile.delete()
    }

    @Test
    @DisplayName("readFromFile retorna Err si archivo no existe")
    fun readFromFile_retornaErr_archivoNoExiste() {
        val file = File("archivo_inexistente.csv")
        val result = personaStorageCsv.readFromFile(file)

        assertTrue(result.isErr)
        val error = result.getError()
        assertTrue(error is PersonaError.PersonaStorageError)
        assertEquals("El fichero no existe o no se puede leer: ${file.path}", error.message)
    }

    @Test
    @DisplayName("readFromFile retorna Err si tipo no es Jugador ni Entrenador")
    fun readFromFile_retornaErr_tipoDesconocido() {
        val content = """
            id,nombre,apellido,fechaNacimiento,fechaIncorporacion,salario,pais,tipo,imagen,equipo,especializacion,posicion,dorsal,altura,peso,goles,mediaGoles,partidosJugados,minutosJugados
            3,John,Doe,1990-01-01,2020-01-01,30000.0,USA,Manager,john.png,REAL_MADRID,PRINCIPAL,DELANTERO,10,1.85,80.0,10,0.5,20,1500
        """.trimIndent()

        val tempFile = createTempFile("test_persona_invalid_type", ".csv")
        tempFile.writeText(content)

        val result = personaStorageCsv.readFromFile(tempFile)

        assertTrue(result.isErr)
        val error = result.getError()
        assertTrue(error is PersonaError.PersonaStorageError)
        assertTrue(error.message!!.contains("Tiene que ser o un Jugador o un Entrenador"))

        tempFile.delete()
    }
}*/
