package plantilla.mapper

import dev.newteam.newteam3.plantilla.dao.JugadorEntity
import dev.newteam.newteam3.plantilla.dto.JugadorDto
import dev.newteam.newteam3.plantilla.dto.PersonaDto
import dev.newteam.newteam3.plantilla.mapper.toDto
import dev.newteam.newteam3.plantilla.mapper.toEntity
import dev.newteam.newteam3.plantilla.mapper.toModel
import dev.newteam.newteam3.plantilla.models.Entrenador
import dev.newteam.newteam3.plantilla.models.Jugador
import dev.newteam.newteam3.plantilla.models.Persona
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDate

class JugadorMapperTest {

    @Test
    fun conversionJugadoraPersonaDto() {
        val jugador = Jugador(
            id = 1,
            nombre = "Cristiano",
            apellido = "Ronaldo",
            fechaNacimiento = LocalDate.of(1985, 2, 5),
            fechaIncorporacion = LocalDate.of(2003, 8, 12),
            salario = 4000000.0,
            pais = "Portugal",
            posicion = Jugador.Posicion.DELANTERO,
            dorsal = 7,
            altura = 1.87,
            peso = 83.0,
            goles = 850,
            mediaGoles = 0.9,
            partidosJugados = 1000,
            minutosJugados = 90000,
            imagen = "ronaldo.jpg",
            rol = "jugador",
            equipo = Persona.Equipos.MUPPET
        )

        val dto = jugador.toDto()

        assertTrue(dto is PersonaDto) // ✅ Validamos que la conversión es correcta

        // Validaciones de propiedades
        assertEquals(jugador.id, dto.id)
        assertEquals(jugador.nombre, dto.nombre)
        assertEquals(jugador.apellido, dto.apellido)
        assertEquals(jugador.fechaNacimiento, dto.fecha_nacimiento)
        assertEquals(jugador.fechaIncorporacion, dto.fecha_incorporacion)
        assertEquals(jugador.salario, dto.salario)
        assertEquals(jugador.pais, dto.pais)
        assertEquals(jugador.posicion, dto.posicion)
        assertEquals(jugador.dorsal, dto.dorsal)
        assertEquals(jugador.altura, dto.altura)
        assertEquals(jugador.peso, dto.peso)
        assertEquals(jugador.goles, dto.goles)
        assertEquals(jugador.mediaGoles, dto.media_goles)
        assertEquals(jugador.partidosJugados, dto.partidos_jugados)
        assertEquals(jugador.minutosJugados, dto.minutos_jugados)
        assertEquals(jugador.imagen, dto.imagen)
        assertEquals(jugador.rol, dto.rol)
        assertEquals(jugador.equipo.toString(), dto.equipo)
    }

    @Test
    fun conversionJugadorDtoaJugador() {
        val jugadorDto = JugadorDto(
            id = 1,
            nombre = "Cristiano",
            apellido = "Ronaldo",
            fechaNacimiento = LocalDate.of(1985, 2, 5),
            fechaIncorporacion = LocalDate.of(2003, 8, 12),
            salario = 4000000.0,
            pais = "Portugal",
            posicion = Jugador.Posicion.DELANTERO,
            rol = "jugador",
            dorsal = 7,
            altura = 1.87,
            peso = 83.0,
            goles = 850,
            mediaGoles = 0.9,
            partidosJugados = 1000,
            minutosJugados = 90000,
            imagen = "jugador",
            equipo = Persona.Equipos.MUPPET.toString()
        )

        val persona = jugadorDto.toModel()

        assertTrue(persona is Jugador) // ✅ Validamos que la conversión es correcta

        val jugador = persona as Jugador

        // Validaciones de propiedades
        assertEquals(jugadorDto.id, jugador.id)
        assertEquals(jugadorDto.nombre, jugador.nombre)
        assertEquals(jugadorDto.apellido, jugador.apellido)
        assertEquals(jugadorDto.fechaNacimiento, jugador.fechaNacimiento)
        assertEquals(jugadorDto.fechaIncorporacion, jugador.fechaIncorporacion)
        assertEquals(jugadorDto.salario, jugador.salario)
        assertEquals(jugadorDto.pais, jugador.pais)
        assertEquals(jugadorDto.posicion, jugador.posicion)
        assertEquals(jugadorDto.rol, jugador.rol)
        assertEquals(jugadorDto.dorsal, jugador.dorsal)
        assertEquals(jugadorDto.altura, jugador.altura)
        assertEquals(jugadorDto.peso, jugador.peso)
        assertEquals(jugadorDto.goles, jugador.goles)
        assertEquals(jugadorDto.mediaGoles, jugador.mediaGoles)
        assertEquals(jugadorDto.partidosJugados, jugador.partidosJugados)
        assertEquals(jugadorDto.minutosJugados, jugador.minutosJugados)
        assertEquals(jugadorDto.imagen, jugador.imagen)
        assertEquals(Persona.Equipos.valueOf(jugadorDto.equipo), jugador.equipo)
    }

