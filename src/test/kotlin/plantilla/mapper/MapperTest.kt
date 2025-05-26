package plantilla.mapper

import dev.newteam.newteam3.plantilla.dao.PersonaEntity
import dev.newteam.newteam3.plantilla.mapper.toEntity
import dev.newteam.newteam3.plantilla.mapper.toModel
import dev.newteam.newteam3.plantilla.models.Entrenador
import dev.newteam.newteam3.plantilla.models.Jugador
import dev.newteam.newteam3.plantilla.models.Persona
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.LocalDate
import org.junit.jupiter.api.Assertions.*

class PersonaMapperTest {

    private val jugador = Jugador(
        id = 1,
        nombre = "Cristiano",
        apellido = "Ronaldo",
        fechaNacimiento = LocalDate.of(1985, 2, 5),
        fechaIncorporacion = LocalDate.of(2021, 7, 1),
        salario = 5000000.0,
        pais = "Portugal",
        imagen = "cr7.jpg",
        equipo = Persona.Equipos.NEW_TEAM,
        posicion = Jugador.Posicion.DELANTERO,
        dorsal = 7,
        altura = 1.87,
        peso = 83.0,
        goles = 30,
        mediaGoles = 0.8,
        partidosJugados = 38,
        minutosJugados = 3420
    )

    private val entrenador = Entrenador(
        id = 2,
        nombre = "Zinedine",
        apellido = "Zidane",
        fechaNacimiento = LocalDate.of(1972, 6, 23),
        fechaIncorporacion = LocalDate.of(2020, 8, 10),
        salario = 4000000.0,
        pais = "Francia",
        imagen = "zizou.jpg",
        equipo = Persona.Equipos.NEW_TEAM,
        especializacion = Entrenador.Especializacion.PRINCIPAL
    )

    private val jugadorEntity = PersonaEntity(
        id = 1,
        nombre = "Cristiano",
        apellido = "Ronaldo",
        fechaNacimiento = LocalDate.of(1985, 2, 5),
        fechaIncorporacion = LocalDate.of(2021, 7, 1),
        salario = 5000000.0,
        pais = "Portugal",
        imagen = "cr7.jpg",
        rol = "jugador",
        equipo = Persona.Equipos.NEW_TEAM,
    )

    private val entrenadorEntity = PersonaEntity(
        id = 2,
        nombre = "Zinedine",
        apellido = "Zidane",
        fechaNacimiento = LocalDate.of(1972, 6, 23),
        fechaIncorporacion = LocalDate.of(2020, 8, 10),
        salario = 4000000.0,
        pais = "Francia",
        imagen = "zizou.jpg",
        rol = "entrenador",
        equipo = Persona.Equipos.NEW_TEAM,
    )

    @Nested
    @DisplayName("Mapeo correcto entre modelos y entidad")
    inner class MapeoCorrecto {

        @Test
        fun JugadoraPersonaEntity() {
            val result = jugador.toEntity()
            assertEquals(jugadorEntity, result)
        }

        @Test
        fun EntrenadoraPersonaEntity() {
            val result = entrenador.toEntity()
            assertEquals(entrenadorEntity, result)
        }

        @Test
        fun PersonaEntityaJugador() {
            val result = jugadorEntity.toModel()
            assertTrue(result is Jugador)
            result as Jugador
            assertEquals(jugador.nombre, result.nombre)
            assertEquals(jugador.posicion, result.posicion)
        }

        @Test
        fun PersonaEntityaEntrenador() {
            val result = entrenadorEntity.toModel()
            assertTrue(result is Entrenador)
            result as Entrenador
            assertEquals(entrenador.nombre, result.nombre)
            assertEquals(entrenador.especializacion, result.especializacion)
            }
        }
}