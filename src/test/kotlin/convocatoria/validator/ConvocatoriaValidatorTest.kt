package convocatoria.validator

import dev.newteam.newteam3.convocatoria.models.Convocatoria
import dev.newteam.newteam3.convocatoria.models.JugadorConvocatoria
import dev.newteam.newteam3.convocatoria.validator.ConvocatoriaValidator
import dev.newteam.newteam3.plantilla.models.Jugador
import dev.newteam.newteam3.plantilla.models.Persona
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.time.LocalDateTime

class ConvocatoriaValidatorTest {

    private val validator = ConvocatoriaValidator()

    private fun crearJugador(id: Int, posicion: Jugador.Posicion) = Jugador(
        id = id,
        nombre = "Carlos",
        apellido = "Alcaraz",
        fechaNacimiento = LocalDate.of(2003, 5, 5),
        fechaIncorporacion = LocalDate.of(2025, 5, 20),
        salario = 4000.0,
        pais = "España",
        rol = "Jugador",
        imagen = "https://www.directvsports.com/__export/1734643585495/sites/dsports/img/2024/12/19/alcaraz.jpg_1627369046.jpg",
        posicion = posicion,
        dorsal = 7,
        altura = 1.83,
        peso = 74.0,
        goles = 0,
        partidosJugados = 0,
        equipo = Persona.Equipos.MUPPET,
        mediaGoles = 0.0,
        minutosJugados = 0
    )

    private fun convocado(jugador: Jugador) = JugadorConvocatoria(jugador)

    @Test
    @DisplayName("Test Correcto Convocatoria válida")
    fun testConvocatoriaValida() {
        val defensas = List(10) { crearJugador(it + 1, Jugador.Posicion.DEFENSA) }
        val portero = crearJugador(99, Jugador.Posicion.PORTERO)
        val jugadores = defensas + portero

        val convocados = jugadores.map { convocado(it) }
        val onceTitular = jugadores.map { it.id }

        val convocatoria = Convocatoria(
            id = 1,
            descripcion = "Jornada muy importante de la temporada.",
            jornada = LocalDateTime.now().minusDays(1),
            jugadoresConvocados = convocados,
            onceTitular = onceTitular
        )

        val result = validator.validate(convocatoria)

        assertTrue(result.isOk)
        assertEquals(convocatoria, result.value)
    }

    @Test
    @DisplayName("Test Fallo por Convocatoria con fecha futura")
    fun testConvocatoriaFechaFutura() {
        val jugador = crearJugador(1, Jugador.Posicion.DEFENSA)

        val convocatoria = Convocatoria(
            id = 1,
            descripcion = "Una jornada futura.",
            jornada = LocalDateTime.now().plusDays(3),
            jugadoresConvocados = listOf(convocado(jugador)),
            onceTitular = listOf(jugador.id)
        )

        val result = validator.validate(convocatoria)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. La fecha de la jornada no puede estar en el futuro.", result.error.message)
    }



    @Test
    @DisplayName("Test Fallo por Convocatoria sin jugadores")
    fun testConvocatoriaSinJugadoresConvocados() {
        val convocatoria = Convocatoria(
            id = 1,
            descripcion = "Sin jugadores.",
            jornada = LocalDateTime.now().minusDays(1),
            jugadoresConvocados = listOf(),
            onceTitular = listOf()
        )

        val result = validator.validate(convocatoria)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. Debe haber al menos un jugador convocado.", result.error.message)
    }

    @Test
    @DisplayName("Test Fallo por Convocatoria con once titular vacío")
    fun testConvocatoriaOnceTitularVacio() {
        val jugador = crearJugador(1, Jugador.Posicion.PORTERO)

        val convocatoria = Convocatoria(
            id = 1,
            descripcion = "Sin once titular.",
            jornada = LocalDateTime.now().minusDays(1),
            jugadoresConvocados = listOf(convocado(jugador)),
            onceTitular = listOf()
        )

        val result = validator.validate(convocatoria)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. Debe haber al menos un jugador en el once titular.", result.error.message)
    }

    @Test
    @DisplayName("Test Fallo por Convocatoria con más de 11 titulares")
    fun testConvocatoriaOnceTitularExcedido() {
        val jugadores = List(12) { crearJugador(it + 1, Jugador.Posicion.DEFENSA) } // lista de 12 jugadores
        val convocados = jugadores.map { convocado(it) }
        val onceTitular = jugadores.map { it.id }

        val convocatoria = Convocatoria(
            id = 1,
            descripcion = "Once titular excedido.",
            jornada = LocalDateTime.now().minusDays(1),
            jugadoresConvocados = convocados,
            onceTitular = onceTitular
        )

        val result = validator.validate(convocatoria)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. El once titular no puede tener más de 11 jugadores.", result.error.message)
    }
    @Test
    @DisplayName("Test Fallo por Convocatoria con ID negativo/nulo.")
    fun testConvocatoriaIdInvalido() {
        val jugador = crearJugador(1, Jugador.Posicion.DEFENSA)

        val convocatoria = Convocatoria(
            id = 0,
            descripcion = "Convocatoria con ID inválido.",
            jornada = LocalDateTime.now().minusDays(1),
            jugadoresConvocados = listOf(convocado(jugador)),
            onceTitular = listOf(jugador.id)
        )

        val result = validator.validate(convocatoria)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. El ID de la convocatoria debe ser mayor a 0.", result.error.message)
    }

    @Test
    @DisplayName("Test Fallo por Convocatoria con descripción vacía")
    fun testConvocatoriaDescripcionVacia() {
        val jugador = crearJugador(1, Jugador.Posicion.DEFENSA)

        val convocatoria = Convocatoria(
            id = 1,
            descripcion = "  ",
            jornada = LocalDateTime.now().minusDays(1),
            jugadoresConvocados = listOf(convocado(jugador)),
            onceTitular = listOf(jugador.id)
        )

        val result = validator.validate(convocatoria)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. La descripción no puede estar vacía.", result.error.message)
    }

    @Test
    @DisplayName("Test Fallo por Convocatoria con descripción demasiado corta")
    fun testConvocatoriaDescripcionMuyCorta() {
        val jugador = crearJugador(1, Jugador.Posicion.DEFENSA)

        val convocatoria = Convocatoria(
            id = 1,
            descripcion = "Corta",
            jornada = LocalDateTime.now().minusDays(1),
            jugadoresConvocados = listOf(convocado(jugador)),
            onceTitular = listOf(jugador.id)
        )

        val result = validator.validate(convocatoria)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. La descripción debe tener entre 10 y 300 caracteres.", result.error.message)
    }

    @Test
    @DisplayName("Test Fallo por Convocatoria con descripción demasiado larga")
    fun testConvocatoriaDescripcionMuyLarga() {
        val jugador = crearJugador(1, Jugador.Posicion.DEFENSA)
        val descripcionLarga = "A".repeat(301)

        val convocatoria = Convocatoria(
            id = 1,
            descripcion = descripcionLarga,
            jornada = LocalDateTime.now().minusDays(1),
            jugadoresConvocados = listOf(convocado(jugador)),
            onceTitular = listOf(jugador.id)
        )

        val result = validator.validate(convocatoria)

        assertTrue(result.isErr)
        assertEquals("Datos no válidos. La descripción debe tener entre 10 y 300 caracteres.", result.error.message)
    }
}