    @Test
    fun conversionJugadorDtoaEntrenador() {
        val entrenadorDto = JugadorDto(
            id = 2,
            nombre = "Pep",
            apellido = "Guardiola",
            fechaNacimiento = LocalDate.of(1971, 1, 18),
            fechaIncorporacion = LocalDate.of(2008, 6, 1),
            salario = 5000000.0,
            pais = "España",
            posicion = Jugador.Posicion.DELANTERO, // ⚠️ Posición no relevante para entrenador
            rol = "entrenador",
            dorsal = 10,
            altura = 1.80,
            peso = 75.0,
            goles = 0,
            mediaGoles = 0.0,
            partidosJugados = 0,
            minutosJugados = 0,
            imagen = "entrenador",
            equipo = Persona.Equipos.MUPPET.toString()
        )

        val persona = entrenadorDto.toModel()

        assertTrue(persona is Entrenador) // ✅ Validamos que la conversión es correcta

        val entrenador = persona as Entrenador

        // Validaciones de propiedades
        assertEquals(entrenadorDto.id, entrenador.id)
        assertEquals(entrenadorDto.nombre, entrenador.nombre)
        assertEquals(entrenadorDto.apellido, entrenador.apellido)
        assertEquals(entrenadorDto.fechaNacimiento, entrenador.fechaNacimiento)
        assertEquals(entrenadorDto.fechaIncorporacion, entrenador.fechaIncorporacion)
        assertEquals(entrenadorDto.salario, entrenador.salario)
        assertEquals(entrenadorDto.pais, entrenador.pais)
        assertEquals(entrenadorDto.imagen, entrenador.imagen)
        assertEquals(Persona.Equipos.valueOf(entrenadorDto.equipo), entrenador.equipo)
        assertEquals(Entrenador.Especializacion.PRINCIPAL, entrenador.especializacion) // ✅ Valor por defecto
        assertEquals(entrenadorDto.rol, entrenador.rol)
    }

    @Test
    fun conversionJugadorDtoconroldesconocidolanzaexcepcion() {
        val desconocidoDto = JugadorDto(
            id = 3,
            nombre = "X",
            apellido = "Y",
            fechaNacimiento = LocalDate.of(1990, 5, 10),
            fechaIncorporacion = LocalDate.of(2015, 8, 20),
            salario = 50000.0,
            pais = "Desconocido",
            posicion = Jugador.Posicion.DELANTERO,
            rol = "misterioso", // ⚠️ Rol desconocido
            dorsal = 9,
            altura = 1.75,
            peso = 70.0,
            goles = 10,
            mediaGoles = 0.3,
            partidosJugados = 100,
            minutosJugados = 9000,
            imagen = "desconocido.jpg",
            equipo = Persona.Equipos.NEW_TEAM.toString()
        )

        assertThrows<IllegalArgumentException> { desconocidoDto.toModel() } // ✅ Validamos la excepción
    }

    @Test
    fun conversionJugadoraJugadorEntity() {
        val jugador = Jugador(
            id = 3,
            nombre = "Neymar",
            apellido = "Jr",
            fechaNacimiento = LocalDate.of(1992, 2, 5),
            fechaIncorporacion = LocalDate.of(2013, 6, 3),
            salario = 2000000.0,
            pais = "Brasil",
            posicion = Jugador.Posicion.DELANTERO,
            dorsal = 10,
            altura = 1.75,
            peso = 68.0,
            goles = 500,
            mediaGoles = 0.8,
            partidosJugados = 700,
            minutosJugados = 70000,
            imagen = "neymar.jpg",
            rol = "jugador",
            equipo = Persona.Equipos.NEW_TEAM
        )

        val entity = jugador.toEntity()

        assertTrue(entity is JugadorEntity)

        assertEquals(jugador.id, entity.id)
        assertEquals(jugador.nombre, entity.nombre)
        assertEquals(jugador.apellido, entity.apellido)
        assertEquals(jugador.fechaNacimiento, entity.fechaNacimiento)
        assertEquals(jugador.fechaIncorporacion, entity.fechaIncorporacion)
        assertEquals(jugador.salario, entity.salario)
        assertEquals(jugador.pais, entity.pais)
        assertEquals(jugador.posicion, entity.posicion)
        assertEquals(jugador.dorsal, entity.dorsal)
        assertEquals(jugador.altura, entity.altura)
        assertEquals(jugador.peso, entity.peso)
        assertEquals(jugador.goles, entity.goles)
        assertEquals(jugador.mediaGoles, entity.mediaGoles)
        assertEquals(jugador.partidosJugados, entity.partidosJugados)
        assertEquals(jugador.minutosJugados, entity.minutosJugados)
        assertEquals(jugador.imagen, entity.imagen)
        assertEquals(jugador.rol, entity.rol)
        assertEquals(jugador.equipo, entity.equipo)
    }

    @Test
    fun conversionJugadorEntityaJugador() {
        val jugadorEntity = JugadorEntity(
            id = 1,
            nombre = "Cristiano",
            apellido = "Ronaldo",
            fechaNacimiento = LocalDate.of(1985, 2, 5),
            fechaIncorporacion = LocalDate.of(2003, 8, 12),
            salario = 4000000.0,
            pais = "Portugal",
            posicion = Jugador.Posicion.DELANTERO,
            dorsal = 7,
            altura = 1.87,
            peso = 83.0,
            goles = 850,
            mediaGoles = 0.9,
            partidosJugados = 1000,
            minutosJugados = 90000,
            imagen = "jugador",
            rol = "jugador",
            equipo = Persona.Equipos.MUPPET
        )

        val jugador = jugadorEntity.toModel()

        assertTrue(jugador is Jugador) // ✅ Validamos que la conversión es correcta

        // Validaciones de propiedades
        assertEquals(jugadorEntity.id, jugador.id)
        assertEquals(jugadorEntity.nombre, jugador.nombre)
        assertEquals(jugadorEntity.apellido, jugador.apellido)
        assertEquals(jugadorEntity.fechaNacimiento, jugador.fechaNacimiento)
        assertEquals(jugadorEntity.fechaIncorporacion, jugador.fechaIncorporacion)
        assertEquals(jugadorEntity.salario, jugador.salario)
        assertEquals(jugadorEntity.pais, jugador.pais)
        assertEquals(jugadorEntity.posicion, jugador.posicion)
        assertEquals(jugadorEntity.dorsal, jugador.dorsal)
        assertEquals(jugadorEntity.altura, jugador.altura)
        assertEquals(jugadorEntity.peso, jugador.peso)
        assertEquals(jugadorEntity.goles, jugador.goles)
        assertEquals(jugadorEntity.mediaGoles, jugador.mediaGoles)
        assertEquals(jugadorEntity.partidosJugados, jugador.partidosJugados)
        assertEquals(jugadorEntity.minutosJugados, jugador.minutosJugados)
        assertEquals(jugadorEntity.imagen, jugador.imagen)
        assertEquals(jugadorEntity.rol, jugador.rol)
        assertEquals(jugadorEntity.equipo, jugador.equipo)
    }

    @Test
    fun conversionJugadorDtoaJugadorEntity() {
        val jugadorDto = JugadorDto(
            id = 1,
            nombre = "Cristiano",
            apellido = "Ronaldo",
            fechaNacimiento = LocalDate.of(1985, 2, 5),
            fechaIncorporacion = LocalDate.of(2003, 8, 12),
            salario = 4000000.0,
            pais = "Portugal",
            posicion = Jugador.Posicion.DELANTERO,
            rol = "jugador",
            dorsal = 7,
            altura = 1.87,
            peso = 83.0,
            goles = 850,
            mediaGoles = 0.9,
            partidosJugados = 1000,
            minutosJugados = 90000,
            imagen = "jugador",
            equipo = Persona.Equipos.MUPPET.toString()
        )

        val jugadorEntity = jugadorDto.toEntity()

        assertTrue(jugadorEntity is JugadorEntity) // ✅ Validamos que la conversión es correcta

        // Validaciones de propiedades
        assertEquals(jugadorDto.id, jugadorEntity.id)
        assertEquals(jugadorDto.nombre, jugadorEntity.nombre)
        assertEquals(jugadorDto.apellido, jugadorEntity.apellido)
        assertEquals(jugadorDto.fechaNacimiento, jugadorEntity.fechaNacimiento)
        assertEquals(jugadorDto.fechaIncorporacion, jugadorEntity.fechaIncorporacion)
        assertEquals(jugadorDto.salario, jugadorEntity.salario)
        assertEquals(jugadorDto.pais, jugadorEntity.pais)
        assertEquals(jugadorDto.posicion, jugadorEntity.posicion)
        assertEquals(jugadorDto.rol, jugadorEntity.rol)
        assertEquals(jugadorDto.dorsal, jugadorEntity.dorsal)
        assertEquals(jugadorDto.altura, jugadorEntity.altura)
        assertEquals(jugadorDto.peso, jugadorEntity.peso)
        assertEquals(jugadorDto.goles, jugadorEntity.goles)
        assertEquals(jugadorDto.mediaGoles, jugadorEntity.mediaGoles)
        assertEquals(jugadorDto.partidosJugados, jugadorEntity.partidosJugados)
        assertEquals(jugadorDto.minutosJugados, jugadorEntity.minutosJugados)
        assertEquals(jugadorDto.imagen, jugadorEntity.imagen)
        assertEquals(Persona.Equipos.valueOf(jugadorDto.equipo), jugadorEntity.equipo)
    }


}
